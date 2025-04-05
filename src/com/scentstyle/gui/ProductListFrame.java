package com.scentstyle.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.scentstyle.model.Product;
import database.ProductDB;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProductListFrame extends JFrame {
    private JTable productTable;
    private JButton btnAddToCart, btnBack;
    private DefaultTableModel tableModel;
    private List<Product> productList;
    private List<Product> cartItems;
    private String email;

    public ProductListFrame(String email) {
        this.email = email;
        productList = ProductDB.getInstance().getProducts();
        cartItems = new ArrayList<>();

        setTitle("Product List - Scent & Style");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.ORANGE);
        setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("Product List");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitle, BorderLayout.NORTH);

        String[] columnNames = {"Product Name", "Category", "Price", "Stocks"};
        tableModel = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.ORANGE);
        btnAddToCart = new JButton("Add to Cart");
        btnBack = new JButton("Back");
        buttonPanel.add(btnAddToCart);
        buttonPanel.add(btnBack);
        add(buttonPanel, BorderLayout.SOUTH);

        btnAddToCart.addActionListener(e -> addToCartAction());
        btnBack.addActionListener(e -> goBack());

        loadProductData();
    }

    private void loadProductData() {
        tableModel.setRowCount(0);
        for (Product product : productList) {
            Object[] row = {product.getName(), product.getCategory(), product.getPrice(), product.getStock()};
            tableModel.addRow(row);
        }
    }

    private void addToCartAction() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow != -1) {
            String productName = (String) tableModel.getValueAt(selectedRow, 0);
            String productCategory = (String) tableModel.getValueAt(selectedRow, 1);
            Double productPrice = (Double) tableModel.getValueAt(selectedRow, 2);
            int productStock = (Integer) tableModel.getValueAt(selectedRow, 3);

            if (productStock == 0) {
                JOptionPane.showMessageDialog(this, "The Stock is Empty", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            ProductDB.getInstance().insertOrder(productName, productCategory, productPrice, productStock);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product to add to the order.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void goBack() {
        new UserDasdboard(email).setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new ProductListFrame("example@example.com").setVisible(true));
    }
}