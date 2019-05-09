package com.dbse.android.spendemon.model;

import java.util.ArrayList;
import java.util.Date;

public class entry {

    private String category;
    private float Amount;
    private Date date;

    public entry(String category, float amount, Date date) {
        this.category = category;
        this.Amount = amount;
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getAmount() {
        return Amount;
    }

    public void setAmount(float amount) {
        Amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public entry() {
        this.category = null;
        this.Amount = 0;
        this.date = null;

    }
    private static int AmountId = 0;
    private static Date dateID = new Date ( "2009/12/31" );
    public static ArrayList<entry> createEntryArrayList(int numEntries){
        ArrayList<entry> entries = new ArrayList<>();
        for (int i=0; i<numEntries;i++){
            entries.add(new entry("dummy", ++AmountId, dateID));
        }
        return entries;
    }
}