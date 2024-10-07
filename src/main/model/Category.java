package model;

import java.util.HashMap;
import java.util.UUID;

// represents a category within the finance tracker which has a name, allocated budget amount, and a 
// corresponding hashmap of all the transactions that have been assigned to the category

public class Category {
    
    private String name;
    private int budget;
    private HashMap<UUID, Transaction> transactions;

    // REQUIRES: catName must not be an empty string, catBudget must be > 0, catTransactions must be an empty list
    // EFFECTS: category name is set to catName, category budget is set to catBudget, 
    //          and an empty transaction list is initialized for the category
    public Category(String catName, int catBudget) {
        this.name = catName;
        this.budget = catBudget;
        this.transactions = new HashMap<>();
    }
    

    // REQUIRES: newName must not be an empty string
    // MODIFIES: this
    // EFFECTS: sets the category name equal to newName
    public void setName(String newName) {
        this.name = newName;
    }

    // REQUIRES: newBudget must be > 0
    // MODIFIES: this
    // EFFECTS: sets the category budget equal to newBudget
    public void setBudget(int newBudget) {
        this.budget = newBudget;
    }

    // REQUIRES: an existing and valid category
    // EFFECTS: adds the amounts of all transactions within the category and determines if the budget has been reached, 
    // true if it has, false if not
    public Boolean isBudgetReached() {
        int budgetCountTemporary = 0;
        for (Transaction t : transactions.values()) {
            budgetCountTemporary += t.getAmount();
        }
        
        return budgetCountTemporary >= this.getBudget();
    }

    // GETTER METHODS

    public int getBudget() {
        return this.budget;
    } 

    public String getName() {
        return this.name;
    }

    public HashMap<UUID, Transaction> getTransactions() {
        return this.transactions;
    }

}
