package ui;

import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

/**
 * The main Dashboard UI for managing contacts.
 */
public class DashboardFrame extends JFrame {

    private User currentUser;

    // Theme Colors
    private static final Color BG_COLOR = new Color(30, 30, 30);
    private static final Color PANEL_COLOR = new Color(45, 45, 45);
    private static final Color TEXT_COLOR = new Color(230, 230, 230);
    private static final Color BUTTON_BG = new Color(0, 122, 204);
    private static final Color BUTTON_FG = Color.WHITE;
    private static final Color TABLE_HEADER_BG = new Color(60, 60, 60);

    // UI Components
    private JButton logoutButton;
    private JTable contactsTable;
    private DefaultTableModel tableModel;
    private JButton addContactButton;
    private JButton editContactButton;
    private JButton deleteContactButton;

    public DashboardFrame(User user) {
        this.currentUser = user;
        
        setTitle("Phone Book - Dashboard");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
    }

    private void initComponents() {
        // Main container
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(BG_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- TOP PANEL ---
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(BG_COLOR);

        JLabel welcomeLabel = new JLabel("Welcome, " + currentUser.getUsername());
        welcomeLabel.setForeground(TEXT_COLOR);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        topPanel.add(welcomeLabel, BorderLayout.WEST);

        // Top-Right Panel for Logout
        JPanel topRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        topRightPanel.setBackground(BG_COLOR);

        logoutButton = new JButton("Logout");
        styleButton(logoutButton, new Color(200, 50, 50), Color.WHITE); // Red color for logout

        topRightPanel.add(logoutButton);

        topPanel.add(topRightPanel, BorderLayout.EAST);

        // --- CENTER PANEL (TABLE) ---
        String[] columns = {"ID", "Name", "Phone", "Email", "Address"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells read-only
            }
        };

        contactsTable = new JTable(tableModel);
        contactsTable.setBackground(PANEL_COLOR);
        contactsTable.setForeground(TEXT_COLOR);
        contactsTable.setGridColor(new Color(70, 70, 70));
        contactsTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        contactsTable.setRowHeight(30);
        contactsTable.setSelectionBackground(BUTTON_BG);
        contactsTable.setSelectionForeground(Color.WHITE);
        contactsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        contactsTable.setAutoCreateRowSorter(true); // Enable row sorting

        // Hide ID column
        contactsTable.getColumnModel().getColumn(0).setMinWidth(0);
        contactsTable.getColumnModel().getColumn(0).setMaxWidth(0);
        contactsTable.getColumnModel().getColumn(0).setWidth(0);

        // Adjust column widths
        contactsTable.getColumnModel().getColumn(1).setPreferredWidth(150); // Name
        contactsTable.getColumnModel().getColumn(2).setPreferredWidth(120); // Phone
        contactsTable.getColumnModel().getColumn(3).setPreferredWidth(180); // Email
        contactsTable.getColumnModel().getColumn(4).setPreferredWidth(200); // Address

        // Style the table header
        JTableHeader tableHeader = contactsTable.getTableHeader();
        tableHeader.setBackground(TABLE_HEADER_BG);
        tableHeader.setForeground(TEXT_COLOR);
        tableHeader.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tableHeader.setBorder(BorderFactory.createLineBorder(TABLE_HEADER_BG));

        JScrollPane scrollPane = new JScrollPane(contactsTable);
        scrollPane.getViewport().setBackground(PANEL_COLOR);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60)));

        // --- BOTTOM PANEL ---
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        bottomPanel.setBackground(BG_COLOR);

        addContactButton = new JButton("Add Contact");
        styleButton(addContactButton, BUTTON_BG, BUTTON_FG);

        editContactButton = new JButton("Edit Contact");
        styleButton(editContactButton, PANEL_COLOR, TEXT_COLOR);

        deleteContactButton = new JButton("Delete Contact");
        styleButton(deleteContactButton, new Color(200, 50, 50), Color.WHITE);

        bottomPanel.add(addContactButton);
        bottomPanel.add(editContactButton);
        bottomPanel.add(deleteContactButton);

        // Assemble panels
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void styleButton(JButton button, Color bg, Color fg) {
        button.setBackground(bg);
        button.setForeground(fg);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
    }

    // Getters for controller access
    public JButton getLogoutButton() { return logoutButton; }
    public JButton getAddContactButton() { return addContactButton; }
    public JButton getEditContactButton() { return editContactButton; }
    public JButton getDeleteContactButton() { return deleteContactButton; }
    public JTable getContactsTable() { return contactsTable; }
    public DefaultTableModel getTableModel() { return tableModel; }
}
