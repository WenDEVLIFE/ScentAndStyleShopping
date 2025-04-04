package com.scentstyle.gui;

import javax.swing.*;
import com.scentstyle.model.Order;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TrackOrderFrame extends JFrame {
    private JTextField txtOrderID;
    private JButton btnTrack;
    private JTextArea txtOrderDetails;
    private List<Order> orderList; // List of all orders

    public TrackOrderFrame(List<Order> orderList) {
        this.orderList = orderList;
        
        setTitle("Track Order - Scent & Style");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblOrderID = new JLabel("Enter Order ID:");
        lblOrderID.setBounds(20, 20, 100, 25);
        add(lblOrderID);

        txtOrderID = new JTextField();
        txtOrderID.setBounds(130, 20, 150, 25);
        add(txtOrderID);

        btnTrack = new JButton("Track Order");
        btnTrack.setBounds(300, 20, 120, 25);
        add(btnTrack);

        txtOrderDetails = new JTextArea();
        txtOrderDetails.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtOrderDetails);
        scrollPane.setBounds(20, 60, 450, 200);
        add(scrollPane);

        // Button action to track order
        btnTrack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trackOrder();
            }
        });
    }

    // Track order and display details
    private void trackOrder() {
        String orderIDText = txtOrderID.getText().trim();

        // Validate order ID input
        if (orderIDText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Order ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int orderID = Integer.parseInt(orderIDText);

            // Find order by orderID
            Order order = findOrderById(orderID);

            if (order != null) {
                displayOrderDetails(order);
            } else {
                txtOrderDetails.setText("Order not found. Please check the Order ID.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Order ID format. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Search for order by ID
    private Order findOrderById(int orderID) {
        for (Order order : orderList) {
            if (order.getOrderID() == orderID) {
                return order;
            }
        }
        return null;
    }

    // Display order details
    private void displayOrderDetails(Order order) {
        StringBuilder details = new StringBuilder();
        details.append("Order ID: ").append(order.getOrderID()).append("\n");
        details.append("Customer Name: ").append(order.getCustomerName()).append("\n");
        details.append("Order Date: ").append(order.getOrderDate()).append("\n");
        details.append("Status: ").append(order.getStatus()).append("\n");
        details.append("Items:\n");

        order.getProducts().forEach(product -> {
            details.append("- ").append(product.getName()).append(" | Qty: ")
                    .append(product.getStock()).append(" | Price: ₱")
                    .append(product.getPrice()).append("\n");
        });

        details.append("\nTotal Amount: ₱").append(order.getTotalAmount());
        txtOrderDetails.setText(details.toString());
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
