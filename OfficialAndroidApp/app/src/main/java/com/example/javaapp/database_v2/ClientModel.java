package com.example.javaapp.database_v2;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientModel)) return false;
        ClientModel that = (ClientModel) o;
        return Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getLastName(), that.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getFirstName(), getLastName());
    }
}
