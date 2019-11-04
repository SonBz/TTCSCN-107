package com.hvktmm.at13.model;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class TransactionHistory {
    private int amount,id;
    private Timestamp dateImport, dateExport;
    private String note, nameProduct, user;
    private int productId,userId;

    public TransactionHistory() {
    }

    public TransactionHistory(int amount, String note, int productId, int userId) {
        this.amount = amount;
        this.note = note;
        this.productId = productId;
        this.userId = userId;
    }

//    public TransactionHistory(int amount, int id, Date dateExport, String note, String nameProduct, String user) {
//        this.amount = amount;
//        this.id = id;
//        this.dateExport = dateExport;
//        this.note = note;
//        this.nameProduct = nameProduct;
//        this.user = user;
//    }
    public TransactionHistory(int amount, int id, Timestamp dateExport, String note, String nameProduct, String user) {
        this.amount = amount;
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

//    public Date getDateImport() {
//        return dateImport;
//    }
//
//    public void setDateImport(Date dateImport) {
//        this.dateImport = dateImport;
//    }
//
//    public Date getDateExport() {
//        return dateExport;
//    }
//
//    public void setDateExport(Date dateExport) {
//        this.dateExport = dateExport;
//    }


    public Timestamp getDateImport() {
        return dateImport;
    }

    public void setDateImport(Timestamp dateImport) {
        this.dateImport = dateImport;
    }

    public Timestamp getDateExport() {
        return dateExport;
    }

    public void setDateExport(Timestamp dateExport) {
        this.dateExport = dateExport;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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
