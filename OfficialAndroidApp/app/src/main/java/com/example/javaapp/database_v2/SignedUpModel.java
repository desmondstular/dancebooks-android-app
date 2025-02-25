package com.example.javaapp.database_v2;

import java.util.Objects;

public class SignedUpModel {
    private String email;
    private String className;
    private int year;
    private int isPaid;
    private Integer invoiceID;

    // SIGNED UP MODEL CONSTRUCTOR
    public SignedUpModel(String email, String className, int year, int isPaid, Integer invoiceID) {
        this.email = email;
        this.className = className;
        this.year = year;
        this.isPaid = isPaid;
        this.invoiceID = invoiceID;
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

    public Integer getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(Integer invoiceID) {
        this.invoiceID = invoiceID;
    }

    @Override
    public String toString() {
        return email + " " + className + " " + year + " invID: " +invoiceID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SignedUpModel)) return false;
        SignedUpModel that = (SignedUpModel) o;
        return year == that.year && Objects.equals(email, that.email) && Objects.equals(className, that.className) && Objects.equals(invoiceID, that.invoiceID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, className, year, invoiceID);
    }
}
