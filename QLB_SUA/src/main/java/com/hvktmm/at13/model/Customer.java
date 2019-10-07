package com.hvktmm.at13.model;

public class Customer {
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private long moneyTotal;
    private int status;
    private int numberOf;

    public Customer() {
    }

    public Customer(String name, String address, String phoneNumber, long moneyTotal) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.moneyTotal = moneyTotal;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNumberOf() {
        return numberOf;
    }

    public void setNumberOf(int numberOf) {
        this.numberOf = numberOf;
    }

    public long getMoneyTotal() {
        return moneyTotal;
    }

    public void setMoneyTotal(long moneyTotal) {
        this.moneyTotal = moneyTotal;
    }
}


