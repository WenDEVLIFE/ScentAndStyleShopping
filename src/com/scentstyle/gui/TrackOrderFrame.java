package com.scentstyle.gui;

import javax.swing.*;
import com.scentstyle.model.Order;
import com.scentstyle.model.TrackingModel;
import database.TrackDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TrackOrderFrame extends JFrame {
    private JTextField txtOrderID;
    private JButton btnTrack, btnBack;
    private JTextArea txtOrderDetails;
    private List<Order> orderList;
    private String email;

    public TrackOrderFrame(String email) {
        this.email = email;

        setTitle("Track Order - Scent & Style");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.ORANGE);
        setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("Track Your Order");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitle, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.ORANGE);
        JLabel lblOrderID = new JLabel("Enter Order ID:");
        txtOrderID = new JTextField(15);
        btnTrack = new JButton("Track Order");
        inputPanel.add(lblOrderID);
        inputPanel.add(txtOrderID);
        inputPanel.add(btnTrack);
        add(inputPanel, BorderLayout.NORTH);

        txtOrderDetails = new JTextArea();
        txtOrderDetails.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtOrderDetails);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.ORANGE);
        btnBack = new JButton("Back");
        buttonPanel.add(btnBack);
        add(buttonPanel, BorderLayout.SOUTH);

        btnTrack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trackOrder();
            }
        });

        btnBack.addActionListener(e -> goBack());
    }

    private void trackOrder() {
        String orderIDText = txtOrderID.getText().trim();

        if (orderIDText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Order ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int orderID = Integer.parseInt(orderIDText);
            TrackingModel order = TrackDB.getInstance().getOrderDetails(orderID);

            if (order != null) {
                displayOrderDetails(order);
            } else {
                txtOrderDetails.setText("Order not found. Please check the Order ID.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Order ID format. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void goBack() {
        new UserDasdboard(email).setVisible(true);
        dispose();
    }

    private void displayOrderDetails(TrackingModel order) {
        String details = "Order ID: " + order.getTrackID() + "\n" +
                "Customer Name: " + order.getCustomerName() + "\n" +
                "Order Date: " + order.getOrderDate() + "\n" +
                "Status: " + order.getStatus() + "\n" +
                "Product Name: " + order.getProductName() + "\n" +
                "Category: " + order.getCategory() + "\n" +
                "Quantity: " + order.getQuantity() + "\n" +
                "Price: ₱" + order.getTotalPrice() + "\n" +
                "Payment Option: " + order.getPaymentOption() + "\n" +
                "Pay Amount: ₱" + order.getPayAmount() + "\n";

        txtOrderDetails.setText(details);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new TrackOrderFrame("example@example.com").setVisible(true));
    }
}