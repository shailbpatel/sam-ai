package com.example.chatgptapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "chatGPTAudit.db";
    private static final int DATABASE_VERSION = 1;

    // Tables and column names
    public static final String TABLE_PROMPT = "AuditPrompt";
    public static final String COLUMN_SEQUENCE = "SequenceNumber";
    public static final String COLUMN_DATE_TIME = "DateTime";
    public static final String COLUMN_PROMPT = "Prompt";

    public static final String TABLE_RESPONSE = "Responses";
    public static final String COLUMN_RESPONSE = "Response";

    // SQL statement to create the tables
    private static final String DATABASE_CREATE_PROMPT =
            "CREATE TABLE " + TABLE_PROMPT + "(" +
                    COLUMN_SEQUENCE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_DATE_TIME + " TEXT NOT NULL, " +
                    COLUMN_PROMPT + " TEXT);";

    private static final String DATABASE_CREATE_RESPONSE =
            "CREATE TABLE " + TABLE_RESPONSE + "(" +
                    COLUMN_SEQUENCE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_DATE_TIME + " TEXT NOT NULL, " +
                    COLUMN_RESPONSE + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_PROMPT);
        db.execSQL(DATABASE_CREATE_RESPONSE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROMPT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESPONSE);
        onCreate(db);
    }
}

