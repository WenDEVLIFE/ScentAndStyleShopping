package com.scentstyle.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.scentstyle.model.Product;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class CartFrame extends JFrame {
    private JTable cartTable;
    private JButton btnCheckout, btnBack;
    private DefaultTableModel tableModel;
    private List<Product> cartItems;

    public CartFrame(List<Product> cartItems) {
        // Ensure cartItems is not null, and initialize if necessary
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        
        this.cartItems = cartItems;
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

        loadCartData();  // Load cart data when frame is initialized

        // Button Listeners
        btnCheckout.addActionListener(e -> checkoutAction());
        btnBack.addActionListener(e -> goBack());
    }

    // Load cart data into the table properly
    private void loadCartData() {
        tableModel.setRowCount(0); // Clear existing rows in the table
        for (Product product : cartItems) {
            // Assuming product.getQuantityInCart() holds the quantity for each product in the cart
            Object[] row = {
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                product.getStock() // Correct quantity field
            };
            tableModel.addRow(row); // Add row to the table
        }
    }

    // Checkout action
    private void checkoutAction() {
        if (cartItems.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your cart is empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Show success message on the Event Dispatch Thread (EDT)
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    JOptionPane.showMessageDialog(CartFrame.this, "Checkout successful! Thank you for shopping with Scent & Style.");
                    cartItems.clear(); // Clear cart after checkout
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

    // Add a new product to the cart and update the view
    public void addProductToCart(Product product) {
        cartItems.add(product); // Add product to cart list
        loadCartData(); // Reload table data to reflect the new product
    }

    // Method to update a product's quantity if needed
    public void updateProductQuantity(Product product, int stock) {
        for (Product p : cartItems) {
            if (p.equals(product)) {
                p.setStock(stock); // Update the quantity
                break;
            }
        }
        loadCartData(); // Reload table after updating quantity
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