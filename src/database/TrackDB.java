package database;

import com.scentstyle.model.TrackingModel;

import javax.swing.*;
import java.sql.*;

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
}