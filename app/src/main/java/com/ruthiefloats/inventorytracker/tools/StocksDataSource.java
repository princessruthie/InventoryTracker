package com.ruthiefloats.inventorytracker.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ruthiefloats.inventorytracker.model.Stock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 7/14/16.
 */
public class StocksDataSource {
    public static final String LOG_TAG = "StocksDataSource: ";
    SQLiteOpenHelper mSQLiteOpenHelper;
    SQLiteDatabase mSQLiteDatabase;

    private static final String[] allColumns = {
            DBOpenHelper.COLUMN_PRODUCT_NAME,
            DBOpenHelper.COLUMN_QUANTITY,
            DBOpenHelper.COLUMN_PRICE,
            DBOpenHelper.COLUMN_IMAGE,
            DBOpenHelper.COLUMN_ID
    };

    public StocksDataSource(Context context) {
        mSQLiteOpenHelper = new DBOpenHelper(context);
    }

    public void open() {
         /* getWriteableDatabase calls the onCreate
        and creates the table
         */
        mSQLiteDatabase = mSQLiteOpenHelper.getWritableDatabase();
    }

    public void close() {
        mSQLiteDatabase.close();
    }

    public Stock create(Stock stock) {
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.COLUMN_QUANTITY, stock.getQuantity());
        values.put(DBOpenHelper.COLUMN_PRODUCT_NAME, stock.getName());
        values.put(DBOpenHelper.COLUMN_PRICE, stock.getPrice());
        values.put(DBOpenHelper.COLUMN_IMAGE, stock.getImageUri());

        long insertid = mSQLiteDatabase.insert(DBOpenHelper.TABLE_INVENTORY, null, values);
        stock.setId(insertid);

        return stock;
    }

    public List<Stock> getAllStocks() {
        List<Stock> stocks = new ArrayList<Stock>();

        Cursor cursor = mSQLiteDatabase.query(DBOpenHelper.TABLE_INVENTORY,
                allColumns, null, null, null, null, null);
        Log.i(LOG_TAG, "Number of rows returned: " + cursor.getCount());
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
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
        Log.i(LOG_TAG, "All stocks returned");
        cursor.close();
        return stocks;
    }

    public void deleteRecord(Stock currentStock) {
        open();
        String whereClause = DBOpenHelper.COLUMN_ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(currentStock.getId())};
        mSQLiteDatabase.delete(DBOpenHelper.TABLE_INVENTORY, whereClause, whereArgs);
        close();
    }

    public boolean decrementInventory(Stock currentStock) {
        int newQuantity;
        newQuantity = currentStock.getQuantity() - 1;
        if (newQuantity > -1) {
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
            mSQLiteDatabase.execSQL(query);
            close();

            currentStock.setQuantity(newQuantity);
            return true;
        } else {
            return false;
        }
    }

    public void incrementInventory(Stock currentStock) {
        int newQuantity = currentStock.getQuantity() + 1;
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
        mSQLiteDatabase.execSQL(query);
        close();
        currentStock.setQuantity(newQuantity);
    }
}