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

}
