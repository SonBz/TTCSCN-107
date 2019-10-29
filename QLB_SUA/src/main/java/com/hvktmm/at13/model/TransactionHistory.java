package com.hvktmm.at13.model;


import java.time.LocalDateTime;
import java.util.Date;

public class TransactionHistory {
    private int aumount,id;
    private Date dateImport, dateExport;
    private String note, nameProduct, user;
    private int productId,userId;

    public TransactionHistory() {
    }

    public TransactionHistory(int aumount, String note, int productId, int userId) {
        this.aumount = aumount;
        this.note = note;
        this.productId = productId;
        this.userId = userId;
    }

    public TransactionHistory(int aumount, int id, Date dateExport, String note, String nameProduct, String user) {
        this.aumount = aumount;
        this.id = id;
        this.dateExport = dateExport;
        this.note = note;
        this.nameProduct = nameProduct;
        this.user = user;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public Date getDateImport() {
        return dateImport;
    }

    public void setDateImport(Date dateImport) {
        this.dateImport = dateImport;
    }

    public Date getDateExport() {
        return dateExport;
    }

    public void setDateExport(Date dateExport) {
        this.dateExport = dateExport;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAumount() {
        return aumount;
    }

    public void setAumount(int aumount) {
        this.aumount = aumount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
