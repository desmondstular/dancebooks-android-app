package com.example.javaapp.database_v2;

import java.util.Objects;

public class InvoiceModel {
    private Integer invoiceID;
    private String email;
    private Float totalCost;
    private Integer isPaid;

    public InvoiceModel(Integer invoiceID, String email, Float totalCost, Integer isPaid) {
        this.invoiceID = invoiceID;
        this.email = email;
        this.totalCost = totalCost;
        this.isPaid = isPaid;
    }

    public Integer getInvoiceID() {
        return invoiceID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Float totalCost) {
        this.totalCost = totalCost;
    }

    public Integer getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Integer isPaid) {
        this.isPaid = isPaid;
    }

    @Override
    public String toString() {
        return "invID: " + invoiceID + "\n " + email + " \n" + "Total: " + totalCost + " \n" + isPaid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvoiceModel)) return false;
        InvoiceModel that = (InvoiceModel) o;
        return Objects.equals(invoiceID, that.invoiceID) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceID, email);
    }
}
