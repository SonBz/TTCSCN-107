package com.hvktmm.at13.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProductItem {
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;
    private SimpleStringProperty capacity;
    private SimpleStringProperty product_type;
    private SimpleStringProperty company;

    public ProductItem(String name,Double price, String capacity, String product_type, String company) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.capacity = new SimpleStringProperty(capacity);
        this.product_type = new SimpleStringProperty(product_type);
        this.company = new SimpleStringProperty(company);
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

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
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
}
