package database;

import com.scentstyle.model.TrackingModel;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrackDB {

    private static volatile TrackDB instance;

    public static TrackDB getInstance() {
        if (instance == null) {
            instance = new TrackDB();
        }
        return instance;
    }

    // Method to get order details by track ID
    public TrackingModel getOrderDetails(int trackID) {
        String SQLDb = JavaSQLiteDB.database_url;
        String query = "SELECT * FROM TrackingTable WHERE track_id = ?";

        try (Connection conn = DriverManager.getConnection(SQLDb);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, trackID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int orderId = rs.getInt("track_id");
                String productName = rs.getString("product_name");
                String category = rs.getString("category");
                double totalPrice = rs.getDouble("total_price");
                String customerName = rs.getString("customer_name");
                String orderDate = rs.getString("order_date");
                String status = rs.getString("status");
                int quantity = rs.getInt("quantity");
                double payAmount = rs.getDouble("pay_amount");
                String paymentOption = rs.getString("payment_option");

                return new TrackingModel(orderId, productName, category, totalPrice, customerName, orderDate, status, quantity, payAmount, paymentOption);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }

    // Get all orders
    public List<TrackingModel> getAllOrders() {
        List<TrackingModel> orderList = new ArrayList<>();
        String SQLDb = JavaSQLiteDB.database_url;
        String query = "SELECT * FROM TrackingTable";

        try (Connection conn = DriverManager.getConnection(SQLDb);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int orderId = rs.getInt("track_id");
                String productName = rs.getString("product_name");
                String category = rs.getString("category");
                double totalPrice = rs.getDouble("total_price");
                String customerName = rs.getString("customer_name");
                String orderDate = rs.getString("order_date");
                String status = rs.getString("status");
                int quantity = rs.getInt("quantity");
                double payAmount = rs.getDouble("pay_amount");
                String paymentOption = rs.getString("payment_option");

                orderList.add(new TrackingModel(orderId, productName, category, totalPrice, customerName, orderDate, status, quantity, payAmount, paymentOption));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return orderList;
    }

    // Delete an order
    public void deleteOrder(int orderID) {
        String SQLDb = JavaSQLiteDB.database_url;
        String query = "DELETE FROM TrackingTable WHERE track_id = ?";

        try (Connection conn = DriverManager.getConnection(SQLDb);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderID);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Order deleted successfully!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Update order status
    public void updateOrderStatus(int orderID, String newStatus) {
        String SQLDb = JavaSQLiteDB.database_url;
        String query = "UPDATE TrackingTable SET status = ? WHERE track_id = ?";

        try (Connection conn = DriverManager.getConnection(SQLDb);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, newStatus);
            stmt.setInt(2, orderID);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Order status updated successfully!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}