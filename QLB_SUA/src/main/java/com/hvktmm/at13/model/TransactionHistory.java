package com.hvktmm.at13.model;


import java.sql.Date;
import java.time.LocalDateTime;

public class TransactionHistory {
    private int aumount;
    private LocalDateTime dateImport, dateExport;
    private String note;
    private int productId,userId;

    public TransactionHistory() {
    }

    public TransactionHistory(int aumount, String note, int productId, int userId) {
        this.aumount = aumount;
        this.note = note;
        this.productId = productId;
        this.userId = userId;
    }

    public int getAumount() {
        return aumount;
    }

    public void setAumount(int aumount) {
        this.aumount = aumount;
    }

    public LocalDateTime getDateImport() {
        return dateImport;
    }

    public void setDateImport(LocalDateTime dateImport) {
        this.dateImport = dateImport;
    }

    public LocalDateTime getDateExport() {
        return dateExport;
    }

    public void setDateExport(LocalDateTime dateExport) {
        this.dateExport = dateExport;
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
