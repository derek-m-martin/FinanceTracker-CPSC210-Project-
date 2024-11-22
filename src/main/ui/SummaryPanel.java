package ui;

import model.Category;
import model.Transaction;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// ** SOURCE I REFERENCED TO HELP WITH THIS PHASE!!! ** //
// Link: https://www.youtube.com/watch?v=Kmgo00avvEw //
// A bit long but went real in depth to many different components //
// of swing and made this whole phase way easier than hitting //
// stackoverflow every 5 minutes //

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
        this.mainApp = mainApp;
        setLayout(new BorderLayout());
        initializeComponents();
    }

    // MODIFIES: this
    // EFFECTS: initializes and adds components to the panel
    private void initializeComponents() {
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Start Date (dd/mm/yyyy):"));
        startDateField = new JTextField();
        inputPanel.add(startDateField);

        inputPanel.add(new JLabel("End Date (dd/mm/yyyy):"));
        endDateField = new JTextField();
        inputPanel.add(endDateField);

        generateButton = new JButton("Generate Summary");
        generateButton.addActionListener(e -> generateSummary());
        inputPanel.add(new JLabel());
        inputPanel.add(generateButton);

        add(inputPanel, BorderLayout.NORTH);

        summaryArea = new JTextArea();
        summaryArea.setEditable(false);
        add(new JScrollPane(summaryArea), BorderLayout.CENTER);
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
