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
public class EditTransactionPanel extends JPanel {

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
    }

    // MODIFIES: this
    // EFFECTS: refreshes the categories in the combo box
    public void refreshTransactions() {
    }

    // Listener class for searching transactions
    private class SearchTransactionListener implements ActionListener {
    }

    // Listener class for updating transactions
    private class UpdateTransactionListener implements ActionListener {
        // MODIFIES: currentTransaction
        // EFFECTS: updates the transaction with new details
        public void actionPerformed(ActionEvent e) {
        }
    }

    // Listener class for deleting transactions
    private class DeleteTransactionListener implements ActionListener {
    }

    // EFFECTS: resets the input fields
    private void resetFields() {
    }

    // EFFECTS: finds and returns the transaction with the given ID
    private Transaction findTransaction(int id) {
    }
}
