package controller;

import dao.ContactDAO;
import model.Contact;
import model.User;
import ui.ContactDialog;
import ui.DashboardFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Controller for handling all business logic in the Dashboard.
 */
public class DashboardController {

    private DashboardFrame dashboardFrame;
    private User currentUser;
    private ContactDAO contactDAO;
    private List<Contact> currentContacts;

    public DashboardController(User user) {
        this.currentUser = user;
        this.contactDAO = new ContactDAO();
        this.dashboardFrame = new DashboardFrame(user);
        
        initController();
    }

    public void start() {
        dashboardFrame.setVisible(true);
        loadContacts();
    }

    private void initController() {
        // Top buttons
        dashboardFrame.getLogoutButton().addActionListener(e -> logoutAction());

        // Bottom buttons
        dashboardFrame.getAddContactButton().addActionListener(e -> addContactAction());
        dashboardFrame.getEditContactButton().addActionListener(e -> editContactAction());
        dashboardFrame.getDeleteContactButton().addActionListener(e -> deleteContactAction());
    }

    private void loadContacts() {
        currentContacts = contactDAO.getAllContacts(currentUser.getId());
        refreshTable();
    }

    private void refreshTable() {
        DefaultTableModel model = dashboardFrame.getTableModel();
        model.setRowCount(0); // Clear existing rows

        for (Contact c : currentContacts) {
            Object[] row = {
                c.getId(), // Hidden column
                c.getName(),
                c.getPhoneNumber(),
                c.getEmail() != null ? c.getEmail() : "",
                c.getAddress() != null ? c.getAddress() : ""
            };
            model.addRow(row);
        }
    }

    private void addContactAction() {
        ContactDialog dialog = new ContactDialog(dashboardFrame, "Add Contact", null);
        
        dialog.getSaveButton().addActionListener(e -> {
            if (validateAndSaveContact(dialog, null)) {
                dialog.dispose();
                loadContacts();
                JOptionPane.showMessageDialog(dashboardFrame, "Contact added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        dialog.getCancelButton().addActionListener(e -> dialog.dispose());
        dialog.setVisible(true);
    }

    private void editContactAction() {
        Contact selectedContact = getSelectedContact();
        if (selectedContact == null) {
            JOptionPane.showMessageDialog(dashboardFrame, "Please select a contact to edit.", "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ContactDialog dialog = new ContactDialog(dashboardFrame, "Edit Contact", selectedContact);
        
        dialog.getSaveButton().addActionListener(e -> {
            if (validateAndSaveContact(dialog, selectedContact)) {
                dialog.dispose();
                loadContacts();
                JOptionPane.showMessageDialog(dashboardFrame, "Contact updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        dialog.getCancelButton().addActionListener(e -> dialog.dispose());
        dialog.setVisible(true);
    }

    private boolean validateAndSaveContact(ContactDialog dialog, Contact existingContact) {
        String name = dialog.getNameInput();
        String phone = dialog.getPhoneInput();
        String email = dialog.getEmailInput();
        String address = dialog.getAddressInput();
        boolean favorite = false; // Simplified

        // 1. Validation
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(dialog, "Name is required.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (phone.isEmpty()) {
            JOptionPane.showMessageDialog(dialog, "Phone number is required.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // 3. Save or Update
        if (existingContact == null) {
            Contact newContact = new Contact(currentUser.getId(), name, phone, email, address, favorite);
            return contactDAO.addContact(newContact);
        } else {
            existingContact.setName(name);
            existingContact.setPhoneNumber(phone);
            existingContact.setEmail(email);
            existingContact.setAddress(address);
            existingContact.setFavorite(favorite);
            return contactDAO.updateContact(existingContact);
        }
    }

    private void deleteContactAction() {
        Contact selectedContact = getSelectedContact();
        if (selectedContact == null) {
            JOptionPane.showMessageDialog(dashboardFrame, "Please select a contact to delete.", "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(dashboardFrame, 
            "Are you sure you want to delete '" + selectedContact.getName() + "'?", 
            "Confirm Delete", JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            if (contactDAO.deleteContact(selectedContact.getId(), currentUser.getId())) {
                loadContacts();
                JOptionPane.showMessageDialog(dashboardFrame, "Contact deleted successfully.", "Deleted", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(dashboardFrame, "Failed to delete contact.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void logoutAction() {
        int confirm = JOptionPane.showConfirmDialog(dashboardFrame, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dashboardFrame.dispose();
            new AuthController().start();
        }
    }

    private Contact getSelectedContact() {
        JTable table = dashboardFrame.getContactsTable();
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) return null;
        
        // Convert view index to model index in case the table is sorted
        int modelRow = table.convertRowIndexToModel(selectedRow);
        int contactId = (int) dashboardFrame.getTableModel().getValueAt(modelRow, 0);
        
        return contactDAO.getContactById(contactId, currentUser.getId());
    }
}
