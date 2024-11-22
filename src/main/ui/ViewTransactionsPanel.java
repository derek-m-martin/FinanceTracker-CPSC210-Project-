package ui;

import model.Category;
import model.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// ** SOURCE I REFERENCED TO HELP WITH THIS PHASE!!! ** //
// Link: https://www.youtube.com/watch?v=Kmgo00avvEw //
// A bit long but went real in depth to many different components //
// of swing and made this whole phase way easier than hitting //
// stackoverflow every 5 minutes //

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
        tableModel = new DefaultTableModel();
        transactionTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(transactionTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel filterPanel = new JPanel();
        filterComboBox = new JComboBox<>(new String[]{"All", "Oldest", "Newest", "Category"});
        filterButton = new JButton("Apply Filter");
        filterButton.addActionListener(e -> applyFilter());
        filterPanel.add(new JLabel("Filter By:"));
        filterPanel.add(filterComboBox);
        filterPanel.add(filterButton);
        add(filterPanel, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: refreshes the transactions displayed in the table
    public void refreshTransactions() {
        tableModel.setRowCount(0);
        tableModel.setColumnIdentifiers(new String[]{"ID", "Amount", "Description", "Category", "Date"});
        for (Category category : mainApp.getCategories().values()) {
            for (Transaction transaction : category.getTransactions().values()) {
                tableModel.addRow(new Object[]{
                        transaction.getId(),
                        transaction.getAmount(),
                        transaction.getDescription(),
                        transaction.getCategory().getName(),
                        transaction.getDate()
                });
            }
        }
    }

    // EFFECTS: applies the selected filter to the transactions
    private void applyFilter() {
        String filter = (String) filterComboBox.getSelectedItem();
        List<Transaction> transactionList = new ArrayList<>();
        for (Category category : mainApp.getCategories().values()) {
            transactionList.addAll(category.getTransactions().values());
        }

        if (filter.equals("Oldest")) {
            transactionList.sort(Comparator.comparingInt(Transaction::getId));
        } else if (filter.equals("Newest")) {
            transactionList.sort(Comparator.comparingInt(Transaction::getId).reversed());
        } else if (filter.equals("Category")) {
            transactionList.sort(Comparator.comparing(t -> t.getCategory().getName()));
        }

        tableModel.setRowCount(0);
        for (Transaction transaction : transactionList) {
            tableModel.addRow(new Object[]{
                    transaction.getId(),
                    transaction.getAmount(),
                    transaction.getDescription(),
                    transaction.getCategory().getName(),
                    transaction.getDate()
            });
        }
    }
}
