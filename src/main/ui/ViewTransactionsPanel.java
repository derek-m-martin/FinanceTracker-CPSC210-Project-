package ui;

import model.Category;
import model.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// A panel to view, filter, and sort transactions
public class ViewTransactionsPanel extends JPanel {

    private FinanceTrackerGUI mainApp;
    private JTable transactionTable;
    private DefaultTableModel tableModel;
    private JComboBox<String> filterComboBox;
    private JButton filterButton;

    // REQUIRES: mainApp is not null
    // EFFECTS: constructs the ViewTransactionsPanel
    public ViewTransactionsPanel(FinanceTrackerGUI mainApp) {
        this.mainApp = mainApp;
        setLayout(new BorderLayout());
        initializeComponents();
        refreshTransactions();
    }

    // MODIFIES: this
    // EFFECTS: initializes and adds components to the panel
    private void initializeComponents() {
    }

    // MODIFIES: this
    // EFFECTS: refreshes the transactions displayed in the table
    public void refreshTransactions() {
    }

    // EFFECTS: applies the selected filter to the transactions
    private void applyFilter() {
    }
}
