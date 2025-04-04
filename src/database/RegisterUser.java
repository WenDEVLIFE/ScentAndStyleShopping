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
public class RegisterUser {
    private static volatile RegisterUser instance;
    final String SQLiteDB = JavaSQLiteDB.database_url;
    
    
    public static RegisterUser getInstance(){
        if(instance==null){
            instance = new RegisterUser();
        }
        
        return instance;
    }
    
    public void InsertUser(Map<String, String> meow){
        
        String insertUsers = JavaSQLiteDB.insertUser;
        
        try(Connection conn = DriverManager.getConnection(SQLiteDB);
                PreparedStatement init = conn.prepareStatement(insertUsers)){
            
            String email = meow.get("email");
            String password = meow.get("password");
            String role = meow.get("role");
              init.setString(1, email);
               init.setString(2, password);
               init.setString(3, role);
               
               int rows= init.executeUpdate();
               
               if(rows>0){
                   JOptionPane.showMessageDialog(null, "Registration successful! You can now log in.");
               }
                    
                } catch(SQLException e){
                    System.out.print(e.getMessage());
                }
    }
    
    
    public boolean emailExist(String email){
        String checkEmails = JavaSQLiteDB.checkEmail;
        
        try(Connection conn=DriverManager.getConnection(SQLiteDB);
        PreparedStatement db = conn.prepareStatement(checkEmails)){
            
            db.setString(1, email);
            ResultSet rs = db.executeQuery();
            
            if(rs.next()){
                int count = rs.getInt("count");
                return count >0;
            }
            
        }catch(SQLException e){
            
        }
        
        return false;
    }
}
