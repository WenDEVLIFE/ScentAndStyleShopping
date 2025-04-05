package com.scentstyle.gui;

import com.scentstyle.model.TrackingModel;
import database.TrackDB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class OrderManagement extends javax.swing.JFrame {

    private JTable orderTable;
    private List<TrackingModel> orderList;
    private DefaultTableModel tableModel;
    private JButton btnDeleteOrder, btnUpdateStatus, btnBack;

    public OrderManagement() {
        initComponents();
        loadOrderData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Order Management - Scent & Style");
        setSize(600, 400);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.ORANGE);
        setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("Order History");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitle, BorderLayout.NORTH);

        String[] columnNames = {"Order ID", "Product Name", "Category", "Total Price", "Customer Name", "Order Date", "Status", "Quantity", "Pay Amount", "Payment Option"};
        tableModel = new DefaultTableModel(columnNames, 0);
        orderTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(orderTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.ORANGE);
        btnDeleteOrder = new JButton("Delete Order");
        btnUpdateStatus = new JButton("Update Status");
        btnBack = new JButton("Back");
        buttonPanel.add(btnDeleteOrder);
        buttonPanel.add(btnUpdateStatus);
        buttonPanel.add(btnBack);
        add(buttonPanel, BorderLayout.SOUTH);

        btnDeleteOrder.addActionListener(e -> deleteOrderAction());
        btnUpdateStatus.addActionListener(e -> updateStatusAction());
        btnBack.addActionListener(e -> backAction());

        loadOrderData();
    }// </editor-fold>//GEN-END:initComponents

    private void loadOrderData() {
        orderList = TrackDB.getInstance().getAllOrders();
        tableModel.setRowCount(0); // Clear existing data

        for (TrackingModel order : orderList) {
            Object[] row = {
                    order.getTrackID(),
                    order.getProductName(),
                    order.getCategory(),
                    order.getTotalPrice(),
                    order.getCustomerName(),
                    order.getOrderDate(),
                    order.getStatus(),
                    order.getQuantity(),
                    order.getPayAmount(),
                    order.getPaymentOption()
            };
            tableModel.addRow(row);
        }
    }

    private void deleteOrderAction() {
        int selectedRow = orderTable.getSelectedRow();
        if (selectedRow != -1) {
            int orderID = (int) tableModel.getValueAt(selectedRow, 0);
            TrackDB.getInstance().deleteOrder(orderID);
            loadOrderData();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an order to delete.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateStatusAction() {
        int selectedRow = orderTable.getSelectedRow();
        if (selectedRow != -1) {
            int orderID = (int) tableModel.getValueAt(selectedRow, 0);
            String newStatus = JOptionPane.showInputDialog(this, "Enter new status:");
            if (newStatus != null && !newStatus.trim().isEmpty()) {
                TrackDB.getInstance().updateOrderStatus(orderID, newStatus.trim());
                loadOrderData();
            } else {
                JOptionPane.showMessageDialog(this, "Status cannot be empty.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an order to update.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    void backAction() {
        dispose();
        new AdminDashboard().setVisible(true);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}