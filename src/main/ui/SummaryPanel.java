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
    private int foodAmt = 0;
    private int entertainmentAmt = 0;
    private int transportationAmt = 0;
    private int housingAmt = 0;
    private int miscellaneousAmt = 0;

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
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate startDate = LocalDate.parse(startDateField.getText(), formatter);
            LocalDate endDate = LocalDate.parse(endDateField.getText(), formatter);

            if (startDate.isAfter(endDate)) {
                LocalDate temp = startDate;
                startDate = endDate;
                endDate = temp;
            }

            List<Transaction> transactions = grabTransactions(startDate, endDate);

            generateSummaryHelper(transactions);

            displaySummary(startDate, endDate, foodAmt, housingAmt, entertainmentAmt,
                    transportationAmt, miscellaneousAmt);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(SummaryPanel.this,
                    "Invalid date format.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generateSummaryHelper(List<Transaction> transactions) {
        for (Transaction t : transactions) {
            int amount = t.getAmount();
            String categoryName = t.getCategory().getName().toLowerCase();
            switch (categoryName) {
                case "food":
                    foodAmt += amount;
                    break;
                case "housing":
                    housingAmt += amount;
                    break;
                case "entertainment":
                    entertainmentAmt += amount;
                    break;
                case "transportation":
                    transportationAmt += amount;
                    break;
                default:
                    miscellaneousAmt += amount;
                    break;
            }
        }
    }

    // EFFECTS: displays the spending summary in the text area
    private void displaySummary(LocalDate startDate, LocalDate endDate,
            int food, int housing, int entertainment, int transportation, int miscellaneous) {
        summaryArea.setText("");
        summaryArea.append("Between " + startDate + " and " + endDate + " you:\n\n");
        summaryArea.append("Spent $" + food + " on food\n");
        summaryArea.append("Spent $" + housing + " on housing\n");
        summaryArea.append("Spent $" + entertainment + " on entertainment\n");
        summaryArea.append("Spent $" + transportation + " on transportation\n");
        summaryArea.append("Spent $" + miscellaneous + " on miscellaneous\n");
    }

    // EFFECTS: retrieves transactions between the given dates
    private List<Transaction> grabTransactions(LocalDate start, LocalDate end) {
        List<Transaction> result = new ArrayList<>();
        for (Category category : mainApp.getCategories().values()) {
            for (Transaction transaction : category.getTransactions().values()) {
                LocalDate transDate = transaction.getDate();
                if ((transDate.isAfter(start) || transDate.equals(start))
                        && (transDate.isBefore(end) || transDate.equals(end))) {
                    result.add(transaction);
                }
            }
        }
        return result;
    }
}
