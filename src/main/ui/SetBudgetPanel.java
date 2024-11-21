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
public class SetBudgetPanel extends JPanel {

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
    }

    // MODIFIES: this
    // EFFECTS: refreshes the categories in the combo box
    public void refreshCategories() {
    }

    // Listener class for setting budgets
    private class SetBudgetListener implements ActionListener {
    }
}
