package com.example.javaapp;

import androidx.annotation.NonNull;

public class ClientModel {
    private int id;
    private String firstName;
    private String lastName;
    private String clientEmail;
    private int clientPhone;

    //constructor
    public ClientModel(int id, String firstName, String lastName,
                       String clientEmail, int clientPhone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.clientEmail = clientEmail;
        this.clientPhone = clientPhone;
    }
    public ClientModel() {
    }

    //to string
    @Override
    public String toString() {
        return "ClientModel{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                ", clientPhone=" + clientPhone +
                '}';
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public int getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(int clientPhone) {
        this.clientPhone = clientPhone;
    }
}
