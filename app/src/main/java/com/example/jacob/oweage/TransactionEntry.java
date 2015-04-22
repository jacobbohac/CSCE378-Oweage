package com.example.jacob.oweage;

/**
 * Created by Brett on 4/21/2015.
 */
public class TransactionEntry {
    private String date = "";
    private String description = "";
    private double amount = 0.0;
    //positive value means you paid for someone, negative means they paid for you
    public TransactionEntry(String date, String description, double amount) {
        this.date = date;
        this.description = description;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
