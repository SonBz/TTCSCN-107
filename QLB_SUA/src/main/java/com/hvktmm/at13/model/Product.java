package com.hvktmm.at13.model;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

public class Product {
    protected int id;
    protected String name;
    protected long price;
    protected String capacity;
    protected String product_type;
    protected int company_id;
    protected int amount;

    public Product() {
    }

    public Product(int id, long price, int amount) {
        this.id = id;
        this.price = price;
        this.amount = amount;
    }

    public Product(String name, long price, String capacity, String product_type, int company_id, int amount) {
        this.name = name;
        this.price = price;
        this.capacity = capacity;
        this.product_type = product_type;
        this.company_id = company_id;
        this.amount = amount;
    }
    public Product(String name, long price, String capacity, String product_type, int company_id) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
