package com.dbse.android.spendemon.model;

import java.util.ArrayList;
import java.util.Date;

public class Entry {

    private String category;
    private double Amount;
    private Date date;
    private String payMethod;

    public Entry(String category, double amount, Date date) {
        this.category = category;
        this.Amount = amount;
        this.date = date;
    }

    public Entry() {
       this.category = null;
       this.Amount = 0;
       this.date = null;
       this.payMethod = null;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "category='" + category + '\'' +
                ", Amount=" + Amount +
                ", date=" + date +
                ", payMethod=" + payMethod +
                '}';
    }

    public Entry(String cat, double amount, Date date, String payMeth) {
        this.category = cat;
        this.Amount = amount;
        this.date = date;
        this.payMethod = payMeth;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    private static int AmountId = 0;
    private static Date dateID = new Date("2009/12/31");

    public static ArrayList<Entry> createEntryArrayList(int numEntries) {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < numEntries; i++) {
            entries.add(new Entry("dummy " + i, ++AmountId, dateID));
        }
        return entries;
    }
}