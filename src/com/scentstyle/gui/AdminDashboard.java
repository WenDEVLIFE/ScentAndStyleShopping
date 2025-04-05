package com.scentstyle.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminDashboard extends JFrame {
    private JButton btnManageProduct, btnViewOrders, btnLogout;
    
    public AdminDashboard(){
        setTitle("Admin Dashboard");
        setSize(400, 300);
        setLocationRelativeTo(null);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        getContentPane().setBackground(Color.ORANGE);

        JLabel lblWelcome = new JLabel("Welcome, Admin" +"!");
        lblWelcome.setBounds(100, 10, 300, 30);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblWelcome);
        
        btnManageProduct = new JButton("Manage Product");
        btnManageProduct.setBounds(100, 50, 200, 30);
        add(btnManageProduct);
        
        btnViewOrders = new JButton("View Order History");
        btnViewOrders.setBounds(100, 100, 200, 30);
        add(btnViewOrders);
        
        btnLogout = new JButton("Logout");
        btnLogout.setBounds(100, 150, 200, 30);
        add(btnLogout);
        
        btnManageProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openProductManagement();
            }
        });

        btnViewOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openOrderView();
            }
        });

       
        
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logoutAction();
            }
        });
    }

    // Method to open product management
    private void openProductManagement() {
        JOptionPane.showMessageDialog(this, "Opening Product Management...");
        // Add functionality to open product management window
        ProductManagement productManagement = new ProductManagement();
        productManagement.setVisible(true);
        dispose(); // Close current frame
    }

    // Method to open order view
    private void openOrderView() {
        JOptionPane.showMessageDialog(this, "Opening Order View...");

        OrderManagement orderManagement = new OrderManagement();
        orderManagement.setVisible(true);
        dispose(); // Close current frame

    }

    // Method to handle logout
    private void logoutAction() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Logout", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dispose(); // Close current frame
            new LoginFrame().setVisible(true); // Redirect to login
        }
    }

    public static void main(String[] args) {
        new AdminDashboard().setVisible(true);
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