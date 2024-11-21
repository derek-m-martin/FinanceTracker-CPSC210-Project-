package ui;

import model.Category;
import model.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// A panel that allows users to add new transactions
public class AddTransactionPanel extends JPanel {

    private FinanceTrackerGUI mainApp;
    private JTextField amountField;
    private JComboBox<String> categoryComboBox;
    private JTextField descriptionField;
    private JButton addButton;

    // REQUIRES: mainApp is not null
    // EFFECTS: constructs the AddTransactionPanel
    public AddTransactionPanel(FinanceTrackerGUI mainApp) {
    }

    // MODIFIES: this
    // EFFECTS: initializes and adds components to the panel
    private void initializeComponents() {
    }

    // MODIFIES: this
    // EFFECTS: refreshes the categories in the combo box
    public void refreshCategories() {
    }

    // Listener class for adding transactions
    private class AddTransactionListener implements ActionListener {
        // MODIFIES: mainApp
        public void actionPerformed(ActionEvent e) {
        }
    }
}
