package database;

import com.scentstyle.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProductDB {
    private static volatile ProductDB instance;

    public static ProductDB getInstance() {
        if (instance == null) {
            instance = new ProductDB();
        }
        return instance;
    }

    // Get the products
    public List<Product> getProducts() {
        List<Product> productList = new ArrayList<>();
        String query = JavaSQLiteDB.productSql;
        String SQLDb = JavaSQLiteDB.database_url;

        try (Connection conn = DriverManager.getConnection(SQLDb);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("category"),
                        rs.getFloat("price"),
                        rs.getInt("stock")
                );
                productList.add(product);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return productList;
    }

    // insert the orders from the database
    public void insertOrder(String productName, String category, double price, int stock) {
        String SQLDb = JavaSQLiteDB.database_url;  // Database URL
        String query = JavaSQLiteDB.orderSql;       // The SQL query for inserting into the OrderTable
        String updateStockQuery = "UPDATE Product SET stock = stock - ? WHERE product_name = ?"; // Query to update stock

        try (Connection conn = DriverManager.getConnection(SQLDb);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Insert values into the OrderTable
            stmt.setInt(1, getProductIdByName(productName));  // Get the product_id from the name
            stmt.setString(2, productName);                    // product_name
            stmt.setString(3, category);                       // category
            stmt.setFloat(4, (float) price);                   // price
            stmt.setInt(5, 1);                                 // Assuming quantity is always 1 for this example (you can modify if needed)

            // Execute the insertion
            stmt.executeUpdate();

            // Update the product stock after order is placed
            try (PreparedStatement updateStmt = conn.prepareStatement(updateStockQuery)) {
                updateStmt.setInt(1, 1);  // Decrease by 1 (adjust this if needed)
                updateStmt.setString(2, productName);  // Product name

                // Execute the stock update
                updateStmt.executeUpdate();
            }

            // Optionally show a success message
            JOptionPane.showMessageDialog(null, "Order placed successfully!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Insert a new product
    public void insertProduct(Product product) {
        String SQLDb = JavaSQLiteDB.database_url;
        String query = "INSERT INTO Product (product_id, product_name, category, price, stock) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(SQLDb);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, product.getProductID());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getCategory());
            stmt.setDouble(4, product.getPrice());
            stmt.setInt(5, product.getStock());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Product added successfully!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Update an existing product
    public void updateProduct(Product product) {
        String SQLDb = JavaSQLiteDB.database_url;
        String query = "UPDATE Product SET product_name = ?, category = ?, price = ?, stock = ? WHERE product_id = ?";

        try (Connection conn = DriverManager.getConnection(SQLDb);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, product.getName());
            stmt.setString(2, product.getCategory());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getStock());
            stmt.setInt(5, product.getProductID());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Product updated successfully!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Delete a product
    public void deleteProduct(int productID) {
        String SQLDb = JavaSQLiteDB.database_url;
        String query = "DELETE FROM Product WHERE product_id = ?";

        try (Connection conn = DriverManager.getConnection(SQLDb);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, productID);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Product deleted successfully!");

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
}