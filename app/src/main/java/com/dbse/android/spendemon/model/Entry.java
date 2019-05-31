package com.dbse.android.spendemon.model;

public class Entry {

    private String category;
    private double Amount;
    private String date;
    private String payMethod;
    private int catResource;
    private String type;
    private int typeResource;
    private int paymentResource;

    public Entry(String category, double amount, String date) {
        this.category = category;
        this.Amount = amount;
        this.date = date;
        setCatResource();
        setPaymentResource();
        setTypeResource();
    }

    public void setCatResource() {
        switch (category) {
            case "Rent": {
                this.catResource = 1;
                break;
            }
            case "Insurance": {
                this.catResource = 2;
                break;
            }
            case "Groceries": {
                this.catResource = 3;
                break;
            }
            case "Travel": {
                this.catResource = 4;
                break;
            }
            case "Restaurant": {
                this.catResource = 5;
                break;
            }
            case "Allowance": {
                this.catResource = 5;
                break;
            }
            case "Salary": {
                this.catResource = 7;
                break;
            }
            case "Bonds": {
                this.catResource = 8;
                break;
            }
            case "Bonus": {
                this.catResource = 9;
                break;
            }
            case "Gift": {
                this.catResource = 10;
                break;
            }
            default: {
                this.catResource = 0;
                break;
            }
        }
    }

    public void setTypeResource() {
        switch (type) {
            case "Incomes": {
                this.catResource = 1;
                break;
            }
            case "Expenses": {
                this.catResource = 1;
                break;
            }
            default: {
                this.catResource = 0;
                break;
            }

        }
    }

    public void setPaymentResource() {
        switch (payMethod) {
            case "Cash": {
                this.catResource = 1;
                break;
            }
            case "Card": {
                this.catResource = 2;
                break;
            }
            case "PayPal": {
                this.catResource = 3;
                break;
            }
            case "GooglePay": {
                this.catResource = 4;
                break;
            }
            case "ApplePay": {
                this.catResource = 5;
                break;
            }

            default: {
                this.catResource = 0;
                break;
            }

        }
    }

    public Entry() {
        this.category = null;
        this.Amount = 0;
        this.date = null;
        this.payMethod = null;
        setCatResource();
        setPaymentResource();
        setTypeResource();
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

    public Entry(String cat, double amount, String date, String payMeth) {
        this.category = cat;
        this.Amount = amount;
        this.date = date;
        this.payMethod = payMeth;
        setCatResource();
        setPaymentResource();
        setTypeResource();
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    private static int AmountId = 0;


}