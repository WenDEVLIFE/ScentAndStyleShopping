package com.scentstyle.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.scentstyle.model.Product;
import database.ProductDB;

import java.awt.*;
import java.util.List;

public class ProductManagement extends JFrame {
    private JTable productTable;
    private JButton btnAddProduct, btnEditProduct, btnDeleteProduct, btnBack;
    private DefaultTableModel tableModel;
    private List<Product> productList;
    
    public ProductManagement(){
        // Sample product list for demonstration
        productList = ProductDB.getInstance().getProducts();
        
        setTitle("Product Management - Scent & Style");
        setSize(600, 350);
        setLocationRelativeTo(null);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.ORANGE);
        setLayout(null);

        // Table setup
        JLabel lblTitle = new JLabel("Product List");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(200, 10, 200, 30);
        add(lblTitle);

        String[] columnNames = {"Product ID","Product Name", "Category", "Price", "Stocks"};
        tableModel = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBounds(20, 40, 550, 150);
        add(scrollPane);
        
        btnAddProduct = new JButton("Add Product");
        btnAddProduct.setBounds(50, 200, 120, 30);
        add(btnAddProduct);
        
        btnEditProduct = new JButton("Edit Product");
        btnEditProduct.setBounds(200, 200, 120, 30);
        add(btnEditProduct);
        
        btnDeleteProduct = new JButton("Delete Product");
        btnDeleteProduct.setBounds(350, 200, 150, 30);
        add(btnDeleteProduct);
        
        btnBack = new JButton("Back");
        btnBack.setBounds(250, 250, 120, 30);
        add(btnBack);

        loadData();
        loadProductData();
        
        btnAddProduct.addActionListener(e -> addProductAction());
        btnEditProduct.addActionListener(e -> editProductAction());
        btnDeleteProduct.addActionListener(e -> deleteProductAction());
        btnBack.addActionListener(e -> goBack());
    }
    
    void loadData(){
        try{
            productList = ProductDB.getInstance().getProducts();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void loadProductData() {
        tableModel.setRowCount(0);
        for (Product product : productList) {
            Object [] row = {product.getProductID(), product.getName(), product.getCategory(), product.getPrice(), product.getStock()};
            tableModel.addRow(row);
        }
    }

    private void addProductAction() {
        String name = JOptionPane.showInputDialog(this, "Enter product name:");
        String category = JOptionPane.showInputDialog(this, "Enter product category:");
        double price;
        int stock;

        try {
            price = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter product price:"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid price. Please enter a valid number.");
            return;
        }

        try {
            stock = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter product stock:"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid stock. Please enter a valid number.");
            return;
        }

        if (name != null && category != null && !name.trim().isEmpty() && !category.trim().isEmpty()) {
            int productID = generateProductID();
            Product newProduct = new Product(productID, name.trim(), category.trim(), (float) price, stock);
            ProductDB.getInstance().insertProduct(newProduct);
            productList.add(newProduct);
            loadProductData();
        } else {
            JOptionPane.showMessageDialog(this, "Product information cannot be empty.");
        }
    }

    private void editProductAction() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow != -1) {
            int productID = (int) tableModel.getValueAt(selectedRow, 0);
            Product selectedProduct = findProductByID(productID);

            if (selectedProduct != null) {
                String newName = JOptionPane.showInputDialog(this, "Enter new product name:", selectedProduct.getName());
                String newCategory = JOptionPane.showInputDialog(this, "Enter new product category:", selectedProduct.getCategory());
                double newPrice;
                int newStock;

                try {
                    newPrice = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter new product price:", selectedProduct.getPrice()));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid price. Please enter a valid number.");
                    return;
                }

                try {
                    newStock = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter new product stock:", selectedProduct.getStock()));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid stock. Please enter a valid number.");
                    return;
                }

                if (newName != null && newCategory != null && !newName.trim().isEmpty() && !newCategory.trim().isEmpty()) {
                    selectedProduct.setName(newName.trim());
                    selectedProduct.setCategory(newCategory.trim());
                    selectedProduct.setPrice((float) newPrice);
                    selectedProduct.updateStock(newStock);
                    ProductDB.getInstance().updateProduct(selectedProduct);
                    loadProductData();
                } else {
                    JOptionPane.showMessageDialog(this, "Product information cannot be empty.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product to edit.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteProductAction() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow != -1) {
            int productID = (int) tableModel.getValueAt(selectedRow, 0);
            ProductDB.getInstance().deleteProduct(productID);
            productList.removeIf(product -> product.getProductID() == productID);
            loadProductData();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product to delete.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void goBack() {
        new AdminDashboard().setVisible(true);
        dispose();
    }

    private int generateProductID() {
        return productList.isEmpty() ? 1 : productList.stream().mapToInt(Product::getProductID).max().getAsInt() + 1;
    }

    private Product findProductByID(int productID) {
        for (Product product : productList) {
            if (product.getProductID() == productID) {
                return product;
            }
        }
        return null;
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