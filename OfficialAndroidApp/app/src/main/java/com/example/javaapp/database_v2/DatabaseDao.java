package com.example.javaapp.database_v2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseDao extends SQLiteOpenHelper {

    public DatabaseDao(@Nullable Context context) {
        super(context, "Dance.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATES A CLIENT TABLE
        db.execSQL("CREATE TABLE CLIENT(EMAIL TEXT PRIMARY KEY NOT NULL, FIRSTNAME TEXT NOT NULL, LASTNAME TEXT NOT NULL, PHONENUMBER BIGINT NOT NULL, BALANCE REAL NOT NULL)");

        // CREATES A CLASS TABLE
        db.execSQL("CREATE TABLE CLASS(CLASSNAME TEXT NOT NULL, YEAR INTEGER NOT NULL, COST DECIMAL(10,2) NOT NULL, CAPACITY INTEGER NOT NULL, ENROLLED INTEGER NOT NULL, PRIMARY KEY(CLASSNAME, YEAR))");

        // CREATES A SIGNUP TABLE
        db.execSQL("CREATE TABLE SIGNEDUP(EMAIL TEXT, CLASSNAME TEXT, YEAR INTEGER, ISPAID BOOLEAN, PRIMARY KEY(EMAIL, CLASSNAME, YEAR))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // ########## CLIENT QUERIES ###########

    // Adds a single client to the database
    public boolean addOneClient(ClientModel clientModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("EMAIL", clientModel.getEmail());
        cv.put("FIRSTNAME", clientModel.getFirstName());
        cv.put("LASTNAME", clientModel.getLastName());
        cv.put("PHONENUMBER", clientModel.getPhoneNumber());

        long insert = db.insert("CLIENT", null, cv);
        db.close();
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    // Deletes a single client from the database
    public boolean deleteOneClient(ClientModel clientModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM CLIENT WHERE EMAIL = " + clientModel.getEmail();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToNext()) {
            return true;
        }
        else {
            return false;
        }
    }

    // ########## CLASS QUERIES ###########

    // Adds a single dance class to the database
    public boolean addOneClass(ClassModel classModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("CLASSNAME", classModel.getClassName());
        cv.put("YEAR", classModel.getYear());
        cv.put("COST", classModel.getCost());
        cv.put("CAPACITY", classModel.getCapacity());
        cv.put("ENROLLED", classModel.getEnrolled());

        long insert = db.insert("CLASS", null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    // Deletes a single class from the database
    public boolean deleteOneClass(ClassModel classModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM CLASS WHERE CLASSNAME = " + classModel.getClassName() + " AND YEAR = " + classModel.getYear();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToNext()) {
            return true;
        }
        else {
            return false;
        }
    }

    // ########## CLASS QUERIES ###########

    // Adds a single dance class to the database
    public boolean addOneSignedUp(SignedUpModel signedUpModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("EMAIL", signedUpModel.getEmail());
        cv.put("CLASSNAME", signedUpModel.getClassName());
        cv.put("YEAR", signedUpModel.getYear());
        cv.put("ISPAID", signedUpModel.getIsPaid());

        long insert = db.insert("SIGNEDUP", null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    // Deletes a single signed up from the database
    public boolean deleteSignedUp(SignedUpModel signedUpModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM SIGNEDUP WHERE EMAIL = " + signedUpModel.getEmail() + " AND CLASSNAME = " + signedUpModel.getClassName() + " AND YEAR = " + signedUpModel.getYear();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToNext()) {
            return true;
        }
        else {
            return false;
        }
    }
}
