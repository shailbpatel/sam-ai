package com.example.chatgptapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DataManager {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private Context context;

    public DataManager(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertPrompt(String prompt) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_DATE_TIME, System.currentTimeMillis());
        values.put(DatabaseHelper.COLUMN_PROMPT, prompt);
        return database.insert(DatabaseHelper.TABLE_PROMPT, null, values);
    }

    public long insertResponse(String response) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_DATE_TIME, System.currentTimeMillis());
        values.put(DatabaseHelper.COLUMN_RESPONSE, response);
        return database.insert(DatabaseHelper.TABLE_RESPONSE, null, values);
    }
}

