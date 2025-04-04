/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scentstyle.model;

/**
 *
 * @author Frouen Junior
 */
public class CartModel {
    
   private int orderID;
   private int productID;
   private String productname;
   private String category;
   private double price;
    private int quantity;

    public CartModel(int orderID, int productID, String productname, String category, double price, int quantity) {
        this.orderID = orderID;
        this.productID = productID;
        this.productname = productname;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartModel{" + "orderID=" + orderID + ", productID=" + productID + ", productname=" + productname + ", category=" + category + ", price=" + price + ", quantity=" + quantity + '}';
    }
   
    
}
