package controller;

import dao.UserDAO;
import model.User;
import ui.LoginFrame;
import ui.RegisterFrame;

import javax.swing.JOptionPane;

/**
 * Controller responsible for Authentication-related flows and navigation.
 * Adheres to MVC architecture by keeping navigation logic out of the UI forms.
 */
public class AuthController {

    private UserDAO userDAO;

    public AuthController() {
        this.userDAO = new UserDAO();
    }

    /**
     * Starts the application by displaying the Login screen.
     */
    public void start() {
        showLoginFrame();
    }

    /**
     * Initializes and displays the LoginFrame.
     * Sets up the navigation to the RegisterFrame.
     */
    private void showLoginFrame() {
        LoginFrame loginFrame = new LoginFrame();
        
        // Handle navigation: Login -> Register
        loginFrame.getRegisterButton().addActionListener(e -> {
            loginFrame.dispose(); // Close current window
            showRegisterFrame();  // Open new window
        });

        // Handle Login Action
        loginFrame.getLoginButton().addActionListener(e -> {
            String username = loginFrame.getUsername().trim();
            String password = loginFrame.getPassword();

            // Validate empty fields
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(loginFrame, 
                    "Username and password are required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Attempt login via DAO
            User loggedInUser = userDAO.login(username, password);

            if (loggedInUser != null) {
                // Success
                loginFrame.dispose();
                controller.DashboardController dashboardController = new controller.DashboardController(loggedInUser);
                dashboardController.start();
            } else {
                // Failure
                JOptionPane.showMessageDialog(loginFrame, 
                    "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        loginFrame.setVisible(true);
    }

    /**
     * Initializes and displays the RegisterFrame.
     * Sets up the navigation back to the LoginFrame and handles registration logic.
     */
    private void showRegisterFrame() {
        RegisterFrame registerFrame = new RegisterFrame();
        
        // Handle navigation: Register -> Login
        registerFrame.getBackToLoginButton().addActionListener(e -> {
            registerFrame.dispose(); // Close current window
            showLoginFrame();        // Open new window
        });

        // Handle Registration Action
        registerFrame.getRegisterButton().addActionListener(e -> {
            String username = registerFrame.getUsername().trim();
            String password = registerFrame.getPassword();
            String confirmPassword = registerFrame.getConfirmPassword();

            // 1. Validate empty fields
            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(registerFrame, 
                    "All fields are required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 2. Validate passwords match
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(registerFrame, 
                    "Passwords do not match!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 3. Validate username existence
            if (userDAO.usernameExists(username)) {
                JOptionPane.showMessageDialog(registerFrame, 
                    "Username already exists! Please choose another one.", "Registration Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 4. Register the user
            User newUser = new User(username, password);
            boolean success = userDAO.register(newUser);

            if (success) {
                // Show success message
                JOptionPane.showMessageDialog(registerFrame, 
                    "Registration successful! You can now login.", "Success", JOptionPane.INFORMATION_MESSAGE);
                
                // Return to login
                registerFrame.dispose();
                showLoginFrame();
            } else {
                JOptionPane.showMessageDialog(registerFrame, 
                    "Registration failed due to a database error.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        registerFrame.setVisible(true);
    }
}
