/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import java.sql.Connection;
import  java.sql.SQLException;
import java.sql.DriverManager;

/**
 *
 * @author Frouen Junior
 */
public class JavaSQLiteDB {
    public static String database_url ="jdbc:sqlite:shopdatabase.db";
    public static String insertUser ="Insert INTO Users (email, password, role) VALUES(?,?,?)";
    
    public static void main(String[] args){
        connection();
    }
    public static void connection(){
        Connection conn = null;
        
        try{
            database_url ="jdbc:sqlite:shopdatabase.db";
            conn = DriverManager.getConnection(database_url);
            System.out.println("SQLite Initialize");
        } catch(SQLException e){
            System.out.print(e.getMessage());
        }
        finally{
            try{
                if (conn!=null)
                    conn.close();
            } catch(SQLException e){
                 System.out.print(e.getMessage());
            }
        }
    }
}
