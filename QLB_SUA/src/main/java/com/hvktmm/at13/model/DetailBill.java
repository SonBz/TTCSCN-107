package com.hvktmm.at13.model;

public class DetailBill {
    private int id,amount ;
    private long total_money;
    private long price;
    private int billId,productId;

    public DetailBill() {
    }

    public DetailBill(int amount, long total_money, long price, int billId, int productId) {
        this.amount = amount;
        this.total_money = total_money;
        this.price = price;
        this.billId = billId;
        this.productId = productId;
    }

    public int getId() {
        return id;
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

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public long getTotal_money() {
        return total_money;
    }

    public void setTotal_money(long total_money) {
        this.total_money = total_money;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
