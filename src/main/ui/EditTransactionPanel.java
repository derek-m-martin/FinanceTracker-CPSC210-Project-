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

// A panel to edit or delete existing transactions
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

    private void handleSearch() {

    }

    private void handleUpdate() {
        
    }

    private void handleDelete() {
        
    }

    // EFFECTS: resets the input fields
    private void resetFields() {
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
