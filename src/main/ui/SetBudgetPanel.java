package ui;

import model.Category;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// ** SOURCE I REFERENCED TO HELP WITH THIS PHASE!!! ** //
// Link: https://www.youtube.com/watch?v=Kmgo00avvEw //
// A bit long but went real in depth to many different components //
// of swing and made this whole phase way easier than hitting //
// stackoverflow every 5 minutes //

// A panel to set budgets for categories
public class SetBudgetPanel extends JPanel implements ActionListener {

    private FinanceTrackerGUI mainApp;
    private JComboBox<String> categoryComboBox;
    private JTextField budgetField;
    private JButton setBudgetButton;

    // REQUIRES: mainApp is not null
    // EFFECTS: constructs the SetBudgetPanel
    public SetBudgetPanel(FinanceTrackerGUI mainApp) {
        this.mainApp = mainApp;
        setLayout(new GridLayout(3, 2));
        initializeComponents();
    }

    // MODIFIES: this
    // EFFECTS: initializes and adds components to the panel
    private void initializeComponents() {
        add(new JLabel("Select Category:"));
        categoryComboBox = new JComboBox<>();
        refreshCategories();
        add(categoryComboBox);

        add(new JLabel("New Budget Amount:"));
        budgetField = new JTextField();
        add(budgetField);

        setBudgetButton = new JButton("Set Budget");
        setBudgetButton.addActionListener(this);
        add(new JLabel());
        add(setBudgetButton);
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
        // EFFECTS: sets the new budget for the selected category
    public void actionPerformed(ActionEvent e) {
        try {
            String categoryName = (String) categoryComboBox.getSelectedItem();
            int newBudget = Integer.parseInt(budgetField.getText());
            Category category = mainApp.findCategory(categoryName);
            category.setBudget(newBudget);
            JOptionPane.showMessageDialog(SetBudgetPanel.this,
                    "Budget updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            budgetField.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(SetBudgetPanel.this,
                    "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
