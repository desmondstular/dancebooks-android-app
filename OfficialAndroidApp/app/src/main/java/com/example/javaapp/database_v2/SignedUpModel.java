package com.example.javaapp.database_v2;

public class SignedUpModel {
    private String email;
    private String className;
    private int year;
    private int isPaid;

    // SIGNED UP MODEL CONSTRUCTOR
    public SignedUpModel(String email, String className, int year, int isPaid) {
        this.email = email;
        this.className = className;
        this.year = year;
        this.isPaid = isPaid;
    }

    // SIGNED UP MODEL GETTERS
    public String getEmail() {
        return email;
    }

    public String getClassName() {
        return className;
    }

    public int getYear() {
        return year;
    }

    public int getIsPaid() {
        return isPaid;
    }

    // SIGNED UP MODEL SETTERS
    public void setEmail(String email) {
        this.email = email;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setIsPaid(int paid) {
        isPaid = paid;
    }
}
