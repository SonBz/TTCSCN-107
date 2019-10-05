package com.hvktmm.at13.model;

import java.util.Date;

public class Bill {

    private int id;
    private Date dateTrading;
    private String note;
    private double total_money;
    private int userId,customerId;

    public Bill(Date dateTrading, String note, double total_money, int userId, int customerId) {
        this.dateTrading = dateTrading;
        this.note = note;
        this.total_money = total_money;
        this.userId = userId;
        this.customerId = customerId;
    }

    public Bill() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateTrading() {
        return dateTrading;
    }

    public void setDateTrading(Date dateTrading) {
        this.dateTrading = dateTrading;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getTotal_money() {
        return total_money;
    }

    public void setTotal_money(double total_money) {
        this.total_money = total_money;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
