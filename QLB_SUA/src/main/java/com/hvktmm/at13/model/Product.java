package com.hvktmm.at13.model;

public class Product {
    private int id;
    private String name;
    private Double price;
    private String capacity;
    private String product_type;
    private int company_id;

    public Product() {
    }

    public Product(String name, Double price, String capacity, String product_type, int company_id) {
        this.name = name;
        this.price = price;
        this.capacity = capacity;
        this.product_type = product_type;
        this.company_id = company_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }
}
