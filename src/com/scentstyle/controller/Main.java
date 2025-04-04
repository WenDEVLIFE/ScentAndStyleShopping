package com.scentstyle.controller;

import com.scentstyle.gui.AdminDashboard;
import com.scentstyle.gui.CartFrame;
import com.scentstyle.gui.LoginFrame;
import com.scentstyle.gui.ProductListFrame;
import com.scentstyle.gui.UserDasdboard;
import database.JavaSQLiteDB;

public class Main {
    public static void main(String[] args) {
       // Display the Login Frame at the start
       showLoginFrame();
       JavaSQLiteDB obj = new JavaSQLiteDB();
       obj.connection();
    }
    
      // Show Login Frame
    public static void showLoginFrame() {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
        loginFrame.setLocationRelativeTo(null);
    }

    // Show Admin Dashboard after successful admin login
    public static void showAdminDashboard() {
        AdminDashboard adminDashboard = new AdminDashboard();
        adminDashboard.setVisible(true);
        adminDashboard.setLocationRelativeTo(null);
    }
}
