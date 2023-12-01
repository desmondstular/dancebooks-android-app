package com.example.javaapp.database_v2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.javaapp.ClassSignUp;

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
        db.execSQL("CREATE TABLE SIGNEDUP(EMAIL TEXT, CLASSNAME TEXT, YEAR INTEGER, ISPAID INTEGER, INVOICEID INTEGER, PRIMARY KEY(EMAIL, CLASSNAME, YEAR))");

        // CREATES A INVOICE TABLE
        db.execSQL("CREATE TABLE INVOICE(INVOICEID INTEGER PRIMARY KEY AUTOINCREMENT, EMAIL TEXT NOT NULL, TOTALCOST DECIMAL(10,2) NOT NULL, ISPAID INTEGER NOT NULL)");
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
    public List<ClassModel> getAllClassesByClassNameAndOrYear(String className, Integer year) {
        List<ClassModel> returnList = new ArrayList<>();
        String cName, cYear;

        if (className.isEmpty()) {
            cName = "";
        } else {
            cName = " WHERE CLASSNAME = '" + className + "'";
        }

        if (year == null) {
            cYear = "";
        } else {
            cYear = " WHERE YEAR = '" + year.toString() + "'";
        }

        // get client data from the database
        String queryString = "SELECT * FROM CLASS" + cName +
                " INTERSECT " +
                "SELECT * FROM CLASS" + cYear;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        // returns true if there are results to query
        if (cursor.moveToFirst()) {
            // Loops through cursor (query results) and adds to new client object
            do {
                String name = cursor.getString(0);
                int classYear = cursor.getInt(1);
                float cost = cursor.getFloat(2);
                int capacity = cursor.getInt(3);
                int enrolled = cursor.getInt(4);
                ClassModel newClass = new ClassModel(
                        name,
                        classYear,
                        cost,
                        capacity,
                        enrolled);
                returnList.add(newClass);
            } while (cursor.moveToNext());
        } else {
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
        cv.put("INVOICEID", signedUpModel.getInvoiceID());

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

            /*
            if (signedUpModel.getIsPaid() == 0) {
                updateClientBalance(clientModel, classModel.getCost());
            }
             */
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
                updateClientBalanceBySpecificAmount(clientModel, -1 * classModel.getCost());
            }
            return true;
        }
        else {return false;}
    }

    // Updates the client balance by a specific amount passed as a parameter.
    public boolean updateClientBalanceBySpecificAmount(ClientModel clientModel, float balance) {
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
                    cursor.getInt(3),
                    cursor.getInt(4));
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
                Integer invoiceID = cursor.getInt(4);
                SignedUpModel newSignedUp = new SignedUpModel(
                        email,
                        className,
                        year,
                        isPaid,
                        invoiceID);
                returnList.add(newSignedUp);
            } while (cursor.moveToNext());
            }
        else {}
        cursor.close();
        db.close();
        return returnList;
    }

    public List<SignedUpModel> getAllSignedUpsUnPaid() {
        List<SignedUpModel> returnList = new ArrayList<>();

        // get client data from the database
        String queryString = "SELECT * FROM SIGNEDUP WHERE ISPAID=0";
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
                Integer invoiceID = cursor.getInt(4);
                SignedUpModel newSignedUp = new SignedUpModel(
                        email,
                        className,
                        year,
                        isPaid,
                        invoiceID);
                returnList.add(newSignedUp);
            } while (cursor.moveToNext());
        }
        else {}
        cursor.close();
        db.close();
        return returnList;
    }
    public List<SignedUpModel> getFromTextFields(String emailAddress, String className,
                                                 Integer classYear, Integer isPaid){
        List<SignedUpModel> returnList = new ArrayList<>();
        String classYearAsString, isPaidAsString;

        //get client data from the database
        if (emailAddress.isEmpty()) {
            emailAddress = "";
        } else {
            emailAddress = " WHERE EMAIL = '" + emailAddress + "'";
        }

        if (className.isEmpty()) {
            className = "";
        } else {
            className = " WHERE CLASSNAME = '" + className + "'";
        }

        if (classYear == 0) {
            classYearAsString = "" ;
        } else {
            classYearAsString = " WHERE YEAR = " + classYear.toString();
        }
        if (isPaid == -1) {
            isPaidAsString = "" ;
        } else {
            isPaidAsString = " WHERE ISPAID = " + isPaid.toString();
        }

        String queryString =
                "SELECT * FROM SIGNEDUP" + emailAddress +
                        " INTERSECT " +
                "SELECT * FROM SIGNEDUP" + className +
                        " INTERSECT " +
                "SELECT * FROM SIGNEDUP" + classYearAsString +
                        " INTERSECT " +
                "SELECT * FROM SIGNEDUP" + isPaidAsString;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        // returns true if there are results to query
        if (cursor.moveToFirst()) {
            // Loops through cursor (query results) and adds to new client object
            do {
                String email = cursor.getString(0);
                className = cursor.getString(1);
                int year = cursor.getInt(2);
                int isPaidFromdb = cursor.getInt(3);
                Integer invoiceID = cursor.getInt(4);
                SignedUpModel newSignedUp = new SignedUpModel(
                        email,
                        className,
                        year,
                        isPaidFromdb,
                        invoiceID);
                returnList.add(newSignedUp);
            } while (cursor.moveToNext());
        }
        else {}
        cursor.close();
        db.close();
        return returnList;
    }

    //################## INVOICE QUERIES ###################

    // Adds one new Invoice to the database
    public boolean addOneInvoice(InvoiceModel invoiceModel) {
        // Check first that there are null signups available; exit and return false if none
        if (hasNullSignUps(invoiceModel.getEmail()) == false) {
            return false;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("EMAIL", invoiceModel.getEmail());
        cv.put("TOTALCOST", invoiceModel.getTotalCost());
        cv.put("ISPAID", invoiceModel.getIsPaid());

        long insert = db.insert("INVOICE", null, cv);

        // If insert went wrong, return -1
        if (insert==-1) {
            return false;
        }

        // Get database generated InvoiceID
        Integer invoiceID = getNewestInvoiceId();

        // Retrieve all SignUps associated with invoice and assign new invoice ID to invoice ID column in signups
        ArrayList<SignedUpModel> signedUpModels = getAllSignUpsForClientWithNullInvoiceID(invoiceModel.getEmail());
        for (SignedUpModel signedUpModel : signedUpModels) {
            updateSignedUpInvoiceID(signedUpModel, invoiceID);
        }

        // Method updates the Invoice's totalcost column
        updateInvoiceTotalCost(invoiceModel, invoiceID);

        // Method updates the Client's balance with creation of new invoice
        updateClientBalance(invoiceModel.getEmail());

        return true;
    }

    // Returns an ArrayList of all SignedUpModels for a specific client where the InvoiceID == NULL
    public ArrayList<SignedUpModel> getAllSignUpsForClientWithNullInvoiceID(String email) {
        ArrayList<SignedUpModel> returnList = new ArrayList<>();

        // get client data from the database
        String queryString = "SELECT * FROM SIGNEDUP WHERE EMAIL='" + email +"' AND INVOICEID IS NULL";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        // returns true if there are results to query
        if (cursor.moveToFirst()) {
            // Loops through cursor (query results) and adds to new client object
            do {
                String newEmail = cursor.getString(0);
                String className = cursor.getString(1);
                int year = cursor.getInt(2);
                int isPaid = cursor.getInt(3);
                Integer invoiceID = cursor.getInt(4);
                SignedUpModel newSignedUp = new SignedUpModel(
                        email,
                        className,
                        year,
                        isPaid,
                        invoiceID);
                returnList.add(newSignedUp);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }

    // When a new invoice is created, this method updates all signups associated with that invoice
    // with the id of the new invoice.
    public boolean updateSignedUpInvoiceID(SignedUpModel signedUpModel, Integer invoiceID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("EMAIL", signedUpModel.getEmail());
        cv.put("CLASSNAME", signedUpModel.getClassName());
        cv.put("YEAR", signedUpModel.getYear());
        cv.put("ISPAID", signedUpModel.getIsPaid());
        cv.put("INVOICEID", invoiceID);
        int updated = db.update("SIGNEDUP", cv, "EMAIL=? AND CLASSNAME=? AND YEAR=?", new String[]{signedUpModel.getEmail(), signedUpModel.getClassName(), Integer.toString(signedUpModel.getYear())});
        if (updated == 1) {
            return true;
        }
        else {
            return false;
        }
    }

    // Returns Invoice ID of the newest invoice in the data base as an Integer
    public int getNewestInvoiceId() {
        int maxInvoiceID = -1;
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT MAX(INVOICEID) FROM INVOICE";
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            maxInvoiceID = cursor.getInt(0);
        } else {
            System.out.println("ERROR: Could not move cursor to first entry.");
        }
        cursor.close();
        db.close();
        return maxInvoiceID;
    }

    // Updates the total cost on an invoice
    public Boolean updateInvoiceTotalCost(InvoiceModel invoiceModel, Integer invoiceID) {
        float total = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT COST FROM CLASS, SIGNEDUP WHERE CLASS.CLASSNAME = SIGNEDUP.CLASSNAME AND CLASS.YEAR = SIGNEDUP.YEAR AND SIGNEDUP.INVOICEID=" + invoiceID;
        Cursor cursor = db.rawQuery(queryString, null);

        // Moves through each query entry and sums cost
        if (cursor.moveToFirst()) {
            do {
                total += cursor.getFloat(0);
            } while (cursor.moveToNext());
        } else {
            System.out.println("ERROR: Could not move cursor to first entry.");
        }
        cursor.close();
        db.close();

        // No signups found associated
        if (total == 0) {
            System.out.println("STDERR: Could not find any signups associated with invoice.");
            return false;
        }

        // Open Invoice Table and update totalcost with summed value
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("INVOICEID", invoiceID);
        cv.put("EMAIL", invoiceModel.getEmail());
        cv.put("TOTALCOST", total);
        cv.put("ISPAID", invoiceModel.getIsPaid());
        int updated = db.update("INVOICE", cv, "INVOICEID=?", new String[]{Integer.toString(invoiceID)});
        if (updated == 1) {
            System.out.println("Successfully updated totalcost for invoice in DB.");
            return true;
        }
        else {
            System.out.println("Unsuccessfully updated totalcost for invoice in DB.");
            return false;
        }
    }

    // Updates the client balance for a specific client
    public Boolean updateClientBalance(String clientEmail) {
        float total = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT TOTALCOST FROM INVOICE WHERE EMAIL = '" + clientEmail + "' AND ISPAID=0";
        Cursor cursor = db.rawQuery(queryString, null);

        // Moves through each query entry and sums cost
        if (cursor.moveToFirst()) {
            do {
                total += cursor.getFloat(0);
            } while (cursor.moveToNext());
        } else {
            System.out.println("ERROR: Could not move cursor to first entry.");
        }
        cursor.close();
        db.close();

        //Get clients model
        ClientModel clientModel = getOneClientByPrimaryKey(clientEmail);

        // Open Client Table and update balance with summed value
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("EMAIL", clientModel.getEmail());
        cv.put("FIRSTNAME", clientModel.getFirstName());
        cv.put("LASTNAME", clientModel.getLastName());
        cv.put("PHONENUMBER", clientModel.getPhoneNumber());
        cv.put("BALANCE", total);
        int updated = db.update("CLIENT", cv, "EMAIL=?", new String[]{clientEmail});

        if (updated == 1) {
            System.out.println("Successfully updated totalcost for invoice in DB.");
            return true;
        } else {
            System.out.println("Unsuccessfully updated totalcost for invoice in DB.");
            return false;
        }
    }

    // Returns a list of all Invoices inside the database as InvoiceModels.
    public ArrayList<InvoiceModel> getAllInvoices() {
        ArrayList<InvoiceModel> invoiceModels = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM INVOICE";
        Cursor cursor = db.rawQuery(queryString, null);

        // Cursor moves through each entry, creates invoice model and adds to array list
        if (cursor.moveToFirst()) {
            do {
                Integer invoiceID = cursor.getInt(0);
                String clientEmail = cursor.getString(1);
                float totalCost = cursor.getFloat(2);
                Integer isPaid = cursor.getInt(3);
                InvoiceModel invoiceModel = new InvoiceModel(invoiceID, clientEmail, totalCost, isPaid);
                invoiceModels.add(invoiceModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return invoiceModels;
    }

    // Checks if any null sign ups available. Returns true if yes, false if no
    public Boolean hasNullSignUps(String clientEmail) {
        Boolean result;
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM SIGNEDUP WHERE EMAIL='" + clientEmail + "' AND INVOICEID IS NULL";
        Cursor cursor = db.rawQuery(queryString, null);

        // If cursor moves to first, there are Null entries, otherwise no
        if (cursor.moveToFirst()) {
            result = true;
        } else {
            result = false;
        }
        cursor.close();
        db.close();
        return result;
    }

    // Gets all invoices for a specific client email
    public ArrayList<InvoiceModel> getAllInvoicesByClientEmail(String clientEmail) {
        ArrayList<InvoiceModel> invoiceModels = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM INVOICE WHERE EMAIL = '" + clientEmail + "'";
        Cursor cursor = db.rawQuery(queryString, null);

        // Cursor moves through each entry, creates invoice model and adds to array list
        if (cursor.moveToFirst()) {
            do {
                Integer invoiceID = cursor.getInt(0);
                String clientEmail2 = cursor.getString(1);
                float totalCost = cursor.getFloat(2);
                Integer isPaid = cursor.getInt(3);
                InvoiceModel invoiceModel = new InvoiceModel(invoiceID, clientEmail2, totalCost, isPaid);
                invoiceModels.add(invoiceModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return invoiceModels;
    }

    // Returns an InvoiceModel for a specific Invoice by its invoice id.
    public InvoiceModel getInvoiceByInvoiceID(Integer invoiceID) {
        InvoiceModel invoiceModel = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM INVOICE WHERE INVOICEID = " + invoiceID;
        Cursor cursor = db.rawQuery(queryString, null);

        // Cursor moves through each entry, creates invoice model and adds to array list
        if (cursor.moveToFirst()) {
            Integer invoiceID2 = cursor.getInt(0);
            String clientEmail = cursor.getString(1);
            float totalCost = cursor.getFloat(2);
            Integer isPaid = cursor.getInt(3);
            invoiceModel = new InvoiceModel(invoiceID2, clientEmail, totalCost, isPaid);
        }

        cursor.close();
        db.close();
        return invoiceModel;
    }

    // Updates the isPaid column for an invoice to 1 which equals paid.
    public Boolean updateInvoiceToPaid(Integer invoiceID) {
        // Get invoice model for invoice being updated
        InvoiceModel invoiceModel = getInvoiceByInvoiceID(invoiceID);

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        // Invoice already paid, return false
        if (invoiceModel.getIsPaid() == 1) {
            return false;
        }

        // Enter update values and run update
        cv.put("INVOICEID", invoiceModel.getInvoiceID());
        cv.put("EMAIL", invoiceModel.getEmail());
        cv.put("TOTALCOST", invoiceModel.getTotalCost());
        cv.put("ISPAID", 1);
        int updated = db.update("INVOICE", cv, "INVOICEID=?", new String[]{Integer.toString(invoiceID)});
        if (updated == 1) {
            updateClientBalance(invoiceModel.getEmail());
            System.out.println("Successfully updated invoice as paid.");
            return true;
        }
        else {
            System.out.println("Unsuccessfully updated invoice as paid.");
            return false;
        }
    }
}
