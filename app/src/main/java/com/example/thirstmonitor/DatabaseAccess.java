package com.example.thirstmonitor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c = null;

    private DatabaseAccess(Context context) {
        context.deleteDatabase(DatabaseHelper.DB_NAME);
        this.openHelper = new DatabaseHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.db = openHelper.getWritableDatabase();
    }

    public void close() {
        if (db != null)
            this.db.close();
    }


    public boolean insertUser(String firstName,
                              String lastName,
                              String email,
                              String phoneNumber,
                              String username,
                              String password
    ) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name", firstName);
        contentValues.put("last_name", lastName);
        contentValues.put("email", email);
        contentValues.put("phone", phoneNumber);
        contentValues.put("user_name", username);
        contentValues.put("password", password);

        long ins = db.insert("user_account", null, contentValues);

        if (ins == -1) return false;
        else return true;
    }


    //checking if credentials are valid
    public boolean authenticate(String username, String password) {
        open();
        Cursor cursor = db.rawQuery("Select * from user_account where user_name = ?" +
                        " and password= ?",
                new String[]{username, password});

        if (cursor.getCount() == 1) {
            return true;
        } else {
            return false;
        }
    }

    //checking if user exists;
    public boolean checkUser(String username) {
        open();
        Cursor cursor = db.rawQuery("Select * from user_account where username = ?",
                new String[]{username});

        if (cursor.getCount() > 0) return false;
        else return true;
    }
}
