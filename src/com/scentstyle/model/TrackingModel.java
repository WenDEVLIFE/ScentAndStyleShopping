package com.scentstyle.model;

public class TrackingModel {
    private int trackID;
    private String productName;
    private String category;
    private double totalPrice;
    private String customerName;
    private String orderDate;
    private String status;
    private int quantity;
    private double payAmount;
    private String paymentOption;

    public TrackingModel(int trackID, String productName, String category, double totalPrice, String customerName, String orderDate, String status, int quantity, double payAmount, String paymentOption) {
        this.trackID = trackID;
        this.productName = productName;
        this.category = category;
        this.totalPrice = totalPrice;
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.status = status;
        this.quantity = quantity;
        this.payAmount = payAmount;
        this.paymentOption = paymentOption;
    }

    public int getTrackID() {
        return trackID;
    }

    public void setTrackID(int trackID) {
        this.trackID = trackID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }


    public String getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(String paymentOption) {
        this.paymentOption = paymentOption;
    }

    @Override
    public String toString() {
        return "TrackingModel{" +
                "trackID=" + trackID +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                ", totalPrice=" + totalPrice +
                ", customerName='" + customerName + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", status='" + status + '\'' +
                ", quantity=" + quantity +
                ", payAmount=" + payAmount +
                ", paymentOption='" + paymentOption + '\'' +
                '}';
    }
}
