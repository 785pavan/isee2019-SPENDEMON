package com.dbse.android.spendemon;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "summary_table")
public class Table {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String Type;
    private String Category;
    private int Amount;
    private String Date;
    private String Paymethod;

    public Table(String Type, String Category, int Amount, String Date, String Paymethod ) {
        this.Type = Type;
        this.Category = Category;
        this.Amount = Amount;
        this.Date = Date;
        this. Paymethod = Paymethod;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return Type;
    }

    public String getCategory() {
        return Category;
    }

    public int getAmount() {
        return Amount;
    }

    public String getDate() {
        return Date;
    }

    public String getPaymethod() {
        return Paymethod;
    }


}
