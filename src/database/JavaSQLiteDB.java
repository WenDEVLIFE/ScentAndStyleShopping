package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JavaSQLiteDB {
    // database url
    public static String database_url = "jdbc:sqlite:shopdatabase.db";

    // insert user
    public static String insertUser = "INSERT INTO Users (email, password, role) VALUES (?, ?, ?)";

    // check if the email exists
    public static String checkEmail = "SELECT COUNT(*) AS count FROM Users WHERE email = ?";

    // This will login the user from the database
    public static String loginSql = "SELECT password, role FROM Users WHERE email = ?";

    // get the product
    public static String productSql = "SELECT product_id, product_name, category, price, stock FROM Product";

    // insert the order
    public static String orderSql = "INSERT INTO OrderTable (product_id, product_name, category, price, quantity) VALUES (?, ?, ?, ?, ?)";

    // get the orders
    public static String cartSQL = "SELECT order_id, product_id, product_name, category, price, quantity FROM OrderTable";

    // This will insert to the tracking table
    public static String insertTrackingSQL = "INSERT INTO TrackingTable (track_id, product_name, category, total_price, customer_name, order_date, status, quantity, pay_amount, payment_option) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    // This will get the tracking table
    public static String trackingSQL = "SELECT track_id, product_name, category, total_price, customer_name, order_date, status, quantity, pay_amount, payment_option FROM TrackingTable";

    public static void main(String[] args) {
        connection();
    }

    public static void connection() {
        Connection conn = null;

        try {
            database_url = "jdbc:sqlite:shopdatabase.db";
            conn = DriverManager.getConnection(database_url);
            System.out.println("SQLite Initialize");
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                System.out.print(e.getMessage());
            }
        }
    }
}