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
    @Override
    public String toString() {
        return firstName + " " + lastName + " " + email + " " +   " " + "Balance: " + balance;
    }

    public String getFnameLnameEmail(){
        return firstName + ", " + lastName + ", " + email;
    }
}
