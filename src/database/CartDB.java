package database;

import com.scentstyle.model.CartModel;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartDB {

    private static volatile CartDB instance;

    public static CartDB getInstance() {
        if (instance == null) {
            instance = new CartDB();
        }
        return instance;
    }

    // Get the instance of the cart
    public List<CartModel> getCartItems() {
        List<CartModel> cartList = new ArrayList<>();
        String query = JavaSQLiteDB.cartSQL;
        String SQLDb = JavaSQLiteDB.database_url;

        try (Connection conn = DriverManager.getConnection(SQLDb);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CartModel cartItem = new CartModel(
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                );
                cartList.add(cartItem);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return cartList;
    }

    public void insertOrder(HashMap<String, Object> orderDetails) {
        String SQLDb = JavaSQLiteDB.database_url;  // Database URL
        String query = JavaSQLiteDB.insertTrackingSQL;  // Query to insert order

        try (Connection conn = DriverManager.getConnection(SQLDb);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set parameters for the prepared statement
            int trackingNumber = generateTrackingNumber();  // Generate tracking number
            stmt.setInt(1, trackingNumber);  // Set tracking number
            stmt.setString(2, (String) orderDetails.get("product_name"));  // Set product name
            stmt.setString(3, (String) orderDetails.get("category"));  // Set category
            stmt.setDouble(4, (double) orderDetails.get("total_price"));  // Set total price
            stmt.setString(5, (String) orderDetails.get("customer_name"));  // Set customer name
            stmt.setString(6, (String) orderDetails.get("order_date"));  // Set order date
            stmt.setString(7, (String) orderDetails.get("status"));  // Set status
            stmt.setInt(8, (int) orderDetails.get("quantity"));  // Set quantity
            stmt.setDouble(9, (double) orderDetails.get("pay_amount"));  // Set payment amount
            stmt.setString(10, (String) orderDetails.get("payment_option"));  // Set payment option


         int rowsInserted = stmt.executeUpdate();  // Execute the insert statement
            // Delete the cart item after successful insertion
            deleteCartItem((int) orderDetails.get("order_id"));  // Delete the cart item using order_id
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Order placed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(null, "Your Tracking number is: " + trackingNumber, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to place order.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to delete a cart item by product_id
    public void deleteCartItem(int productId) {
        String SQLDb = JavaSQLiteDB.database_url;  // Database URL
        String query = "DELETE FROM OrderTable WHERE order_id = ?";  // Query to delete cart item

        try (Connection conn = DriverManager.getConnection(SQLDb);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, productId);  // Set product_id parameter
            stmt.executeUpdate();  // Execute the delete statement

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Get product_id by product_name from the database
    private int getProductIdByName(String productName) {
        String SQLDb = JavaSQLiteDB.database_url;  // Database URL
        String query = "SELECT product_id FROM Product WHERE product_name = ?";  // Query to fetch product_id by name

        try (Connection conn = DriverManager.getConnection(SQLDb);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, productName);  // Set product name parameter
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("product_id");  // Return the product_id
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return -1;  // Return -1 if product is not found
    }

    // Method to generate a random tracking number
    private int generateTrackingNumber() {
        int min = 123456789; // Minimum value (inclusive)
        int max = 999999999; // Maximum value (inclusive)
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}