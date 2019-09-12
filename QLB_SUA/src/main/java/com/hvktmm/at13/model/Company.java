package com.hvktmm.at13.model;

public class Company {
    private int id;
    private String name;
    private String phone_number;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Company(String name, String phone_number, String address) {
        this.name = name;
        this.phone_number = phone_number;
        this.address = address;
    }

    public Company(int id, String name, String phone_number, String address) {
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.address = address;
    }

    public Company() {
    }
}
