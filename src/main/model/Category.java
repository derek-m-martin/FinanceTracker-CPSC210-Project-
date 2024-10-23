package model;

import java.util.HashMap;

import org.json.JSONObject;

// Represents a category within the finance tracker with a name, budget, and transactions

public class Category {

    private String name;
    private int budget;
    private HashMap<Integer, Transaction> transactions;

    // REQUIRES: catName is not empty, catBudget > 0
    // EFFECTS: initializes a category with the given name and budget; transactions
    // are initialized empty
    public Category(String catName, int catBudget) {
        this.name = catName;
        this.budget = catBudget;
        this.transactions = new HashMap<>();
    }

    // REQUIRES: newName is not empty
    // MODIFIES: this
    // EFFECTS: sets the category name
    public void setName(String newName) {
        this.name = newName;
    }

    // REQUIRES: newBudget > 0
    // MODIFIES: this
    // EFFECTS: sets the category budget
    public void setBudget(int newBudget) {
        this.budget = newBudget;
    }

    // EFFECTS: returns true if the budget has been reached, false otherwise
    public Boolean isBudgetReached() {
        int totalSpent = 0;
        for (Transaction t : transactions.values()) {
            totalSpent += t.getAmount();
        }
        return totalSpent >= this.getBudget();
    }
    // EFFECTS: takes a category object and converts it into json format
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        // to fill
        return json;
    }

    // Getter methods

    public int getBudget() {
        return this.budget;
    }

    public String getName() {
        return this.name;
    }

    public HashMap<Integer, Transaction> getTransactions() {
        return this.transactions;
    }
}
