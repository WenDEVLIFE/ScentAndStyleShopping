package com.scentstyle.model;

import java.sql.*;

public class Database {
    public static Connection getConnection(){
        try {
            String url = "jcbc:mysql://localhost:3306/scentstyle_db";
            
            String user = "root";
            
            String password = "";
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            return DriverManager.getConnection(url, user, password);
            
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

