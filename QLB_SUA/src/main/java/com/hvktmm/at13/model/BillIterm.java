package com.hvktmm.at13.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class BillIterm {
    private SimpleStringProperty name;
    private SimpleIntegerProperty amount;
    private SimpleLongProperty totalMoney;

    public BillIterm() {
    }

    public BillIterm(String name, int amount, Long totalMoney) {
        this.name = new SimpleStringProperty(name);
        this.amount = new SimpleIntegerProperty(amount);
        this.totalMoney = new SimpleLongProperty(totalMoney);
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

    public double getTotalMoney() {
        return totalMoney.get();
    }

    public SimpleLongProperty totalMoneyProperty() {
        return totalMoney;
    }

    public void setTotalMoney(long totalMoney) {
        this.totalMoney.set(totalMoney);
    }
}