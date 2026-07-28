package com.example.e_commerceapp.models;

public class MyOrder {

    private int id;
    private String orderId;
    private String orderDate;
    private String productName;
    private String productVariant;
    private double price;
    private double totalPrice;
    private int itemCount;
    private String status;
    private int imageResId;

    public MyOrder(int id, String orderId, String orderDate, String productName, String productVariant,
                   double price, double totalPrice, int itemCount, String status, int imageResId) {
        this.id = id;
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.productName = productName;
        this.productVariant = productVariant;
        this.price = price;
        this.totalPrice = totalPrice;
        this.itemCount = itemCount;
        this.status = status;
        this.imageResId = imageResId;
    }

    public int getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductVariant() {
        return productVariant;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public String getStatus() {
        return status;
    }

    public int getImageResId() {
        return imageResId;
    }
}