package com.example.javaapp.database;

public class ClientModel {
    private int clientID;
    private String clientFirstName;
    private String clientLastName;
    private String clientEmail;
    private int clientPhone;

    //constructor
    public ClientModel(int clientID, String clientFirstName, String clientLastName,
                       String clientEmail, int clientPhone) {
        this.clientID = clientID;
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.clientEmail = clientEmail;
        this.clientPhone = clientPhone;
    }

    public int getClientID() {
        return clientID;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public int getClientPhone() {
        return clientPhone;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public void setClientPhone(int clientPhone) {
        this.clientPhone = clientPhone;
    }
}
