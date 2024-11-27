package model;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents a category within the finance tracker with a name, budget, and transactions

public class Category implements Writable {

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

        if (totalSpent >= this.getBudget()) {
            EventLog.getInstance()
                    .logEvent(new Event("The " + name + " category has exceeded its assigned budget of: $" + budget));
        }

        return totalSpent >= this.getBudget();
    }

    // EFFECTS: takes a category object and converts it into JSON format
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("budget", budget);
        json.put("transactions", transactionsToJson());
        return json;
    }

    // EFFECTS: takes the transaction HashMap and converts it into a JSON array
    // format
    public JSONArray transactionsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Transaction transaction : transactions.values()) {
            jsonArray.put(transaction.toJson());
        }
        return jsonArray;
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
