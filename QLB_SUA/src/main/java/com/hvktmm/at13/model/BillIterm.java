package com.hvktmm.at13.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class BillIterm {
    private  SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleIntegerProperty amount;
    private SimpleLongProperty totalMoney;
    private CheckBox checkBox;
    private SimpleStringProperty user;
    private SimpleStringProperty note;
    private  SimpleStringProperty dateTrading;

    public BillIterm() {
    }

    public BillIterm(int id,String name, int amount, Long totalMoney, CheckBox checkBox) {
        this.name = new SimpleStringProperty(name);
        this.amount = new SimpleIntegerProperty(amount);
        this.totalMoney = new SimpleLongProperty(totalMoney);
        this.id = new SimpleIntegerProperty(id);
        this.checkBox = checkBox;
    }
    public BillIterm(int id, String dateTranding, String note,Long totalMoney,String user){
        this.id = new SimpleIntegerProperty(id);
        this.dateTrading = new SimpleStringProperty(dateTranding);
        this.note = new SimpleStringProperty(note);
        this.totalMoney = new SimpleLongProperty(totalMoney);
        this.user = new SimpleStringProperty(user);
    }
    public String getUser() {
        return user.get();
    }

    public SimpleStringProperty userProperty() {
        return user;
    }

    public void setUser(String user) {
        this.user.set(user);
    }

    public String getNote() {
        return note.get();
    }

    public SimpleStringProperty noteProperty() {
        return note;
    }

    public void setNote(String note) {
        this.note.set(note);
    }

    public String getDateTrading() {
        return dateTrading.get();
    }

    public SimpleStringProperty dateTradingProperty() {
        return dateTrading;
    }

    public void setDateTrading(String dateTrading) {
        this.dateTrading.set(dateTrading);
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getAmount() {
        return amount.get();
    }

    public SimpleIntegerProperty amountProperty() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    public Long getTotalMoney() {
        return totalMoney.get();
    }

    public SimpleLongProperty totalMoneyProperty() {
        return totalMoney;
    }

    public void setTotalMoney(long totalMoney) {
        this.totalMoney.set(totalMoney);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }
}