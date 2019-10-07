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

    public BillIterm() {
    }

    public BillIterm(int id,String name, int amount, Long totalMoney, CheckBox checkBox) {
        this.name = new SimpleStringProperty(name);
        this.amount = new SimpleIntegerProperty(amount);
        this.totalMoney = new SimpleLongProperty(totalMoney);
        this.id = new SimpleIntegerProperty(id);
        this.checkBox = checkBox;
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