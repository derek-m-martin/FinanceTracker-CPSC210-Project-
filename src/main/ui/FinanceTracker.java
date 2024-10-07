package ui;

import model.Category;
import model.Transaction;
import java.util.Scanner;
import java.util.HashMap;
import java.util.UUID;

public class FinanceTracker {

    // ************************************************************************************
    // METHODS WHICH INITALIZE THE CONSOLE AND DEAL WITH USER INPUTS (aka the boring stuff)
    // additional note: referenced the TellerApp for most of this sections methods
    // ************************************************************************************

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
            System.out.println("\n");
            handleAddTransaction();
        }
        else if (instruction.equals("v")) {
            System.out.println("\n");
            handleViewTransactions();
        }
        else if (instruction.equals("c")) {
            System.out.println("\n");
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
        categories.put("food", new Category("FOOD", 100));
        categories.put("transportation", new Category("TRANSPORTATION", 100));
        categories.put("entertainment", new Category("ENTERTAINMENT", 100));
        categories.put("housing", new Category("HOUSING", 100));

        input = new Scanner(System.in);
    }

    // ************************************************************************************
    // METHODS WHICH PERFORM THE USER STORIES AND RELATED HELPER METHODS (aka the fun stuff)
    // ************************************************************************************

    // MODIFIES: this
    // EFFECTS: prompts for transaction details and then creates the transaction accordingly
    private void handleAddTransaction() {
        int amount;
        Category category = null;
        String desc;
        System.out.println("\n\t Please enter the transaction amount:");
        amount = Integer.parseInt(input.next());
        System.out.println("\n\t Please enter an existing or new category for the transaction:");
        String holder = input.next();
        category = findCategory(holder);
        if (category == null) {
            System.out.println("\n\t A category by that name was not located, would you like to create a new one with that name? (yes or no)");
            if (input.next().equalsIgnoreCase("yes")) {
                category = new Category(holder.toUpperCase(), 100);
                categories.put(holder, category);
            }
        }
        if (category != null) {
            System.out.println("\n\t OPTIONAL - enter a brief transaction description, say 'skip' if not needed:");
            desc = input.next();
            if (desc.equalsIgnoreCase("skip")) {
                desc = "";
            }
            Transaction temp = new Transaction(amount, category);
            temp.setDescription(desc);
            category.getTransactions().put(temp.getId(), temp);
        }
    }

    // REQUIRES: a non-empty string
    // EFFECTS: finds the category object from given string and returns the category object,
    // if no such category exists then it will return null
    public Category findCategory(String toFind) {
        for (HashMap.Entry<String, Category> entry : categories.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(toFind)) {
                return entry.getValue();
            }
        }
        return null;
    }

    // EFFECTS: displays transactions, can be filtered by date/category
    private void handleViewTransactions() {
        for (HashMap.Entry<String, Category> entry : categories.entrySet()) {
            Category category = entry.getValue(); 
            for (HashMap.Entry<UUID, Transaction> entry2 : category.getTransactions().entrySet()) {
                Transaction transaction = entry2.getValue();
                System.out.println("Transaction Amount: $" + transaction.getAmount() + ", Description: " + transaction.getDescription() + ", Date: " + transaction.getDate());
            }
        }
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
        for (HashMap.Entry<String, Category> entry : categories.entrySet()) {
            Category category = entry.getValue();
            System.out.println(category.getName() + " with a remaining budget of: " + category.getBudget());
        }
    }

}
