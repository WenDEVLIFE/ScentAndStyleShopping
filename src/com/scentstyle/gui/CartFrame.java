package com.scentstyle.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.scentstyle.model.CartModel;
import com.scentstyle.model.Product;
import database.CartDB;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartFrame extends JFrame {
    private JTable cartTable;
    private JButton btnCheckout, btnBack;
    private DefaultTableModel tableModel;
    private List<CartModel> cartItems;
    private TextField txtCustomerName, txtAmount;
    private String [] paymentMethods;
    private JComboBox<String> paymentMethodComboBox;

    public CartFrame() {
        // Ensure cartItems is not null, and initialize if necessary
        setTitle("Cart - Scent & Style");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Table setup
        String[] columnNames = {"Product Name", "Category", "Price", "Quantity"};
        paymentMethods = new String[]{"Select a payment", "Cash on Delivery", "Credit Card", "Debit Card", "Mobile Payment"};
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
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a product to checkout.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (cartItems.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your cart is empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            String productName = (String) cartTable.getValueAt(selectedRow, 0);
            String category = (String) cartTable.getValueAt(selectedRow, 1);
            double price = (double) cartTable.getValueAt(selectedRow, 2);
            int quantity = (int) cartTable.getValueAt(selectedRow, 3);
            double totalPrice = price * quantity;

            txtCustomerName = new TextField();
            txtCustomerName.setBounds(50, 50, 200, 30);
            txtCustomerName.setVisible(true);

            int response = JOptionPane.showConfirmDialog(this, txtCustomerName, "Customer Name", JOptionPane.OK_CANCEL_OPTION);

            if (response == JOptionPane.OK_OPTION) {
                String customerName = txtCustomerName.getText();

                paymentMethodComboBox = new JComboBox<>(paymentMethods);

                int paymentResponse = JOptionPane.showConfirmDialog(this, paymentMethodComboBox, "Select Payment Method", JOptionPane.OK_CANCEL_OPTION);

                if (paymentResponse == JOptionPane.OK_OPTION) {
                    String selectedPaymentMethod = (String) paymentMethodComboBox.getSelectedItem();
                    assert selectedPaymentMethod != null;
                    if (selectedPaymentMethod.equals("Select a payment")) {
                        JOptionPane.showMessageDialog(this, "Please select a valid payment method.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                        txtAmount = new TextField();
                        txtAmount.setBounds(50, 50, 200, 30);
                        txtAmount.setVisible(true);
                        int responseAmount = JOptionPane.showConfirmDialog(this, txtAmount, "Total Amount", JOptionPane.OK_CANCEL_OPTION);
                        if (responseAmount == JOptionPane.OK_OPTION) {
                            String amount = txtAmount.getText();
                            double priceAmount = Double.parseDouble(amount);
                            if (priceAmount < totalPrice) {
                                JOptionPane.showMessageDialog(this, "Insufficient amount. Please enter a valid amount.", "Error", JOptionPane.INFORMATION_MESSAGE);
                                return; // Exit the method if checkout is cancelled
                            } else {
                                HashMap<String, Object> orderDetails = new HashMap<>();
                                orderDetails.put("product_name", productName);
                                orderDetails.put("category", category);
                                orderDetails.put("price", price);
                                orderDetails.put("quantity", quantity);
                                orderDetails.put("customer_name", customerName);
                                orderDetails.put("payment_method", selectedPaymentMethod);
                                orderDetails.put("amount", priceAmount);
                                orderDetails.put("total_price", totalPrice);
                                orderDetails.put("order_date", java.time.LocalDate.now().toString());
                                orderDetails.put("status", "Pending");
                                orderDetails.put("product_id", cartItems.get(selectedRow).getProductID()); // Assuming getProductID() method exists
                                orderDetails.put("pay_amount", priceAmount);
                                orderDetails.put("payment_option", selectedPaymentMethod);
                                orderDetails.put("order_id", cartItems.get(selectedRow).getOrderID()); // Assuming getOrderID() method exists
                                CartDB.getInstance().insertOrder(orderDetails); // Insert order into the database
                                loadCartData(); // Refresh the table after checkout
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Total Amount is Empty, Please Enter your amount.", "Error", JOptionPane.INFORMATION_MESSAGE);
                            return; // Exit the method if checkout is cancelled
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Payment method selection cancelled.", "Error", JOptionPane.INFORMATION_MESSAGE);
                    return; // Exit the method if checkout is cancelled
                }
            } else {
                JOptionPane.showMessageDialog(this, "Customer Name is Empty, Please Enter your name.", "Error", JOptionPane.INFORMATION_MESSAGE);
                return; // Exit the method if checkout is cancelled
            }
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