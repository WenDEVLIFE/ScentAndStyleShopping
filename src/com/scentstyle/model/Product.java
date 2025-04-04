package com.scentstyle.model;

public class Product {
    private int productID;
    private String name;
    private String category;
    private double price;
    private int stock;
    
    public Product(int productID, String name, String category, float price, int stock){
        this.productID = productID;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }
    
    public int getProductID(){ 
        return productID;
    }
    
    public void setProductID(int productID){
        this.productID = productID;
    }
    
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for category
    public String getCategory() {
        return category;
    }

    // Setter for category
    public void setCategory(String category) {
        this.category = category;
    }

    // Getter for price
    public double getPrice() {
        return price;
    }

    // Setter for price
    public void setPrice(float price) {
        this.price = price;
    }

    // Getter for stock
    public int getStock() {
        return stock;
    }

    // Setter for stock
    public void setStock(int stock) {
        this.stock = stock;
    }

    // Method to update stock
    public void updateStock(int newStock) {
        this.stock = newStock;
    }

    // Override toString for debugging or logging
    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}