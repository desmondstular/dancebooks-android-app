package com.example.javaapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Pair;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    // ##### CONSTANTS START HERE #####
    public static final String TABLE_CLIENT = "Client";
    public static final String COLUMN_CLIENT_ID = "clientID";
    public static final String COLUMN_CLIENT_FIRSTNAME = "clientFirstName";
    public static final String COLUMN_CLIENT_LASTNAME = "clientLastName";
    public static final String COLUMN_CLIENT_EMAIL = "clientEmail";
    public static final String COLUMN_CLIENT_PHONE = "clientPhone";
    public static final String TABLE_DANCECLASS = "DanceClass";
    public static final String COLUMN_DANCECLASS_NAME = "className";
    public static final String COLUMN_DANCECLASS_YEAR = "classYear";
    public static final String COLUMN_DANCECLASS_LUMPSUMCOST = "classLumpSumCost";
    public static final String COLUMN_DANCECLASS_BIANNUALCOST = "classBiAnnualCost";
    public static final String COLUMN_DANCECLASS_MONTHLYCOST = "classMonthlyCost";
    public static final String TABLE_INVOICE = "Invoice";
    // ##### CONSTANTS END HERE #####

    public DatabaseHelper(@Nullable Context context) {
        super(context, "invoice.db", null, 1);
    }

    // Called first time a database is accessed.
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creates the client table
        db.execSQL("CREATE TABLE " + TABLE_CLIENT + "(" +
                COLUMN_CLIENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CLIENT_FIRSTNAME + " TEXT, " +
                COLUMN_CLIENT_LASTNAME + " TEXT, " +
                COLUMN_CLIENT_EMAIL + " TEXT, " +
                COLUMN_CLIENT_PHONE + " INTEGER)"
        );
        // Creates the DanceClass Table
        db.execSQL("CREATE TABLE " + TABLE_DANCECLASS + " (" +
                COLUMN_DANCECLASS_NAME + " TEXT NOT NULL, " +
                COLUMN_DANCECLASS_YEAR + " INTEGER NOT NULL, " +
                COLUMN_DANCECLASS_LUMPSUMCOST + " REAL, " +
                COLUMN_DANCECLASS_BIANNUALCOST + " REAL, " +
                COLUMN_DANCECLASS_MONTHLYCOST + " REAL, " +
                "PRIMARY KEY(" + COLUMN_DANCECLASS_NAME + ", " + COLUMN_DANCECLASS_YEAR + "))"
        );
        // Creates the Invoice Table
        db.execSQL("CREATE TABLE " + TABLE_INVOICE + " (" +
                COLUMN_CLIENT_ID + " INTEGER NOT NULL, " +
                COLUMN_DANCECLASS_NAME + " TEXT NOT NULL, " +
                COLUMN_DANCECLASS_YEAR + " TEXT NOT NULL, " +
                "PRIMARY KEY(" + COLUMN_CLIENT_ID + ", " + COLUMN_DANCECLASS_NAME + ", " + COLUMN_DANCECLASS_YEAR + ")," +
                "FOREIGN KEY(" + COLUMN_CLIENT_ID + ") REFERENCES " + TABLE_CLIENT + "(" + COLUMN_CLIENT_ID + "), " +
                "FOREIGN KEY(" + COLUMN_DANCECLASS_NAME + ") REFERENCES " + TABLE_DANCECLASS + "(" + COLUMN_DANCECLASS_NAME + ") ON DELETE CASCADE, " +
                "FOREIGN KEY(" + COLUMN_DANCECLASS_YEAR + ") REFERENCES " + TABLE_DANCECLASS + "(" + COLUMN_DANCECLASS_YEAR + ") ON DELETE CASCADE);"
        );
    }

    // Called if the database version number changes.
    // Prevents previous users apps from breaking when changing design.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // ##### CLIENT ENTITY RELATED QUERIES #####
    public boolean addOneClient(ClientModel clientModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CLIENT_FIRSTNAME, clientModel.getClientFirstName());
        cv.put(COLUMN_CLIENT_LASTNAME, clientModel.getClientLastName());
        cv.put(COLUMN_CLIENT_EMAIL, clientModel.getClientEmail());
        cv.put(COLUMN_CLIENT_PHONE, clientModel.getClientPhone());

        long insert = db.insert(TABLE_CLIENT, null, cv);
        db.close();
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteOneClient(ClientModel clientModel) {
        // finds ClientModel in data base. If found, deletes it and returns true.
        // If not found, returns, false

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + TABLE_CLIENT +  " WHERE " + COLUMN_CLIENT_ID + " = " + clientModel.getClientID();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToNext()) {
            return true;
        }
        else {
            return false;
        }
    }

    public ClientModel getOneClientByName(String clientFirstName, String clientLastName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + TABLE_CLIENT + " WHERE " + COLUMN_CLIENT_FIRSTNAME + " = " + clientFirstName + " and " + COLUMN_CLIENT_LASTNAME + " = " + clientLastName;
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return new ClientModel(cursor.getInt(0),    //ID number
                    cursor.getString(1),                //First Name
                    cursor.getString(2),                //Last Name
                    cursor.getString(3),                //Email
                    cursor.getInt(4));                  //Phone number
        }
        else {
            return null;
        }
    }

    public List<String> getAllClientNames() {
        List<String> returnList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT " + COLUMN_CLIENT_FIRSTNAME + ", " + COLUMN_CLIENT_LASTNAME + " FROM " + TABLE_CLIENT;
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                String clientFirstName = cursor.getString(0);
                String clientLastName = cursor.getString(1);
                returnList.add(clientFirstName + " " + clientFirstName);
            } while (cursor.moveToNext());
        } else {
            // Failure. Do not anything to the list
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<ClientModel> getAllClients() {
        List<ClientModel> returnList = new ArrayList<>();

        // get client data from the database
        String queryString = "SELECT * FROM " + TABLE_CLIENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        // returns true if there are results to query
        if (cursor.moveToFirst()) {
            // Loops through cursor (query results) and adds to new client object
            do {
                int clientID = cursor.getInt(0);
                String clientFirstName = cursor.getString(1);
                String clientLastName = cursor.getString(2);
                String clientEmail = cursor.getString(3);
                int clientPhone = cursor.getInt(4);
                ClientModel newClient = new ClientModel(
                        clientID,
                        clientFirstName,
                        clientLastName,
                        clientEmail,
                        clientPhone);
                returnList.add(newClient);
            } while (cursor.moveToNext());
;        } else {
            // failure. do not add anything to the list.
        }
        cursor.close();
        db.close();
        return returnList;
    }

    // ##### DANCE CLASS RELATED QUERIES #####
    public boolean addOneDanceClass(DanceClassModel danceClassModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DANCECLASS_NAME, danceClassModel.getClassName());
        cv.put(COLUMN_DANCECLASS_YEAR, danceClassModel.getClassYear());
        cv.put(COLUMN_DANCECLASS_LUMPSUMCOST, danceClassModel.getClassLumpSumCost());
        cv.put(COLUMN_DANCECLASS_BIANNUALCOST, danceClassModel.getClassBiAnnualCost());
        cv.put(COLUMN_DANCECLASS_MONTHLYCOST, danceClassModel.getClassMonthlyCost());

        long insert = db.insert(TABLE_DANCECLASS, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteOneDanceClass(DanceClassModel danceClassModel) {
        // finds DanceClassModel in data base. If found, deletes it and returns true.
        // If not found, returns, false

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + TABLE_DANCECLASS +
                " WHERE " + COLUMN_DANCECLASS_NAME + " = " + danceClassModel.getClassName() +
                " and " + COLUMN_DANCECLASS_YEAR + " = " + danceClassModel.getClassYear();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToNext()) {
            return true;
        }
        else {
            return false;
        }
    }


    public DanceClassModel getOneDanceClass(String danceClassName, int danceClassYear) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + TABLE_DANCECLASS + " WHERE " + COLUMN_DANCECLASS_NAME + " = " + danceClassName + " and " + COLUMN_DANCECLASS_YEAR + " = " + danceClassYear;
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return new DanceClassModel(cursor.getString(0),
                    cursor.getInt(1),
                    cursor.getFloat(2),
                    cursor.getFloat(3),
                    cursor.getFloat(4));
        } else {
            return null;
        }
    }
    public List<DanceClassModel> getAllDanceClasses() {
        List<DanceClassModel> returnList = new ArrayList<>();

        // get DanceClass data from the database
        String queryString = "SELECT * FROM " + TABLE_DANCECLASS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        // returns true if there are results to query
        if (cursor.moveToFirst()) {
            // Loops through cursor (query results) and adds to new DanceClass object
            do {
                String className = cursor.getString(0);
                int classYear = cursor.getInt(1);
                float classLumpSumCost = cursor.getFloat(2);
                float classBiAnnualCost = cursor.getFloat(3);
                float classMonthlyCost = cursor.getFloat(4);
                DanceClassModel newCLass = new DanceClassModel(className, classYear, classLumpSumCost, classBiAnnualCost, classMonthlyCost);
                returnList.add(newCLass);
            } while (cursor.moveToNext());
        } else {
            // failure. do not add anything to the list.
        }
        cursor.close();
        db.close();
        return returnList;
    }

    // ##### INVOICE RELATED QUERIES #####
    public boolean addOneInvoice(InvoiceModel invoiceModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CLIENT_ID, invoiceModel.getClientID());
        cv.put(COLUMN_DANCECLASS_NAME, invoiceModel.getClassName());
        cv.put(COLUMN_DANCECLASS_YEAR, invoiceModel.getClassYear());

        long insert = db.insert(TABLE_INVOICE, null,cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteOneInvoice(InvoiceModel invoiceModel) {
        // finds InvoiceModel in data base. If found, deletes it and returns true.
        // If not found, returns, false

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + TABLE_INVOICE +
                " WHERE " + COLUMN_CLIENT_ID + " = " + invoiceModel.getClientID() +
                " and " + COLUMN_DANCECLASS_NAME + " = " + invoiceModel.getClassName() +
                " and " + COLUMN_DANCECLASS_YEAR + " = " + invoiceModel.getClassYear();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToNext()) {
            return true;
        }
        else {
            return false;
        }
    }

    public List<InvoiceModel> getAllInvoices() {
        List<InvoiceModel> returnList = new ArrayList<>();

        // get Invoice data from the database
        String queryString = "SELECT * FROM " + TABLE_INVOICE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        // returns true if there are results to query
        if (cursor.moveToFirst()) {
            // Loops through cursor (query results) and adds to new DanceClass object
            do {
                int clientID = cursor.getInt(0);
                String className = cursor.getString(1);
                int classYear = cursor.getInt(2);
                //ClassModel newInvoice = new Model(clientID, className, classYear);
            } while (cursor.moveToNext());
            ;        } else {
            // failure. do not add anything to the list.
        }
        cursor.close();
        db.close();
        return returnList;
    }
}
