package ui;

import model.Category;
import model.Transaction;
import java.util.Scanner;
import java.util.HashMap;

public class FinanceTracker {

    // create four generic spending categories (more can be made by the user) and the scanner
    private Scanner input;
    private HashMap<String, Category> categories;

    // EFFECTS: starts the financetracker console application
    public FinanceTracker() {
        categories = new HashMap<>();
        startTracker();
    }

    // MODIFIES: this
    // EFFECTS: processes input and keeps the program running until quit
    private void startTracker() {
        boolean stop = false;
        String instruction = "";

        initilize();

        do {
            display();
            instruction = input.next();
            instruction = instruction.toLowerCase();

            if (instruction.equals("e")) {
                stop = true;
            }
            else {
                processInput(instruction);
            }
        }
        while (!stop);

    }

    // MODIFIES: this
    // EFFECTS: manages user inputs
    private void processInput(String instruction) {
        if (instruction.equals("a")) {
            handleAddTransaction();
        }
        else if (instruction.equals("v")) {
            handleViewTransactions();
        }
        else if (instruction.equals("c")) {
            showCategories();
        }
    }

    // EFFECTS: displays options to the user
    private void display() {
        System.out.println("\n Welcome to your Personal Finance Tracker!");
        System.out.println("\n\tSelect from:");
        System.out.println("\tSelect 'a' to add a transaction");
        System.out.println("\tSelect 'v' to view a summary of your transactions");
        System.out.println("\tSelect 'c' to view your current spending categories");
        System.out.println("\tSelect 'e' to exit the Finance Tracker");
    }

    // MODIFIES: this
    // EFFECTS: initalizes the generic categories and input scanner
    private void initilize() {
        categories.put("food", new Category("Food", 100, null));
        categories.put("transportation", new Category("Transportation", 100, null));
        categories.put("entertainment", new Category("Entertainment", 100, null));
        categories.put("housing", new Category("Housing", 100, null));

        input = new Scanner(System.in);
    }

    // MODIFIES: this
    // EFFECTS: prompts for transaction details and then creates the transaction accordingly
    private void handleAddTransaction() {
        int amount;
        Category category;
        String desc;
        System.out.println("\n\t Please enter the transaction amount:");
        amount = Integer.parseInt(input.next());
        System.out.println("\n\t Please specify a category from below for the transaction:");
        showCategories();
        category = findCategory(input.next());
        System.out.println("\n\t OPTIONAL - enter a brief transaction description, say 'skip' if not needed:");
        desc = input.next();
        if (desc.equalsIgnoreCase("skip")) {
            desc = "";
        }
        new Transaction(amount, category, desc);
    }

    // REQUIRES: a non-empty string
    // EFFECTS: finds the category object from given string and returns the category object,
    // if no such category exists then it will return null
    public Category findCategory(String toFind) {
        for (HashMap.Entry<String, Category> entry : categories.entrySet()) {
            if (entry.getKey().equals(toFind)) {
                return entry.getValue();
            }
        }
        return null;
    }

    // EFFECTS: displays transactions, can be filtered by date/category
    private void handleViewTransactions() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: allows user to select categories and set the budget as desired
    private void handleSetBudget() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: allows user to select specific transactions and edit information about them
    // or delete the transaction entirely
    private void handleEditTransaction() {
        // stub
    }

    // EFFECTS: shows all the categories and current available budgets remaining
    private void showCategories() {
        // stub
    }

}
