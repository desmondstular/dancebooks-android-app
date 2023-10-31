package com.example.javaapp.database_v1;

public class InvoiceModel {
    private int clientID;
    private String className;
    private int classYear;

    public InvoiceModel(int clientID, String className, int classYear) {
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

    public int getClassYear() {
        return classYear;
    }

    @Override
    public String toString() {
        return clientID + " " + className + " " + classYear;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setClassYear(int classYear) {
        this.classYear = classYear;
    }
}
