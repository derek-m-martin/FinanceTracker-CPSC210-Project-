package ui;

import model.Category;
import model.Transaction;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class FinanceTracker {

    // ************************************************************************************
    // METHODS WHICH INITALIZE THE CONSOLE AND DEAL WITH USER INPUTS (aka the boring stuff)
    // additional note: referenced the TellerApp for most of this sections methods
    // ************************************************************************************

    // create four generic spending categories (more can be made by the user) and the scanner
    private Scanner input;
    private HashMap<String, Category> categories;
    private int nextTransactionId;

    // EFFECTS: starts the financetracker console application
    public FinanceTracker() {
        categories = new HashMap<>();
        startTracker();
        nextTransactionId = 0;
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

            if (instruction.equals("q")) {
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
        else if (instruction.equals("e")) {
            System.out.println("\n");
            handleEditTransaction();
        }
        else if (instruction.equals("b")) {
            System.out.println("\n");
            handleSetBudget();
        }
    }

    // EFFECTS: displays options to the user
    private void display() {
        System.out.println("\n Welcome to your Personal Finance Tracker!");
        System.out.println("\n\tSelect from:");
        System.out.println("\tSelect 'a' to add a transaction");
        System.out.println("\tSelect 'v' to view a summary of your transactions");
        System.out.println("\tSelect 'c' to view your current spending categories");
        System.out.println("\tSelect 'e' to edit, delete, or move a transaction between categories");
        System.out.println("\tSelect 'b' to change a category's budget");
        System.out.println("\tSelect 'q' to quit the Finance Tracker");
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
            System.out.println("\n\t A category by that name was not located, one has been created with that name and the transaction is complete.");
            category = new Category(holder.toUpperCase(), 100);
            categories.put(holder, category);
        }
        if (category != null) {
            System.out.println("\n\t OPTIONAL - enter a brief transaction description, say 'skip' if not needed:");
            desc = input.next();
            if (desc.equalsIgnoreCase("skip")) {
                desc = "";
            }
            Transaction temp = new Transaction(nextTransactionId++, amount, category);
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
            for (HashMap.Entry<Integer, Transaction> entry2 : category.getTransactions().entrySet()) {
                Transaction transaction = entry2.getValue();
                System.out.println("ID: " + transaction.getId() + ", Transaction Amount: $" + transaction.getAmount() + ", Description: " + transaction.getDescription() + ", Date: " + transaction.getDate());
            }
        }
        System.out.println("Enter 'oldest' to filter the list by oldest to newest, 'newest' to filter by newest to oldest, or 'menu' to go back to the menu:");
        if (input.next().equals("oldest")) {
            filterTransactionOldest();
        }
        else if (input.next().equals("newest")) {
            filterTransactionNewest();
        }
    }

    // EFFECTS: filters all of the transactions from oldest to newest
    private void filterTransactionOldest() {
        List<Transaction> transactionList = new ArrayList<>();
        for (Category category : categories.values()) {
            transactionList.addAll(category.getTransactions().values());
        }
        transactionList.sort(Comparator.comparingInt(Transaction::getId));
        for (Transaction transaction : transactionList) {
            System.out.println("ID: " + transaction.getId() + ", Transaction Amount: $" + transaction.getAmount() + ", Description: " + transaction.getDescription() + ", Date: " + transaction.getDate());
        }
    }
    

    // EFFECTS: filters all of the transactions from newest to oldest
    private void filterTransactionNewest() {
        List<Transaction> transactionList = new ArrayList<>();
        for (Category category : categories.values()) {
            transactionList.addAll(category.getTransactions().values());
        }
        transactionList.sort(Comparator.comparingInt(Transaction::getId).reversed());
        for (Transaction transaction : transactionList) {
            System.out.println("ID: " + transaction.getId() + ", Transaction Amount: $" + transaction.getAmount() + ", Description: " + transaction.getDescription() + ", Date: " + transaction.getDate());
        }
    }
    
    

    // EFFECTS: groups transactions by category
    private void groupTransactions() {
        // stub
    }

    // MODIFIES: category
    // EFFECTS: allows user to select categories and set the budget as desired
    private void handleSetBudget() {
        System.out.println("Please specify which category's budget you would like to change:");
        Category toChange = findCategory(input.next());
        System.out.println("Now enter the new budget for that category:");
        toChange.setBudget(Integer.parseInt(input.next()));
    }

    // MODIFIES: transaction
    // EFFECTS: allows user to select specific transactions and edit information about them
    // or delete the transaction entirely
    private void handleEditTransaction() {
        System.out.println("Please enter the transaction ID for the transaction you would like to edit: ");
        int toSearch = Integer.parseInt(input.next());
        Transaction toEdit = findTransaction(toSearch);
        System.out.println("Found this transaction with the given id: "+ toEdit.getId() + ", Transaction Amount: $" + toEdit.getAmount() + ", Description: " + toEdit.getDescription() + ", Date: " + toEdit.getDate());;
        System.out.println("\nWhat would you like to do? Enter 'amount' to change the transaction amount, enter 'delete' to delete the transaction, enter 'description' to edit the description or enter 'move' to move the transaction to a different category:");
        String toDo = input.next();
        if (toDo.equals("amount")) {
            System.out.println("Please enter the new amount for the transaction:");
            int newAmt = Integer.parseInt(input.next());
            toEdit.setAmount(newAmt);
        } else if (toDo.equals("delete")) {
            shiftIds(toEdit.getId());
            toEdit.deleteTransaction();
        } else if (toDo.equals("description")) {
            System.out.println("Please enter the new description for the transaction:");
            String newDesc = input.next();
            toEdit.setDescription(newDesc);
        } else if (toDo.equals("move")) {
            System.out.println("Please enter the new category for the transaction:");
            String newCat = input.next();
            toEdit.moveTransaction(findCategory(newCat));
        }  
    }

    // REQUIRES: a valid transaction id
    // MODIFIES: Transaction objects
    // EFFECTS: Shifts the Transaction Ids of all transactions newer than the given Id down by one to allow for
    // consecutive ids even when a transaction is deleted
    private void shiftIds(int threshold) {
        for (HashMap.Entry<String, Category> entry : categories.entrySet()) {
            Category category = entry.getValue(); 
            for (HashMap.Entry<Integer, Transaction> entry2 : category.getTransactions().entrySet()) {
                if (entry2.getKey() > threshold) {
                    entry2.getValue().setId(entry2.getKey() - 1);
                }
            }
        }
    }

    // REQUIRES: an integer
    // EFFECTS: searches through all the transactions for the one corresponding to the given id,
    // if one is found, return the transaction object otherwise return null
    private Transaction findTransaction(int num) {
        for (HashMap.Entry<String, Category> entry : categories.entrySet()) {
            Category category = entry.getValue(); 
            for (HashMap.Entry<Integer, Transaction> entry2 : category.getTransactions().entrySet()) {
                if (entry2.getKey() == num) {
                    return entry2.getValue();
                }
            }
        }
        return null;
    }

    // EFFECTS: shows all the categories and current available budgets remaining
    private void showCategories() {
        for (HashMap.Entry<String, Category> entry : categories.entrySet()) {
            Category category = entry.getValue();
            System.out.println(category.getName() + " with a budget of: " + category.getBudget());
        }
    }

}
