package com.dbse.android.spendemon.model;

public class Entry {

    private String category;
    private double Amount;
    private String date;
    private String payMethod;
    private String type;
    private String note;
    private int id;

    public Entry(String category, double amount, String date) {
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

    public Entry(String type, String cat, double amount, String date, String payMeth, String note) {
        this.type = type;
        this.category = cat;
        this.Amount = amount;
        this.date = date;
        this.payMethod = payMeth;
        this.note = note;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return Amount;
    }

    public String getDate() {
        return date;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public String getNote() {
        return note;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "category='" + category + '\'' +
                ", Amount=" + Amount +
                ", date='" + date + '\'' +
                ", payMethod='" + payMethod + '\'' +
                ", type='" + type + '\'' +
                ", note='" + note + '\'' +
                ", id=" + id +
                '}';
    }
}