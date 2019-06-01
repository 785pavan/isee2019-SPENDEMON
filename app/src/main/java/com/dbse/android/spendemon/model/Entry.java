package com.dbse.android.spendemon.model;

public class Entry {

    private String category;
    private double Amount;
    private String date;
    private String payMethod;
    private String type;

    public Entry(String category, double amount, String date) {
        this.category = category;
        this.Amount = amount;
        this.date = date;
        /*setCatResource();
        setPaymentResource();
        setTypeResource();*/
    }

    public Entry() {
        this.category = null;
        this.Amount = 0;
        this.date = null;
        this.payMethod = null;

    }

    public Entry(String type, String cat, double amount, String date, String payMeth) {
        this.type = type;
        this.category = cat;
        this.Amount = amount;
        this.date = date;
        this.payMethod = payMeth;

    }

    public String getType() {
        return type;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }
}