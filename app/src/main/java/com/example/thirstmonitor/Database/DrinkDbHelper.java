package com.example.thirstmonitor.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DrinkDbHelper  extends SQLiteAssetHelper {
    public static final String Date_TABLE_NAME = "dateTable";
    public static final String TIME_TABLE_NAME = "timeTable";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TIME_ID = "_id";
    public static final String COLUMN_WATER_NEED = "WaterNeed";
    public static final String COLUMN_WATER_DRUNK="drunkWater";
    public static final String COLUMN_WATER_DRUNK_ONCE="drunkWaterOnce";
    public static final String COLUMN_DATE="date";
    public static final String COLUMN_TIME_DATE="date";
    public static final String COLUMN_TIME="time";
    public static final String COLUMN_TYP="containerTyp";

    public static final String USER_ACCOUNT_TABLE_NAME = "userAccount";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USER_NAME = "user_name";
    public static final String COLUMN_USER_PASSWORD = "password";
    public static final String COLUMN_FULL_NAME = "full_name";



    public static final String DATABASE_NAME = "thirstmonitor.db";
    public static final int DATABASE_VERSION = 3;



    private static final String CREATE_DATE_TABLE = "create table " +Date_TABLE_NAME
            + " (" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_WATER_NEED + " integer, "
            + COLUMN_WATER_DRUNK + " integer, "
            + COLUMN_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP" +
            ");";

    private static final String CREATE_TIME_TABLE = "create table " +TIME_TABLE_NAME
            + " (" + COLUMN_TIME_ID + " integer primary key autoincrement, "
            + COLUMN_WATER_DRUNK_ONCE + " integer, "
            + COLUMN_TYP + " TEXT, "
            + COLUMN_TIME_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP , "
            + COLUMN_TIME + " DATETIME DEFAULT CURRENT_TIMESTAMP);";

    private static final String CREATE_USER_ACCOUNT_TABLE = "create table " +USER_ACCOUNT_TABLE_NAME
            + " (" + COLUMN_USER_ID + " integer primary key autoincrement, "
            + COLUMN_USER_NAME + " TEXT, "
            + COLUMN_USER_PASSWORD + " TEXT, "
            + COLUMN_FULL_NAME + " TEXT);";

    public  DrinkDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(CREATE_DATE_TABLE);
//        db.execSQL(CREATE_TIME_TABLE);
//        db.execSQL(CREATE_USER_ACCOUNT_TABLE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        Log.w( DrinkDbHelper.class.getName(), "Upgrading database from version "
//                + oldVersion + " to " + newVersion
//                + ", which will destroy all old data");
//        db.execSQL("DROP TABLE IF EXISTS " + Date_TABLE_NAME);
//        db.execSQL("DROP TABLE IF EXISTS " + TIME_TABLE_NAME);
//        db.execSQL("DROP TABLE IF EXISTS " + USER_ACCOUNT_TABLE_NAME );
//
//        onCreate(db);
//    }
}

