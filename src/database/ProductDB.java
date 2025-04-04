/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import com.scentstyle.model.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
/**
 *
 * @author Frouen Junior
 */
public class ProductDB {
    private static volatile ProductDB instance;
    
    public static  ProductDB getInstance(){
        if(instance==null){
            instance = new ProductDB();
        }
     return instance;
    }
    
      public List<Product> getProducts() {
         List<Product> productList = new ArrayList<>();
        String query = JavaSQLiteDB.productSql;
         String SQLDb = JavaSQLiteDB.database_url;

        try (Connection conn = DriverManager.getConnection(SQLDb);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) { // Corrected syntax issue

            while (rs.next()) {
                Product product = new Product(
                    rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getString("category"),
                    rs.getFloat("price"),
                    rs.getInt("stock")
                );
                productList.add(product);
                  JOptionPane.showMessageDialog(null, "Database is intialize", "success", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return productList;
    }
    
}
