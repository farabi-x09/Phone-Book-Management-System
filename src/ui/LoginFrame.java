package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class LoginFrame extends JFrame {

    // Define colors for the dark theme
    private static final Color BG_COLOR = new Color(30, 30, 30);
    private static final Color PANEL_COLOR = new Color(45, 45, 45);
    private static final Color TEXT_COLOR = new Color(230, 230, 230);
    private static final Color BUTTON_BG = new Color(0, 122, 204);
    private static final Color BUTTON_FG = Color.WHITE;
    private static final Font MAIN_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 24);

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginFrame() {
        setTitle("Phone Book - Login");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setResizable(false);
        
        initComponents();
    }

    private void initComponents() {
        // Main container panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BG_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title Label
        JLabel titleLabel = new JLabel("Welcome Back", SwingUtilities.CENTER);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Form Panel (GridBagLayout for centered, aligned fields)
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(PANEL_COLOR);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(60, 60, 60), 1, true),
            BorderFactory.createEmptyBorder(30, 20, 30, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.weightx = 1.0;

        // Username Label & Field
        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(TEXT_COLOR);
        userLabel.setFont(MAIN_FONT);
        gbc.gridy = 0;
        formPanel.add(userLabel, gbc);

        usernameField = new JTextField(20);
        styleTextField(usernameField);
        gbc.gridy = 1;
        formPanel.add(usernameField, gbc);

        // Password Label & Field
        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(TEXT_COLOR);
        passLabel.setFont(MAIN_FONT);
        gbc.gridy = 2;
        formPanel.add(passLabel, gbc);

        passwordField = new JPasswordField(20);
        styleTextField(passwordField);
        gbc.gridy = 3;
        formPanel.add(passwordField, gbc);

        // Login Button
        loginButton = new JButton("Login");
        styleButton(loginButton, BUTTON_BG, BUTTON_FG);
        gbc.gridy = 4;
        gbc.insets = new Insets(30, 10, 10, 10); // extra top margin
        formPanel.add(loginButton, gbc);

        // Register Button
        registerButton = new JButton("Don't have an account? Register");
        styleButton(registerButton, PANEL_COLOR, new Color(100, 150, 255));
        registerButton.setBorderPainted(false); // Make it look like a text link
        gbc.gridy = 5;
        gbc.insets = new Insets(0, 10, 10, 10);
        formPanel.add(registerButton, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private void styleTextField(JTextField field) {
        field.setBackground(new Color(60, 60, 60));
        field.setForeground(TEXT_COLOR);
        field.setCaretColor(TEXT_COLOR);
        field.setFont(MAIN_FONT);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(80, 80, 80)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
    }

    private void styleButton(JButton button, Color bg, Color fg) {
        button.setBackground(bg);
        button.setForeground(fg);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    // Getters for controller access later
    public JButton getLoginButton() { return loginButton; }
    public JButton getRegisterButton() { return registerButton; }
    public String getUsername() { return usernameField.getText(); }
    public String getPassword() { return new String(passwordField.getPassword()); }
}
