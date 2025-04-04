/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Map;
import javax.swing.JOptionPane;
/**
 *
 * @author Frouen Junior
 */
public class LoginDatabase {
    private static volatile LoginDatabase instance;
    
    public static LoginDatabase getInstance(){
        if (instance==null){
            instance = new LoginDatabase();
        }
        
        return instance;
    }
    
    public String loginUser(String email,String password){
        
        String excecuteLoginSQL = JavaSQLiteDB.loginSql;
        String SQLDb = JavaSQLiteDB.database_url;
        
        try(Connection conn=DriverManager.getConnection(SQLDb);
                PreparedStatement db = conn.prepareStatement(excecuteLoginSQL)){
            db.setString(1, email);
            
            ResultSet rs = db.executeQuery();
            if(rs.next()){
                String storedPW = rs.getString("password");
                String role = rs.getString("role");
                
                if(storedPW.equals(password)){
                   
                    return role;
                }
            }
            
            
        } catch(SQLException e){
            System.out.println("Error during login" + e.getMessage());
        }
        
        return null;
       
    }
    
}
