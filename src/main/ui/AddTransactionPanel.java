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

// A panel that allows users to add new transactions
public class AddTransactionPanel extends JPanel implements ActionListener {

    private FinanceTrackerGUI mainApp;
    private JTextField amountField;
    private JComboBox<String> categoryComboBox;
    private JTextField descriptionField;
    private JButton addButton;

    // REQUIRES: mainApp is not null
    // EFFECTS: constructs the AddTransactionPanel
    public AddTransactionPanel(FinanceTrackerGUI mainApp) {
        this.mainApp = mainApp;
        setLayout(new GridLayout(4, 2));
        initializeComponents();
    }

    // MODIFIES: this
    // EFFECTS: initializes and adds components to the panel
    private void initializeComponents() {
        add(new JLabel("Transaction Amount:"));
        amountField = new JTextField();
        add(amountField);

        add(new JLabel("Category:"));
        categoryComboBox = new JComboBox<>();
        refreshCategories();
        add(categoryComboBox);

        add(new JLabel("Description (Optional):"));
        descriptionField = new JTextField();
        add(descriptionField);

        addButton = new JButton("Add Transaction");
        addButton.addActionListener(this);
        add(new JLabel()); // Empty label for spacing
        add(addButton);
    }

    // MODIFIES: this
    // EFFECTS: refreshes the categories in the combo box
    public void refreshCategories() {
        categoryComboBox.removeAllItems();
        for (Category category : mainApp.getCategories().values()) {
            categoryComboBox.addItem(category.getName());
        }
    }

    // MODIFIES: mainApp
    public void actionPerformed(ActionEvent e) {
        try {
            // Takes all of the users inputs and if allowed then creates a corresponding transaction object
            int amount = Integer.parseInt(amountField.getText());
            String categoryName = (String) categoryComboBox.getSelectedItem();
            String description = descriptionField.getText();
            Category category = mainApp.findCategory(categoryName);
            if (category == null) {
                JOptionPane.showMessageDialog(this,
                        "Category not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Transaction transaction = new Transaction(mainApp.getNextTransactionId(), amount, category);
            transaction.setDescription(description);
            category.getTransactions().put(transaction.getId(), transaction);
            category.setBudget(category.getBudget() - amount);

            // Success message (hopefully this is what is shown)
            JOptionPane.showMessageDialog(this,
                    "Transaction added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            amountField.setText("");
            descriptionField.setText("");

            // First is a non-number error, the second exception is hopefully never seen
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "You need to enter a number!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Something definitely went wrong that should not have!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
