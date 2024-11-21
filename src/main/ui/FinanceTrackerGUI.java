package ui;

import model.Category;
import model.Transaction;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

// The main GUI class for the Finance Tracker application
public class FinanceTrackerGUI extends JFrame {

    // EFFECTS: initializes the finance tracker GUI application
    public FinanceTrackerGUI() {
    }

    // MODIFIES: this
    // EFFECTS: initializes the data fields
    private void initializeFields() {
    }

    // MODIFIES: this
    // EFFECTS: initializes the spending categories
    private void initializeCategories() {
    }

    // MODIFIES: this
    // EFFECTS: initializes the main frame and adds components
    private void initializeGraphics() {
    }

    // MODIFIES: this
    // EFFECTS: initializes the menu bar with save and load options
    private void initializeMenu() {
    }

    // EFFECTS: prompts the user to load data at startup
    private void promptLoadData() {
    }

    // EFFECTS: prompts the user to save data before exiting
    private void promptSaveData() {
    }

    // EFFECTS: saves the categories and their transactions to a json file
    public void saveState() {
    }

    // MODIFIES: this
    // EFFECTS: loads the categories and their transactions from a json file
    public void loadState() {
    }

    // MODIFIES: this
    // EFFECTS: updates the next transaction ID based on loaded data
    private void updateNextTransactionId() {
    }

    // EFFECTS: converts categories to JSON object
    private org.json.JSONObject categoriesToJson() {
    }

    // MODIFIES: this
    // EFFECTS: refreshes all panels after data load
    private void refreshAllPanels() {
    }

    // EFFECTS: returns the next transaction ID
    public int getNextTransactionId() {
    }

    // EFFECTS: finds and returns the category with the given name
    public Category findCategory(String name) {
    }

    // EFFECTS: returns all categories
    public HashMap<String, Category> getCategories() {
    }
}