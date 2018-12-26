package com.example.vanminh.appmobile.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper {
    public static final String DBNAME = "phone.sql";
    public static final int VERSION = 1;

    public static final String TABLE_NAME = "Phone";
    public static final String ID = "id";
    public static final String PHONE_NAME = "name";
    public static final String PRICE = "price";
    public static final String PRO_ID = "pro_id";

    public static final String TABLE_NAME_1 = "Manufacturer";
    public static final String ID_SX = "id";
    public static final String NAME_SX = "name";

    public static final String CREATE_TABLE_PHONE = "CREATE TABLE " + TABLE_NAME + " ("+
            ID + " TEXT PRIMARY KEY, " +
            PHONE_NAME + " TEXT, " +
            PRICE + " TEXT, " +
            PRO_ID + " TEXT)";

    public static final String CREATE_TABLE_SX = "CREATE TABLE " + TABLE_NAME_1 + " ("+
            ID_SX + " INTEGER PRIMARY KEY, " +
            NAME_SX + " TEXT)";
    public DBManager(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PHONE);
        db.execSQL(CREATE_TABLE_SX);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
