package com.scentstyle.gui;

import database.LoginDatabase;
import database.RegisterUser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LoginFrame extends JFrame {
    private JTextField txtEmail;
    private JPasswordField txtPassword, txtConfirmPassword;
    private JButton btnLogin, btnRegister;
    private JRadioButton rdoUser, rdoAdmin;
    private ButtonGroup roleGroup;

    public LoginFrame() {
        setTitle("Login - Scent & Style");
        setSize(450, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        getContentPane().setBackground(Color.ORANGE);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(50, 50, 120, 25);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(180, 50, 200, 25);
        add(txtEmail);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 90, 120, 25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(180, 90, 200, 25);
        add(txtPassword);

        JLabel lblConfirmPassword = new JLabel("Confirm Password:");
        lblConfirmPassword.setBounds(50, 130, 120, 25);
        add(lblConfirmPassword);

        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setBounds(180, 130, 200, 25);
        add(txtConfirmPassword);

        rdoUser = new JRadioButton("User", true);
        rdoUser.setBackground(Color.ORANGE);
        rdoUser.setOpaque(true);
        rdoUser.setBounds(180, 160, 80, 25);
        add(rdoUser);

        rdoAdmin = new JRadioButton("Admin");
        rdoAdmin.setBackground(Color.ORANGE);
        rdoAdmin.setOpaque(true);
        rdoAdmin.setBounds(260, 160, 80, 25);
        add(rdoAdmin);

        roleGroup = new ButtonGroup();
        roleGroup.add(rdoUser);
        roleGroup.add(rdoAdmin);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(50, 200, 100, 30);
        add(btnLogin);

        btnRegister = new JButton("Register");
        btnRegister.setBounds(200, 200, 100, 30);
        add(btnRegister);

        btnLogin.addActionListener(e -> loginAction());
        btnRegister.addActionListener(e -> registerAction());
    }

    private void loginAction() {
        String email = txtEmail.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in both email and password.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String role = LoginDatabase.getInstance().loginUser(email, password);
        if (role == null) {
            JOptionPane.showMessageDialog(this, "Invalid email or password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
        } else if (role.equals("Admin")) {
            new AdminDashboard().setVisible(true);
            dispose();
        } else {
            new UserDasdboard(email).setVisible(true);
            dispose();
        }
    }

    private void registerAction() {
        String email = txtEmail.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();
        String confirmPassword = new String(txtConfirmPassword.getPassword()).trim();
        String role = rdoAdmin.isSelected() ? "Admin" : "User";

        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (RegisterUser.getInstance().emailExist(email)) {
            JOptionPane.showMessageDialog(this, "Email already registered. Please use a different email.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        Map <String, String> meow = new HashMap<>();
         meow.put("email", email);
        meow.put("password", password);
        meow.put("role", role);
        RegisterUser.getInstance().InsertUser(meow);
       /* if (registerUser(email, password, role)) {
            JOptionPane.showMessageDialog(this, "Registration successful! You can now log in.");
        } else {
            JOptionPane.showMessageDialog(this, "Error occurred while registering. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
     */
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        jPasswordField1.setText("jPasswordField1");

        jButton1.setText("jButton1");

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}