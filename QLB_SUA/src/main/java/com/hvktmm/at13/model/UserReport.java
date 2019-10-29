package com.hvktmm.at13.model;

public class UserReport {
    private int stt;
    private String userName,nameProduct;
    private int amountProduct;

    public UserReport() {
    }

    public UserReport(int stt, String userName, String nameProduct, int amountProduct) {
        this.stt = stt;
        this.userName = userName;
        this.nameProduct = nameProduct;
        this.amountProduct = amountProduct;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getAmountProduct() {
        return amountProduct;
    }

    public void setAmountProduct(int amountProduct) {
        this.amountProduct = amountProduct;
    }
}
