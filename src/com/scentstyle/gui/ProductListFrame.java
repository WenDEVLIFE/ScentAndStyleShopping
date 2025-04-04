package com.scentstyle.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.scentstyle.model.Product;
import database.ProductDB;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ProductListFrame extends JFrame {
    private JTable productTable;
    private JButton btnAddToCart, btnBack;
    private DefaultTableModel tableModel;
    private List<Product> productList;
    private List<Product> cartItems;

    public ProductListFrame() {
        productList = ProductDB.getInstance().getProducts();
        List<Product> cartItems = new ArrayList<Product>();

        setTitle("Product List - Scent & Style");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Table setup
        String[] columnNames = {"Product Name", "Category", "Price", "Stocks"};
        tableModel = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBounds(20, 20, 450, 150);
        add(scrollPane);

        // Buttons
        btnAddToCart = new JButton("Add to Cart");
        btnAddToCart.setBounds(50, 200, 120, 30);
        add(btnAddToCart);

        btnBack = new JButton("Back");
        btnBack.setBounds(200, 200, 120, 30);
        add(btnBack);
        
        loadData();
       loadProductData();
       

        // Button Listeners
        btnAddToCart.addActionListener(e -> addToCartAction());
        btnBack.addActionListener(e -> goBack());
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
        // Get the selected product details from the table
        String productName = (String) tableModel.getValueAt(selectedRow, 0);
        String productCategory = (String) tableModel.getValueAt(selectedRow, 1);
       Double productPrice = (Double) tableModel.getValueAt(selectedRow, 2);
        int productStock = (Integer) tableModel.getValueAt(selectedRow, 3);
        
        // Insert the selected product directly into the OrderTable
        ProductDB.getInstance().insertOrder(productName, productCategory, productPrice, productStock);
    } else {
        JOptionPane.showMessageDialog(this, "Please select a product to add to the order.", "Error", JOptionPane.WARNING_MESSAGE);
    }
}
    private void checkoutAction(){
        if (cartItems.isEmpty()){
         
            JOptionPane.showMessageDialog(this, "Cart is empty! Add items before checkout.", "Error", JOptionPane.WARNING_MESSAGE);    
        }else{
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to proseec with checkout?", "Confirm Checkout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION){
                
                JOptionPane.showMessageDialog(this, "Checkout successfull! Thank you for shopping with Scent & Style.");
                cartItems.clear();
               // loadProductData();
               
            }
        }
    }
    
    void loadData(){
        try{
            productList = ProductDB.getInstance().getProducts();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void goBack() {
        new UserDasdboard().setVisible(true);
        dispose();
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