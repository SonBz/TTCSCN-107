package com.hvktmm.at13.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProductItem {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleLongProperty price;
    private SimpleStringProperty capacity;
    private SimpleStringProperty product_type;
    private SimpleStringProperty company;
    private SimpleIntegerProperty company_id;
    private SimpleStringProperty amount;

    public ProductItem(int id, int company_id, String name,Long price, String capacity, String product_type, String company, String amount) {
        this.id = new SimpleIntegerProperty(id);
        this.company_id = new SimpleIntegerProperty(company_id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleLongProperty(price);
        this.capacity = new SimpleStringProperty(capacity);
        this.product_type = new SimpleStringProperty(product_type);
        this.company = new SimpleStringProperty(company);
        this.amount = new SimpleStringProperty(amount);
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

    public Long getPrice() {
        return price.get();
    }

    public SimpleLongProperty priceProperty() {
        return price;
    }

    public void setPrice(long price) {
        this.price.set(price);
    }

    public String getCapacity() {
        return capacity.get();
    }

    public SimpleStringProperty capacityProperty() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity.set(capacity);
    }

    public String getProduct_type() {
        return product_type.get();
    }

    public SimpleStringProperty product_typeProperty() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type.set(product_type);
    }

    public String getCompany() {
        return company.get();
    }

    public SimpleStringProperty companyProperty() {
        return company;
    }

    public void setCompany(String company) {
        this.company.set(company);
    }

    public String getAmount() {
        return amount.get();
    }

    public SimpleStringProperty amountProperty() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
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

    public int getCompany_id() {
        return company_id.get();
    }

    public SimpleIntegerProperty company_idProperty() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id.set(company_id);
    }
}
