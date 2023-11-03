package com.example.javaapp.database_v2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DatabaseDao extends SQLiteOpenHelper {

    public DatabaseDao(@Nullable Context context) {
        super(context, "Dance.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATES A CLIENT TABLE
        db.execSQL("CREATE TABLE CLIENT(EMAIL TEXT PRIMARY KEY NOT NULL, FIRSTNAME TEXT NOT NULL, LASTNAME TEXT NOT NULL, PHONENUMBER TEXT NOT NULL, BALANCE REAL NOT NULL)");

        // CREATES A CLASS TABLE
        db.execSQL("CREATE TABLE CLASS(CLASSNAME TEXT NOT NULL, YEAR INTEGER NOT NULL, COST DECIMAL(10,2) NOT NULL, CAPACITY INTEGER NOT NULL, ENROLLED INTEGER NOT NULL, PRIMARY KEY(CLASSNAME, YEAR))");

        // CREATES A SIGNUP TABLE
        db.execSQL("CREATE TABLE SIGNEDUP(EMAIL TEXT, CLASSNAME TEXT, YEAR INTEGER, ISPAID INTEGER, PRIMARY KEY(EMAIL, CLASSNAME, YEAR))");
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
        cv.put("BALANCE", clientModel.getBalance());

        long insert = db.insert("CLIENT", null, cv);
        db.close();
        if (insert == -1) {
            return false;
        }
        else {return true;}
    }

    // Deletes a single client from the database
    public boolean deleteOneClient(ClientModel clientModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM CLIENT WHERE EMAIL = " + clientModel.getEmail();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToNext()) {
            return true;
        }
        else {return false;}
    }

    // Searches a Client by its Primary Keys and returns it as a Client Model.
    public ClientModel getOneClientByPrimaryKey(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM CLIENT WHERE EMAIL = " + "'"+ email+ "'";
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return new ClientModel(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getFloat(4));
        }
        else {return null;}
    }

    // Returns an array list of all Clients in the DB as ClientModels
    public List<ClientModel> getAllClients() {
        List<ClientModel> returnList = new ArrayList<>();

        // get client data from the database
        String queryString = "SELECT * FROM CLIENT";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        // returns true if there are results to query
        if (cursor.moveToFirst()) {
            // Loops through cursor (query results) and adds to new client object
            do {
                String email = cursor.getString(0);
                String firstName = cursor.getString(1);
                String lastName = cursor.getString(2);
                String phoneNumber = cursor.getString(3);
                float balance = cursor.getFloat(4);
                ClientModel newClient = new ClientModel(
                        email,
                        firstName,
                        lastName,
                        phoneNumber,
                        balance);
                returnList.add(newClient);
            } while (cursor.moveToNext());
        }
        else {}
        cursor.close();
        db.close();
        return returnList;
    }

    // Gets client by first name and/or last name. Enter empty string
    // for parameters not searching for.
    public List<ClientModel> getAllClientByFirstNameAndOrLastName(String firstName, String lastName) {
        List<ClientModel> returnList = new ArrayList<>();
        String first, last;


        if (Objects.equals(firstName, "")) {
            first = "";
        } else {
            first = " WHERE FIRSTNAME = '" + firstName + "'";
        }

        if (Objects.equals(lastName, "")) {
            last = "";
        } else {
            last = " WHERE LASTNAME = '" + lastName + "'";
        }

        // get client data from the database
        String queryString = "SELECT * FROM CLIENT" + first +
                " INTERSECT " +
                "SELECT * FROM CLIENT" + last;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        // returns true if there are results to query
        if (cursor.moveToFirst()) {
            // Loops through cursor (query results) and adds to new client object
            do {
                String email = cursor.getString(0);
                String fName = cursor.getString(1);
                String lName = cursor.getString(2);
                String phoneNumber = cursor.getString(3);
                float balance = cursor.getFloat(4);
                ClientModel newClient = new ClientModel(
                        email,
                        fName,
                        lName,
                        phoneNumber,
                        balance);
                returnList.add(newClient);
            } while (cursor.moveToNext());
        }
        else {}
        cursor.close();
        db.close();
        return returnList;
    }

    // Returns a list of Clients with a balance greater than zero as Client Models
    public List<ClientModel> getAllClientWithBalanceGreaterThanZero() {
        List<ClientModel> returnList = new ArrayList<>();

        // get client data from the database
        String queryString = "SELECT * FROM CLIENT WHERE BALANCE > 0";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        // returns true if there are results to query
        if (cursor.moveToFirst()) {
            // Loops through cursor (query results) and adds to new client object
            do {
                String email = cursor.getString(0);
                String firstName = cursor.getString(1);
                String lastName = cursor.getString(2);
                String phoneNumber = cursor.getString(3);
                float balance = cursor.getFloat(4);
                ClientModel newClient = new ClientModel(
                        email,
                        firstName,
                        lastName,
                        phoneNumber,
                        balance);
                returnList.add(newClient);
            } while (cursor.moveToNext());
        }
        else {}
        cursor.close();
        db.close();
        return returnList;
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
        }
        else {return true;}
    }

    // Searches a Class by its Primary Keys and returns as a ClassModel object.
    public ClassModel getOneClassByPrimaryKey(String className, int year) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM CLASS WHERE CLASSNAME = '" + className + "' and YEAR = '" + year+"'";
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return new ClassModel(cursor.getString(0),
                    cursor.getInt(1),
                    cursor.getFloat(2),
                    cursor.getInt(3),
                    cursor.getInt(4));
        } else {
            return null;
        }
    }
    // show all classes that have enrollment < capacity
    public List<ClassModel> getAllAvailableClasses(){
        List<ClassModel> returnList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM CLASS WHERE ENROLLED < CAPACITY";
        Cursor cursor = db.rawQuery(queryString, null);
        // returns true if there are results to query
        if (cursor.moveToFirst()) {
            // Loops through cursor (query results) and adds to new class list object
            do {
                String className = cursor.getString(0);
                int year = cursor.getInt(1);
                float cost = cursor.getFloat(2);
                int capacity = cursor.getInt(3);
                int enrolled = cursor.getInt(4);
                ClassModel newClass = new ClassModel(
                        className,
                        year,
                        cost,
                        capacity,
                        enrolled);
                returnList.add(newClass);
            } while (cursor.moveToNext());
        }
        else {}
        cursor.close();
        db.close();
        return returnList;
    }

    // Deletes a single class from the database
    public boolean deleteOneClass(ClassModel classModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM CLASS WHERE CLASSNAME = " + classModel.getClassName() + " AND YEAR = " + classModel.getYear();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToNext()) {
            return true;
        }
        else {return false;}
    }

    // Returns an array list of all Classes in the DB as ClassModels
    public List<ClassModel> getAllClasses() {
        List<ClassModel> returnList = new ArrayList<>();

        // get client data from the database
        String queryString = "SELECT * FROM CLASS";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        // returns true if there are results to query
        if (cursor.moveToFirst()) {
            // Loops through cursor (query results) and adds to new class list object
            do {
                String className = cursor.getString(0);
                int year = cursor.getInt(1);
                float cost = cursor.getFloat(2);
                int capacity = cursor.getInt(3);
                int enrolled = cursor.getInt(4);
                ClassModel newClass = new ClassModel(
                        className,
                        year,
                        cost,
                        capacity,
                        enrolled);
                returnList.add(newClass);
            } while (cursor.moveToNext());
            }
        else {}
        cursor.close();
        db.close();
        return returnList;
    }

    // Gets classes by className and/or year. Enter empty string
    // for className or null for year if you wish to not use either one of
    // the filters.
    public List<ClientModel> getAllClassesByClassNameAndOrYear(String className, Integer year) {
        List<ClientModel> returnList = new ArrayList<>();
        String cName, classYear;

        if (className.isEmpty()) {
            cName = "";
        } else {
            cName = " WHERE CLASSNAME = '" + className + "'";
        }

        if (year == null) {
            classYear = "";
        } else {
            classYear = " WHERE YEAR = " + year.toString();
        }

        // get client data from the database
        String queryString = "SELECT * FROM CLASS" + cName +
                " INTERSECT " +
                "SELECT * FROM CLIENT" + classYear;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        // returns true if there are results to query
        if (cursor.moveToFirst()) {
            // Loops through cursor (query results) and adds to new client object
            do {
                String email = cursor.getString(0);
                String fName = cursor.getString(1);
                String lName = cursor.getString(2);
                String phoneNumber = cursor.getString(3);
                float balance = cursor.getFloat(4);
                ClientModel newClient = new ClientModel(
                        email,
                        fName,
                        lName,
                        phoneNumber,
                        balance);
                returnList.add(newClient);
            } while (cursor.moveToNext());
            ;        } else {
            // failure. do not add anything to the list.
        }
        cursor.close();
        db.close();
        return returnList;
    }


    // ########## SIGNED UP QUERIES ###########

    // Adds a single Sign Up to the database
    public boolean addOneSignedUp(SignedUpModel signedUpModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ClassModel classModel = this.getOneClassByPrimaryKey(signedUpModel.getClassName(), signedUpModel.getYear());
        ClientModel clientModel = this.getOneClientByPrimaryKey(signedUpModel.getEmail());
        // Class is already full, cannot add anymore Clients
        if (classModel.getEnrolled() + 1 > classModel.getCapacity()) {
            return false;
        }

        ContentValues cv = new ContentValues();
        cv.put("EMAIL", signedUpModel.getEmail());
        cv.put("CLASSNAME", signedUpModel.getClassName());
        cv.put("YEAR", signedUpModel.getYear());
        cv.put("ISPAID", signedUpModel.getIsPaid());

        long insert = db.insert("SIGNEDUP", null, cv);
        if (insert == -1) {
            return false;
        } else {
            ContentValues cv2 = new ContentValues();
            cv2.put("CLASSNAME", classModel.getClassName());
            cv2.put("YEAR", classModel.getYear());
            cv2.put("COST", classModel.getCost());
            cv2.put("CAPACITY", classModel.getCapacity());
            cv2.put("ENROLLED", classModel.getEnrolled() + 1);
            int updated = db.update("CLASS", cv2, "CLASSNAME=? AND YEAR=?", new String[]{classModel.getClassName(), Integer.toString(classModel.getYear())});

            if (signedUpModel.getIsPaid() == 1) {
                updateClientBalance(clientModel, classModel.getCost());
            }
            return true;
        }
    }

    // Deletes a single signed up from the database
    public boolean deleteSignedUp(SignedUpModel signedUpModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ClassModel classModel = this.getOneClassByPrimaryKey(signedUpModel.getClassName(), signedUpModel.getYear());
        ClientModel clientModel = this.getOneClientByPrimaryKey(signedUpModel.getEmail());
        String queryString = "DELETE FROM SIGNEDUP WHERE EMAIL = " + signedUpModel.getEmail() + " AND CLASSNAME = " + signedUpModel.getClassName() + " AND YEAR = " + signedUpModel.getYear();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToNext()) {
            ContentValues cv = new ContentValues();
            cv.put("CLASSNAME", classModel.getClassName());
            cv.put("YEAR", classModel.getYear());
            cv.put("COST", classModel.getCost());
            cv.put("CAPACITY", classModel.getCapacity());
            cv.put("ENROLLED", classModel.getEnrolled() - 1);
            int updated = db.update("CLASS", cv, "CLASSNAME=? AND YEAR=?", new String[]{classModel.getClassName(), Integer.toString(classModel.getYear())});

            if (signedUpModel.getIsPaid() == 1) {
                updateClientBalance(clientModel, -1 * classModel.getCost());
            }
            return true;
        }
        else {return false;}
    }

    // Updates the client balance by a specific amount passed as a parameter.
    public boolean updateClientBalance(ClientModel clientModel, float balance) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("EMAIL", clientModel.getEmail());
        cv.put("FIRSTNAME", clientModel.getFirstName());
        cv.put("LASTNAME", clientModel.getLastName());
        cv.put("PHONENUMBER", clientModel.getPhoneNumber());
        cv.put("BALANCE", clientModel.getBalance() + balance);
        int updated = db.update("CLIENT", cv, "EMAIL=?", new String[]{clientModel.getEmail()});

        if (updated == 1) {
            return true;
        }
        else {return false;}
    }

    // Searches a Signed Up entity by Primary Keys and returns it is as Signed Up Model.
    public SignedUpModel getOneSignedUpByPrimaryKey(String email, String className, int year) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM CLIENT WHERE EMAIL = " + email + " AND CLASSNAME = " + className + " AND YEAR = " + year;
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return new SignedUpModel(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3));
        }
        else {return null;}
    }

    // Returns an array list of all Sign Ups in the DB as SignedUpModels
    public List<SignedUpModel> getAllSignedUps() {
        List<SignedUpModel> returnList = new ArrayList<>();

        // get client data from the database
        String queryString = "SELECT * FROM SIGNEDUP";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        // returns true if there are results to query
        if (cursor.moveToFirst()) {
            // Loops through cursor (query results) and adds to new client object
            do {
                String email = cursor.getString(0);
                String className = cursor.getString(1);
                int year = cursor.getInt(2);
                int isPaid = cursor.getInt(3);
                SignedUpModel newSignedUp = new SignedUpModel(
                        email,
                        className,
                        year,
                        isPaid);
                returnList.add(newSignedUp);
            } while (cursor.moveToNext());
            }
        else {}
        cursor.close();
        db.close();
        return returnList;
    }
}
