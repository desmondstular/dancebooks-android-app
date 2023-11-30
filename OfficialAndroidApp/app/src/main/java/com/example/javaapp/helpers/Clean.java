package com.example.javaapp.helpers;

import static java.lang.Character.isAlphabetic;
import static java.lang.Character.isDigit;

import android.content.Context;

import com.example.javaapp.database_v2.ClientModel;
import com.example.javaapp.database_v2.DatabaseDao;
import com.example.javaapp.database_v2.InvoiceModel;

import java.io.File;
import java.io.FileWriter;

public class Clean {
    /* String cleanEmail(String input)
        Accepts a String as input and trims whitespaces. Returns
        the cleaned string.
     */
    public static String cleanEmail(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        input = input.toUpperCase();
        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            if (aChar != ' ') {
                stringBuilder.append(aChar);
            }
        }
        return stringBuilder.toString();
    }

    /* String cleanName(String input)
    Accepts a String as input and cleans out all characters
    that are not alphabetic or a "-" character. Returns
    the cleaned string.
     */
    public static String cleanName(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        input = input.toUpperCase();
        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            if (isAlphabetic(aChar) || aChar == '-') {
                stringBuilder.append(aChar);
            }
        }
        return stringBuilder.toString();
    }

    /* String cleanPhone(String input)
    Accepts a String representing user input and cleans out
    all characters that are numeric or a "-" character. Returns
    the cleaned string.
     */
    public static String cleanPhone(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            if (isDigit(aChar)) {
                stringBuilder.append(aChar);
            }
        }
        return stringBuilder.toString();
    }

    /* String clearIntInput(String input)
        Accepts a String as input and cleans out all characters
        except for numbers. Returns the cleaned String.
     */
    public static String cleanIntInput(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            if (isDigit(aChar)) {
                stringBuilder.append(aChar);
            }
        }
        return stringBuilder.toString();
    }

    /* public static String toTitle(String input)
        Accepts a String as input and then converts the string to
        title format. Each makes a character after a whitespace into a capital.
        Returns formatted String.
     */
    public static String toTitle(String input) {
        char last = '0';
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = input.toCharArray();
        int i = 0;
        for (char aChar : chars) {
            if (i == 0) {
                stringBuilder.append(Character.toUpperCase(aChar));
            } else if (last == ' ') {
                stringBuilder.append(Character.toUpperCase(aChar));
            } else {
                stringBuilder.append(Character.toLowerCase(aChar));
            }
            i++;
            last = aChar;
        }
        return stringBuilder.toString();
    }

    /* Boolean exportInvoice(Context context, InvoiceModel invoiceModel)
        Accepts an invoice model by ID and then creates a txt file that is
        saved in the downloads folder of the device. Returns true if file is
        created. Returns false if the file is not created.
     */
    public static Boolean exportInvoice(Context context, InvoiceModel invoiceModel) {
        try {
            String downloadPath = "/storage/emulated/0/Download/";
            String filename = "invoice-id" + invoiceModel.getInvoiceID();
            File file = new File(downloadPath, filename);
            DatabaseDao databaseDao = new DatabaseDao(context);
            ClientModel clientModel = databaseDao.getOneClientByPrimaryKey(invoiceModel.getEmail());

            // Create header of Invoice
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Invoice #" + invoiceModel.getInvoiceID()
                    + "\nClient: " + clientModel.getFirstName() + " " + clientModel.getLastName()
                    + "\nEmail: " + clientModel.getEmail()
                    + "\nPhone: " + clientModel.getPhoneNumber() + "\n");

            // Add trailer to invoice
            for (int i=0; i<30; i++) {
                stringBuilder.append('_');
            }
            stringBuilder.append("\n\nCLASS\t\tYEAR\t\tCOST\n");

            // Iterate each sign up associated with invoice and add line

            // Add trailer to invoice
            for (int i=0; i<30; i++) {
                stringBuilder.append('_');
            }
            stringBuilder.append("\nTotal cost = $");
            stringBuilder.append(invoiceModel.getTotalCost());

            // Write to file
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.append(stringBuilder.toString());
            fileWriter.flush();
            fileWriter.close();
            return true;
        } catch (Exception e) {
            System.out.println("Exception:\n" + e);
            return false;
        }
    }
}
