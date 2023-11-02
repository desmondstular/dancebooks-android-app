package com.example.javaapp.database_v2;

import java.math.BigInteger;

public class ClientModel {
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private float balance;

    // CLIENT MODEL CONSTRUCTOR
    public ClientModel(String email, String firstName, String lastName, String phoneNumber, float balance) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    // CLIENT MODEL GETTERS
    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public float getBalance() {
        return balance;
    }

    //CLIENT MODEL SETTERS
    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + email + " " +   " " + balance;
    }
}
