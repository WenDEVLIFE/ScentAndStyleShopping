package com.scentstyle.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import com.scentstyle.model.Product;
import com.scentstyle.model.Order;
import java.util.Date;

public class UserDasdboard extends JFrame {
    private JButton btnViewProducts, btnViewCart, btnTrackOrder, btnLogout;
    private List<Product> productList = new ArrayList<>();
    private List<Product> cartItems = new ArrayList<>();
    private List<Order> orderList = new ArrayList<>(); // List to store orders
    private String email = ""; // Placeholder for user name

    public UserDasdboard(String email) {
        setTitle("User Dashboard - Scent & Style");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        this.email = email; // Set the username
        JLabel lblWelcome = new JLabel("Welcome, " + email + "!");
        lblWelcome.setBounds(100, 10, 300, 30);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblWelcome);

        // Buttons
        btnViewProducts = new JButton("View Products");
        btnViewProducts.setBounds(100, 50, 200, 30);
        add(btnViewProducts);

        btnViewCart = new JButton("View Cart");
        btnViewCart.setBounds(100, 100, 200, 30);
        add(btnViewCart);

        getContentPane().setBackground(Color.ORANGE);
        btnTrackOrder = new JButton("Track Order");
        btnTrackOrder.setBounds(100, 150, 200, 30);
        add(btnTrackOrder);

        btnLogout = new JButton("Logout");
        btnLogout.setBounds(100, 200, 200, 30);
        add(btnLogout);

        // Button Listeners
        btnViewProducts.addActionListener(e -> openProductList());
        btnViewCart.addActionListener(e -> openCart());
        btnTrackOrder.addActionListener(e -> openTrackOrder());
        btnLogout.addActionListener(e -> logoutAction());

        // Load sample products and orders (for demo)
        loadSampleProducts();
        loadSampleOrders();
    }

    private void openProductList() {
        new ProductListFrame(email).setVisible(true);
        dispose();
    }


    private void openCart() {
        new CartFrame(email).setVisible(true);
        dispose();
    }

    private void openTrackOrder() {
        // Open TrackOrderFrame with order list
        new TrackOrderFrame(email).setVisible(true);
        dispose();
    }

    private void logoutAction() {
        new LoginFrame().setVisible(true);
        dispose();
    }

    private void loadSampleProducts() {
        productList.add(new Product(1, "Perfume", "Vanilla", 500, 10));
        productList.add(new Product(2, "Clothes", "Jeans", 700, 15));
        productList.add(new Product(3, "Clothes", "T-shirt", 900, 20));
    }

    private void loadSampleOrders() {
        // Create sample orders with sample products
        List<Product> order1Products = new ArrayList<>();
        order1Products.add(new Product(1, "Perfume", "Vanilla", 500, 2));
        order1Products.add(new Product(2, "Clothes", "Jeans", 700, 1));

        List<Product> order2Products = new ArrayList<>();
        order2Products.add(new Product(3, "Clothes", "T-shirt", 900, 3));

        // Add sample orders to order list
        orderList.add(new Order(101, "Abalos", new Date(), order1Products, "Shipped", calculateTotal(order1Products)));
        orderList.add(new Order(102, "Angel", new Date(), order2Products, "Processing", calculateTotal(order2Products)));
    }

    private double calculateTotal(List<Product> products) {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice() * product.getStock(); // stock used as quantity
        }
        return total;
    }     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}