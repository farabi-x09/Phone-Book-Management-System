package ui;

import model.Contact;

import javax.swing.*;
import java.awt.*;

/**
 * Dialog for adding or editing a contact.
 */
public class ContactDialog extends JDialog {

    private JTextField nameField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField addressField;
    
    private JButton saveButton;
    private JButton cancelButton;
    
    // Theme Colors
    private static final Color BG_COLOR = new Color(45, 45, 45);
    private static final Color TEXT_COLOR = new Color(230, 230, 230);
    private static final Font MAIN_FONT = new Font("Segoe UI", Font.PLAIN, 14);

    public ContactDialog(JFrame parent, String title, Contact contact) {
        super(parent, title, true);
        setSize(400, 350);
        setLocationRelativeTo(parent);
        setResizable(false);
        
        initComponents(contact);
    }

    private void initComponents(Contact contact) {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(BG_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.weightx = 0.3;

        // Name
        gbc.gridy = 0;
        mainPanel.add(createLabel("Name (*):"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        nameField = createTextField();
        mainPanel.add(nameField, gbc);

        // Phone
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.weightx = 0.3;
        mainPanel.add(createLabel("Phone (*):"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        phoneField = createTextField();
        mainPanel.add(phoneField, gbc);

        // Email
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.weightx = 0.3;
        mainPanel.add(createLabel("Email:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        emailField = createTextField();
        mainPanel.add(emailField, gbc);

        // Address
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.weightx = 0.3;
        mainPanel.add(createLabel("Address:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        addressField = createTextField();
        mainPanel.add(addressField, gbc);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(BG_COLOR);
        
        saveButton = new JButton("Save");
        styleButton(saveButton, new Color(0, 122, 204), Color.WHITE);
        
        cancelButton = new JButton("Cancel");
        styleButton(cancelButton, new Color(100, 100, 100), Color.WHITE);
        
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 0, 10);
        mainPanel.add(buttonPanel, gbc);

        // Populate fields if editing
        if (contact != null) {
            nameField.setText(contact.getName());
            phoneField.setText(contact.getPhoneNumber());
            emailField.setText(contact.getEmail() != null ? contact.getEmail() : "");
            addressField.setText(contact.getAddress() != null ? contact.getAddress() : "");
        }

        add(mainPanel);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(TEXT_COLOR);
        label.setFont(MAIN_FONT);
        return label;
    }

    private JTextField createTextField() {
        JTextField field = new JTextField(20);
        field.setBackground(new Color(60, 60, 60));
        field.setForeground(TEXT_COLOR);
        field.setCaretColor(TEXT_COLOR);
        field.setFont(MAIN_FONT);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(80, 80, 80)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return field;
    }

    private void styleButton(JButton button, Color bg, Color fg) {
        button.setBackground(bg);
        button.setForeground(fg);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
    }

    // Getters for controller
    public JButton getSaveButton() { return saveButton; }
    public JButton getCancelButton() { return cancelButton; }
    
    public String getNameInput() { return nameField.getText().trim(); }
    public String getPhoneInput() { return phoneField.getText().trim(); }
    public String getEmailInput() { return emailField.getText().trim(); }
    public String getAddressInput() { return addressField.getText().trim(); }
}
