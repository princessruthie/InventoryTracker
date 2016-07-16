package com.ruthiefloats.inventorytracker.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created on 7/14/16.
 */
public class DBOpenHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "inventory.db";
    private static final int DATABASE_VERSION = 1;


    private static final String LOGTAG = "DBOpenHelper: ";

    public static final String TABLE_INVENTORY = "inventory";
    public static final String COLUMN_ID = "stockId";
    public static final String COLUMN_PRODUCT_NAME = "name";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_IMAGE = "image";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_INVENTORY + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_PRODUCT_NAME + " TEXT, " +
                    COLUMN_QUANTITY + " NUMERIC, " +
                    COLUMN_IMAGE + " TEXT, " +
                    COLUMN_PRICE + " NUMERIC " +
                    ")";

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i(LOGTAG, "Helper constructed");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
        Log.i(LOGTAG, "Database just instantiated");
        Log.i(LOGTAG, "Table has been created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY);

        onCreate(sqLiteDatabase);
        Log.i(LOGTAG, "Database Upgraded From " + oldVersion + " to " + newVersion);
    }
}