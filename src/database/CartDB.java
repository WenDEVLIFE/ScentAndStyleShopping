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

    // gt the instance of the cart
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
    }
}
