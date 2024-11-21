package ui;

import model.Category;
import model.Transaction;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// A panel to display spending summaries over a selected time period
public class SummaryPanel extends JPanel {

    private FinanceTrackerGUI mainApp;
    private JTextField startDateField;
    private JTextField endDateField;
    private JButton generateButton;
    private JTextArea summaryArea;

    // REQUIRES: mainApp is not null
    // EFFECTS: constructs the SummaryPanel
    public SummaryPanel(FinanceTrackerGUI mainApp) {
    }

    // MODIFIES: this
    // EFFECTS: initializes and adds components to the panel
    private void initializeComponents() {
    }

    // EFFECTS: generates and displays the spending summary
    private void generateSummary() {
    }

    // EFFECTS: displays the spending summary in the text area
    private void displaySummary(LocalDate startDate, LocalDate endDate,
        int food, int housing, int entertainment, int transportation, int miscellaneous) {
    }

    // EFFECTS: retrieves transactions between the given dates
    private List<Transaction> grabTransactions(LocalDate start, LocalDate end) {
    }
}
