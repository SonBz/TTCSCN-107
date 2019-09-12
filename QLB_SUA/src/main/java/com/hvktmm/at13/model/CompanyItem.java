package com.hvktmm.at13.model;

import javafx.beans.property.SimpleStringProperty;

public class CompanyItem {
    private int id;
    private SimpleStringProperty name;
    private SimpleStringProperty address;
    private SimpleStringProperty phone_number;

    public CompanyItem(int id,String name, String address, String phone_number) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.phone_number = new SimpleStringProperty(phone_number);
    }

    public CompanyItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getPhone_number() {
        return phone_number.get();
    }

    public SimpleStringProperty phone_numberProperty() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number.set(phone_number);
    }
}
