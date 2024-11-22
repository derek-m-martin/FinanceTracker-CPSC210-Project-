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

// ** SOURCE I REFERENCED TO HELP WITH THIS PHASE!!! ** //
// Link: https://www.youtube.com/watch?v=Kmgo00avvEw //
// A bit long but went real in depth to many different components //
// of swing and made this whole phase way easier than hitting //
// stackoverflow every 5 minutes //

public class FinanceTrackerGUI extends JFrame {

    private static final String JSON_STORE = "./ProjectStarter/data/financetracker.json";
    private HashMap<String, Category> categories;
    private int nextTransactionId;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JTabbedPane tabbedPane;
    private AddTransactionPanel addTransactionPanel;
    private ViewTransactionsPanel viewTransactionsPanel;
    private EditTransactionPanel editTransactionPanel;
    private SummaryPanel summaryPanel;

    // EFFECTS: initializes the finance tracker GUI application
    public FinanceTrackerGUI() {
        super("Finance Tracker");
        initializeFields();
        initializeGraphics();
        initializeMenu();
        promptLoadData();
    }

    // MODIFIES: this
    // EFFECTS: initializes the data fields
    private void initializeFields() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        categories = new HashMap<>();
        nextTransactionId = 0;
        initializeCategories();
    }

    // MODIFIES: this
    // EFFECTS: initializes the spending categories
    private void initializeCategories() {
        categories.put("food", new Category("FOOD", 1000));
        categories.put("transportation", new Category("TRANSPORTATION", 1000));
        categories.put("entertainment", new Category("ENTERTAINMENT", 1000));
        categories.put("housing", new Category("HOUSING", 1000));
        categories.put("miscellaneous", new Category("MISCELLANEOUS", 1000));
    }

    // MODIFIES: this
    // EFFECTS: initializes the main frame and adds components
    private void initializeGraphics() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        addTransactionPanel = new AddTransactionPanel(this);
        viewTransactionsPanel = new ViewTransactionsPanel(this);
        editTransactionPanel = new EditTransactionPanel(this);
        summaryPanel = new SummaryPanel(this);

        // create tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Add Transaction", addTransactionPanel);
        tabbedPane.addTab("View Transactions", viewTransactionsPanel);
        tabbedPane.addTab("Edit Transaction", editTransactionPanel);
        tabbedPane.addTab("Summary", summaryPanel);

        add(tabbedPane, BorderLayout.CENTER);

        // visual part!
        ImagePanel imagePanel = new ImagePanel();
        add(imagePanel, BorderLayout.NORTH);

        // save prompt
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                promptSaveData();
            }
        });

        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes the menu bar with save and load options
    private void initializeMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem saveItem = new JMenuItem("Save Data");
        saveItem.addActionListener(e -> saveState());
        JMenuItem loadItem = new JMenuItem("Load Data");
        loadItem.addActionListener(e -> loadState());

        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    // EFFECTS: prompts the user to load data at startup
    private void promptLoadData() {
        int result = JOptionPane.showConfirmDialog(this,
                "Would you like to load your previous saved data?", "Load Data",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            loadState();
        }
    }

    // EFFECTS: prompts the user to save data before exiting
    private void promptSaveData() {
        int result = JOptionPane.showConfirmDialog(this,
                "Would you like to save your data before quitting?", "Save Data",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            saveState();
        }
        System.exit(0);
    }

    // EFFECTS: saves the categories and their transactions to a json file
    public void saveState() {
        try {
            jsonWriter.open();
            jsonWriter.write(categoriesToJson());
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Data saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the categories and their transactions from a json file
    public void loadState() {
        try {
            categories = jsonReader.readCategories();
            updateNextTransactionId();
            JOptionPane.showMessageDialog(this, "Data loaded from " + JSON_STORE);
            refreshAllPanels();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the next transaction ID based on loaded data
    private void updateNextTransactionId() {
        int highestId = -1;
        for (Category category : categories.values()) {
            for (Transaction transaction : category.getTransactions().values()) {
                if (transaction.getId() > highestId) {
                    highestId = transaction.getId();
                }
            }
        }
        nextTransactionId = highestId + 1;
    }

    // EFFECTS: converts categories to JSON object
    private org.json.JSONObject categoriesToJson() {
        org.json.JSONObject json = new org.json.JSONObject();
        org.json.JSONArray jsonArray = new org.json.JSONArray();
        for (Category category : categories.values()) {
            jsonArray.put(category.toJson());
        }
        json.put("categories", jsonArray);
        return json;
    }

    // MODIFIES: this
    // EFFECTS: refreshes all panels after data load
    private void refreshAllPanels() {
        addTransactionPanel.refreshCategories();
        viewTransactionsPanel.refreshTransactions();
        editTransactionPanel.refreshTransactions();
    }

    // EFFECTS: returns the next transaction ID
    public int getNextTransactionId() {
        return nextTransactionId++;
    }

    // EFFECTS: finds and returns the category with the given name
    public Category findCategory(String name) {
        return categories.get(name.toLowerCase());
    }

    // EFFECTS: returns all categories
    public HashMap<String, Category> getCategories() {
        return categories;
    }
}