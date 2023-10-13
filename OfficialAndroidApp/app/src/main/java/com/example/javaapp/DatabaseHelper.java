package com.example.javaapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_CLIENT = "Client";
    public static final String COLUMN_CLIENT_ID = "clientID";
    public static final String COLUMN_CLIENT_NAME = "clientName";
    public static final String COLUMN_CLIENT_EMAIL = "clientEmail";
    public static final String COLUMN_CLIENT_PHONE = "clientPhone";
    public static final String TABLE_DANCECLASS = "DanceClass";
    public static final String COLUMN_DANCECLASS_NAME = "className";
    public static final String COLUMN_DANCECLASS_DATE = "date";
    public static final String COLUMN_DANCECLASS_LUMPSUMCOST = "lumpSumCost";
    public static final String COLUMN_DANCECLASS_BIANNUALCOST = "biAnnualCost";
    public static final String COLUMN_DANCECLASS_MONTHLYCOST = "monthlyCost";
    public static final String TABLE_INVOICE = "Invoice";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "invoice.db", null, 1);
    }

    // Called first time a database is accessed.
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creates the client table
        db.execSQL("CREATE TABLE " +
                TABLE_CLIENT + "(" +
                COLUMN_CLIENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CLIENT_NAME + " TEXT, " +
                COLUMN_CLIENT_EMAIL + " TEXT, " +
                COLUMN_CLIENT_PHONE + " INTEGER)"
        );
        // Creates the DanceClass Table
        db.execSQL("CREATE TABLE " +
                TABLE_DANCECLASS + " (" +
                COLUMN_DANCECLASS_NAME + " TEXT, " +
                COLUMN_DANCECLASS_DATE + " INTEGER, " +
                COLUMN_DANCECLASS_LUMPSUMCOST + " REAL, " +
                COLUMN_DANCECLASS_BIANNUALCOST + " REAL, " +
                COLUMN_DANCECLASS_MONTHLYCOST + " REAL, " +
                "PRIMARY KEY(" + COLUMN_DANCECLASS_NAME + ", " + COLUMN_DANCECLASS_DATE + "))"
        );
        // Creates the Invoice Table
        db.execSQL("CREATE TABLE " +
                TABLE_INVOICE + " (" +
                COLUMN_CLIENT_ID + " INTEGER NOT NULL, " +
                COLUMN_DANCECLASS_NAME + " TEXT NOT NULL, " +
                COLUMN_DANCECLASS_DATE + " TEXT NOT NULL, " +
                "PRIMARY KEY(" + COLUMN_CLIENT_ID + ", " + COLUMN_DANCECLASS_NAME + ", " + COLUMN_DANCECLASS_DATE + ")," +
                "FOREIGN KEY(" + COLUMN_CLIENT_ID + ") REFERENCES " + TABLE_CLIENT + "(" + COLUMN_CLIENT_ID + "), " +
                "FOREIGN KEY(" + COLUMN_DANCECLASS_NAME + ") REFERENCES " + TABLE_DANCECLASS + "(" + COLUMN_DANCECLASS_NAME + "), " +
                "FOREIGN KEY(" + COLUMN_DANCECLASS_DATE + ") REFERENCES " + TABLE_DANCECLASS + "(" + COLUMN_DANCECLASS_DATE + "));"
        );
    }

    // Called if the database version number changes.
    // Prevents previous users apps from breaking when changing design.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
