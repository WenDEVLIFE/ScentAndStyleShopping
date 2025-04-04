package com.scentstyle.model;

import java.util.List;
import java.util.Date;

public class Order {
    private int orderID;
    private String customerName;
    private Date orderDate;
    private List<Product> products;
    private String status;
    private double totalAmount;

    public Order(int orderID, String customerName, Date orderDate, List<Product> products, String status, double totalAmount) {
        this.orderID = orderID;
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.products = products;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getStatus() {
        return status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}