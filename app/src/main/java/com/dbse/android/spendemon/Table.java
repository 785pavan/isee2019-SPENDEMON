package com.dbse.android.spendemon;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "summary_table")
public class Table {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String Type;
    private String Category;
    private double Amount;
    private String Date;
    private String Paymethod;
    private String note;

    public Table(String Type, String Category, double Amount, String Date, String Paymethod, String note) {
        this.Type = Type;
        this.Category = Category;
        this.Amount = Amount;
        this.Date = Date;
        this.Paymethod = Paymethod;
        this.note = note;
    }

    public Table() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getPaymethod() {
        return Paymethod;
    }

    public void setPaymethod(String paymethod) {
        Paymethod = paymethod;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
