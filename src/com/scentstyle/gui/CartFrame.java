package com.scentstyle.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.scentstyle.model.CartModel;
import com.scentstyle.model.Product;
import database.CartDB;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class CartFrame extends JFrame {
    private JTable cartTable;
    private JButton btnCheckout, btnBack;
    private DefaultTableModel tableModel;
    private List<CartModel> cartItems;

    public CartFrame() {
        // Ensure cartItems is not null, and initialize if necessary
        setTitle("Cart - Scent & Style");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Table setup
        String[] columnNames = {"Product Name", "Category", "Price", "Quantity"};
        tableModel = new DefaultTableModel(columnNames, 0);
        cartTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(cartTable);
        scrollPane.setBounds(20, 20, 450, 150);
        add(scrollPane);

        // Buttons
        btnCheckout = new JButton("Checkout");
        btnCheckout.setBounds(50, 200, 120, 30);
        add(btnCheckout);

        btnBack = new JButton("Back");
        btnBack.setBounds(200, 200, 120, 30);
        add(btnBack);
        loadCart(); // Load cart items from the database
        loadCartData();  // Load cart data when frame is initialized

        // Button Listeners
        btnCheckout.addActionListener(e -> checkoutAction());
        btnBack.addActionListener(e -> goBack());
    }

    void loadCart(){
        cartTable.clearSelection();
        cartItems = new ArrayList<>();
        cartItems = CartDB.getInstance().getCartItems(); // Fetch cart items from the database
        loadCartData(); // Load data into the table
    }

    // Load cart data into the table properly
    private void loadCartData() {
        tableModel.setRowCount(0); // Clear existing rows in the table
        for (CartModel product : cartItems) {
            // Assuming product.getQuantityInCart() holds the quantity for each product in the cart
            Object[] row = {
                product.getProductname(),
                product.getCategory(),
                product.getPrice(),
                product.getQuantity()
            };
            tableModel.addRow(row); // Add row to the table
        }
    }

    // Checkout action
    private void checkoutAction() {
        int selectedRow = cartTable.getSelectedRow();
        if (cartItems.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your cart is empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            String productName = (String) cartTable.getValueAt(selectedRow, 0);
            String category = (String) cartTable.getValueAt(selectedRow, 1);
            double price = (double) cartTable.getValueAt(selectedRow, 2);
            int quantity = (int) cartTable.getValueAt(selectedRow, 3);




            // Show success message on the Event Dispatch Thread (EDT)
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    JOptionPane.showMessageDialog(CartFrame.this, "Checkout successful! Thank you for shopping with Scent & Style." +productName);
                    loadCartData(); // Refresh the table after checkout
                }
            });
        }
    }

    // Return to previous frame (User Dashboard)
    private void goBack() {
        new UserDasdboard().setVisible(true); // Assuming there's a UserDashboard class
        dispose(); // Close the CartFrame
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