package com.ruthiefloats.inventorytracker.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.ruthiefloats.inventorytracker.model.Stock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 7/14/16.
 */
public class StocksDataSource {
    public static final String LOGTAG = "StocksDataSource: ";
    SQLiteOpenHelper DBHelper;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            DBOpenHelper.COLUMN_PRODUCT_NAME,
            DBOpenHelper.COLUMN_QUANTITY,
            DBOpenHelper.COLUMN_PRICE,
            DBOpenHelper.COLUMN_IMAGE,
            DBOpenHelper.COLUMN_ID
    };

    public StocksDataSource(Context context){
        DBHelper = new DBOpenHelper(context);
    }

    public void open(){
         /* getWriteableDatabase calls the onCreate
        and create the table
         */
        database = DBHelper.getWritableDatabase();
        Log.i(LOGTAG, "Database opened");
    }

    public void close() {
        Log.i(LOGTAG, "Database closed");
        database.close();
    }

    public Stock create(Stock stock){
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.COLUMN_QUANTITY, stock.getQuantity());
        values.put(DBOpenHelper.COLUMN_PRODUCT_NAME, stock.getName());
        values.put(DBOpenHelper.COLUMN_PRICE, stock.getPrice());
        values.put(DBOpenHelper.COLUMN_IMAGE, stock.getImageUri());

        long insertid = database.insert(DBOpenHelper.TABLE_INVENTORY, null, values);
        stock.setId(insertid);

        return stock;
    }

    public List<Stock> findAll() {
        List<Stock> stocks = new ArrayList<Stock>();

        Cursor cursor = database.query(DBOpenHelper.TABLE_INVENTORY,
                allColumns, null, null, null, null, null);
        Log.i(LOGTAG, "Number of rows returned: " + cursor.getCount());
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Stock stock = new Stock();
                stock.setId(cursor.getLong(cursor
                        .getColumnIndex(DBOpenHelper.COLUMN_ID)));
                stock.setName(cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_PRODUCT_NAME)));
                stock.setPrice(cursor.getInt(cursor.getColumnIndex(DBOpenHelper.COLUMN_PRICE)));
                stock.setQuantity(cursor.getInt(cursor.getColumnIndex(DBOpenHelper.COLUMN_QUANTITY)));
                stock.setImageUri(cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_IMAGE)));

                stocks.add(stock);
            }
        }
        Log.i(LOGTAG, "All stocks returned");
        return stocks;
    }

    public void deleteRecord(Stock currentStock) {
        open();
        String query = "DELETE FROM " +
                DBOpenHelper.TABLE_INVENTORY +
                " WHERE " +
                DBOpenHelper.COLUMN_ID +
                " = " +
                currentStock.getId();
        database.execSQL(query);
        close();

    }

    public boolean sellOne(Stock currentStock) {
        int newQuantity;

        newQuantity = currentStock.getQuantity()-1;
        if( newQuantity > -1){
            open();
            String query = "UPDATE " +
                    DBOpenHelper.TABLE_INVENTORY +
                    " SET " +
                    DBOpenHelper.COLUMN_QUANTITY +
                    " = " +
                    newQuantity +
                    " WHERE " +
                    DBOpenHelper.COLUMN_ID +
                    " = " +
                    currentStock.getId();
            database.execSQL(query);
            close();

            currentStock.setQuantity(newQuantity);
            return true;
        } else{
            return false;
        }
    }

    public void addInventory(Stock currentStock) {
        int newQuantity;

        newQuantity = currentStock.getQuantity()+1;
            open();
            String query = "UPDATE " +
                    DBOpenHelper.TABLE_INVENTORY +
                    " SET " +
                    DBOpenHelper.COLUMN_QUANTITY +
                    " = " +
                    newQuantity +
                    " WHERE " +
                    DBOpenHelper.COLUMN_ID +
                    " = " +
                    currentStock.getId();
            database.execSQL(query);
            close();

            currentStock.setQuantity(newQuantity);
    }
}