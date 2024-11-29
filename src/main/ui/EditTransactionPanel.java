package ui;

import model.Category;
import model.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// ** SOURCE I REFERENCED TO HELP WITH THIS PHASE!!! ** //
// Link: https://www.youtube.com/watch?v=Kmgo00avvEw //
// A bit long but went real in depth to many different components //
// of swing and made this whole phase way easier than hitting //
// stackoverflow every 5 minutes //

public class EditTransactionPanel extends JPanel implements ActionListener {

    private FinanceTrackerGUI mainApp;
    private JTextField transactionIdField;
    private JButton searchButton;
    private JTextField amountField;
    private JTextField descriptionField;
    private JComboBox<String> categoryComboBox;
    private JButton updateButton;
    private JButton deleteButton;
    private Transaction currentTransaction;

    // REQUIRES: mainApp is not null
    // EFFECTS: constructs the EditTransactionPanel
    public EditTransactionPanel(FinanceTrackerGUI mainApp) {
        this.mainApp = mainApp;
        setLayout(new GridLayout(7, 2));
        initializeComponents();
    }

    // MODIFIES: this
    // EFFECTS: initializes and adds components to the panel
    private void initializeComponents() {
        componentHelper1();
        componentHelper2();
    }

    private void componentHelper2() {
        add(new JLabel("Category:"));
        categoryComboBox = new JComboBox<>();
        categoryComboBox.setEnabled(false);
        add(categoryComboBox);

        updateButton = new JButton("Update");
        updateButton.setEnabled(false);
        updateButton.addActionListener(this);
        deleteButton = new JButton("Delete");
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(this);

        add(updateButton);
        add(deleteButton);

        searchButton.setActionCommand("search");
        updateButton.setActionCommand("update");
        deleteButton.setActionCommand("delete");
    }

    private void componentHelper1() {
        add(new JLabel("Transaction ID:"));
        transactionIdField = new JTextField();
        add(transactionIdField);

        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        add(new JLabel());
        add(searchButton);

        add(new JLabel("Amount:"));
        amountField = new JTextField();
        amountField.setEnabled(false);
        add(amountField);

        add(new JLabel("Description:"));
        descriptionField = new JTextField();
        descriptionField.setEnabled(false);
        add(descriptionField);
    }

    // MODIFIES: this
    // EFFECTS: refreshes the categories in the combo box
    public void refreshTransactions() {
        categoryComboBox.removeAllItems();
        for (Category category : mainApp.getCategories().values()) {
            categoryComboBox.addItem(category.getName());
        }
    }

    // MODIFIES: this, currentTransaction
    // EFFECTS: Handles button click events based on the ActionCommand.
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "search":
                handleSearch();
                break;
            case "update":
                handleUpdate();
                break;
            case "delete":
                handleDelete();
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: searches for a transaction and populates fields
    private void handleSearch() {
        try {
            int transactionId = Integer.parseInt(transactionIdField.getText());
            currentTransaction = findTransaction(transactionId);
            if (currentTransaction != null) {
                amountField.setText(String.valueOf(currentTransaction.getAmount()));
                descriptionField.setText(currentTransaction.getDescription());
                refreshTransactions();
                categoryComboBox.setSelectedItem(currentTransaction.getCategory().getName());

                amountField.setEnabled(true);
                descriptionField.setEnabled(true);
                categoryComboBox.setEnabled(true);
                updateButton.setEnabled(true);
                deleteButton.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(EditTransactionPanel.this,
                        "Transaction not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(EditTransactionPanel.this,
                    "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: currentTransaction
    // EFFECTS: updates the transaction with new details
    private void handleUpdate() {
        try {
            int newAmount = Integer.parseInt(amountField.getText());
            String newDescription = descriptionField.getText();
            String newCategoryName = (String) categoryComboBox.getSelectedItem();
            Category newCategory = mainApp.findCategory(newCategoryName);

            isCatInvalid(newCategory);

            boolean isUpdated = false;

            isUpdated = hasAmtChanged(newAmount, isUpdated);
            isUpdated = hasDescChanged(newDescription, isUpdated);
            isUpdated = hasCatChanged(newCategory, isUpdated);

            if (isUpdated) {
                JOptionPane.showMessageDialog(EditTransactionPanel.this,
                        "Transaction updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(EditTransactionPanel.this,
                        "No changes detected.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(EditTransactionPanel.this,
                    "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void isCatInvalid(Category newCategory) {
        if (newCategory == null) {
            JOptionPane.showMessageDialog(EditTransactionPanel.this,
                    "Category not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    private boolean hasCatChanged(Category newCategory, boolean isUpdated) {
        if (!newCategory.equals(currentTransaction.getCategory())) {
            currentTransaction.moveTransaction(newCategory);
            isUpdated = true;
        }
        return isUpdated;
    }

    private boolean hasDescChanged(String newDescription, boolean isUpdated) {
        if (!newDescription.equals(currentTransaction.getDescription())) {
            currentTransaction.setDescription(newDescription);
            isUpdated = true;
        }
        return isUpdated;
    }

    private boolean hasAmtChanged(int newAmount, boolean isUpdated) {
        if (newAmount != currentTransaction.getAmount()) {
            currentTransaction.getCategory().setBudget(
                    currentTransaction.getCategory().getBudget() + currentTransaction.getAmount());
            currentTransaction.setAmount(newAmount);
            currentTransaction.getCategory().setBudget(
                    currentTransaction.getCategory().getBudget() - newAmount);
            isUpdated = true;
        }
        return isUpdated;
    }

    // MODIFIES: mainApp
    // EFFECTS: deletes the current transaction
    private void handleDelete() {
        int confirm = JOptionPane.showConfirmDialog(EditTransactionPanel.this,
                "Are you sure you want to delete this transaction?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            currentTransaction.deleteTransaction();
            JOptionPane.showMessageDialog(EditTransactionPanel.this,
                    "Transaction deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            resetFields();
        }
    }

    // EFFECTS: resets the input fields
    private void resetFields() {
        transactionIdField.setText("");
        amountField.setText("");
        descriptionField.setText("");
        amountField.setEnabled(false);
        descriptionField.setEnabled(false);
        categoryComboBox.setEnabled(false);
        updateButton.setEnabled(false);
        deleteButton.setEnabled(false);
    }

    // EFFECTS: finds and returns the transaction with the given ID
    private Transaction findTransaction(int id) {
        for (Category category : mainApp.getCategories().values()) {
            if (category.getTransactions().containsKey(id)) {
                return category.getTransactions().get(id);
            }
        }
        return null;
    }
}
