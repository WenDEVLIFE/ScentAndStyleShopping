package com.scentstyle.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.scentstyle.model.CartModel;
import database.CartDB;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartFrame extends JFrame {
    private JTable cartTable;
    private JButton btnCheckout, btnBack;
    private DefaultTableModel tableModel;
    private List<CartModel> cartItems;
    private TextField txtCustomerName, txtAmount;
    private String[] paymentMethods;
    private JComboBox<String> paymentMethodComboBox;
    private String email;

    public CartFrame(String email) {
        this.email = email;
        cartItems = new ArrayList<>();

        setTitle("Cart - Scent & Style");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.ORANGE);
        setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("Your Cart");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitle, BorderLayout.NORTH);

        String[] columnNames = {"Product Name", "Category", "Price", "Quantity"};
        paymentMethods = new String[]{"Select a payment", "Cash on Delivery", "Credit Card", "Debit Card", "Mobile Payment"};
        tableModel = new DefaultTableModel(columnNames, 0);
        cartTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(cartTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.ORANGE);
        btnCheckout = new JButton("Checkout");
        btnBack = new JButton("Back");
        buttonPanel.add(btnCheckout);
        buttonPanel.add(btnBack);
        add(buttonPanel, BorderLayout.SOUTH);

        btnCheckout.addActionListener(e -> checkoutAction());
        btnBack.addActionListener(e -> goBack());

        loadCart();
        loadCartData();
    }

    void loadCart() {
        cartTable.clearSelection();
        cartItems = CartDB.getInstance().getCartItems();
        loadCartData();
    }

    private void loadCartData() {
        tableModel.setRowCount(0);
        for (CartModel product : cartItems) {
            Object[] row = {
                    product.getProductname(),
                    product.getCategory(),
                    product.getPrice(),
                    product.getQuantity()
            };
            tableModel.addRow(row);
        }
    }

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
                                return;
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
                                orderDetails.put("product_id", cartItems.get(selectedRow).getProductID());
                                orderDetails.put("pay_amount", priceAmount);
                                orderDetails.put("payment_option", selectedPaymentMethod);
                                orderDetails.put("order_id", cartItems.get(selectedRow).getOrderID());
                                CartDB.getInstance().insertOrder(orderDetails);
                                loadCartData();
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Total Amount is Empty, Please Enter your amount.", "Error", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Payment method selection cancelled.", "Error", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Customer Name is Empty, Please Enter your name.", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
    }

    private void goBack() {
        new UserDasdboard(email).setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new CartFrame("example@example.com").setVisible(true));
    }
}