package com.hvktmm.at13.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class DetailBillIterm {
    private SimpleIntegerProperty id;
    private SimpleIntegerProperty amount;
    private SimpleLongProperty price;
    private SimpleLongProperty totalMoney;
    private SimpleStringProperty nameProduct;

    public DetailBillIterm(){

    }
    public DetailBillIterm(int id, int amount, long price , long totalMoney,String nameProduct){
        this.id = new SimpleIntegerProperty(id);
        this.amount = new SimpleIntegerProperty(amount);
        this.price = new SimpleLongProperty(price);
        this.totalMoney = new SimpleLongProperty(totalMoney);
        this.nameProduct = new SimpleStringProperty(nameProduct);
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

    public int getAmount() {
        return amount.get();
    }

    public SimpleIntegerProperty amountProperty() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    public long getPrice() {
        return price.get();
    }

    public SimpleLongProperty priceProperty() {
        return price;
    }

    public void setPrice(long price) {
        this.price.set(price);
    }

    public long getTotalMoney() {
        return totalMoney.get();
    }

    public SimpleLongProperty totalMoneyProperty() {
        return totalMoney;
    }

    public void setTotalMoney(long totalMoney) {
        this.totalMoney.set(totalMoney);
    }

    public String getNameProduct() {
        return nameProduct.get();
    }

    public SimpleStringProperty nameProductProperty() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct.set(nameProduct);
    }
}
