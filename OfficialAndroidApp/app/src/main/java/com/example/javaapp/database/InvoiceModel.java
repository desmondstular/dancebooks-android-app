package com.example.javaapp.database;

public class InvoiceModel {
    private int clientID;
    private String className;
    private String classYear;

    public InvoiceModel(int clientID, String className, String classYear) {
        this.clientID = clientID;
        this.className = className;
        this.classYear = classYear;
    }

    public int getClientID() {
        return clientID;
    }

    public String getClassName() {
        return className;
    }

    public String getClassYear() {
        return classYear;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setClassYear(String classYear) {
        this.classYear = classYear;
    }
}
