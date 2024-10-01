package model;

import java.util.List;

// represents a category within the finance tracker which has a name, allocated budget amount, and a 
// corresponding list of all the transactions that have been assigned to the category

public class Category {
    
    private String name;
    private int budget;
    private List<Transaction> transactions;

    // REQUIRES: catName must not be an empty string, catBudget must be > 0, catTransactions must be an empty list
    // EFFECTS: category name is set to catName, category budget is set to catBudget, 
    //          and an empty transaction list is initialized for the category
    public Category(String catName, int catBudget, List<Transaction> catTransactions) {
        // stub
    }

    // REQUIRES: newName must not be an empty string
    // MODIFIES: this
    // EFFECTS: sets the category name equal to newName
    public void setName(String newName) {
        // stub
    }

    // REQUIRES: newBudget must be > 0
    // MODIFIES: this
    // EFFECTS: sets the category budget equal to newBudget
    public void setBudget(int newBudget) {
        // stub
    }

    // REQUIRES: an existing and valid category
    // EFFECTS: adds the amounts of all transactions within the category and determines if the budget has been reached, 
    // true if it has, false if not
    public Boolean isBudgetReached(Category category) {
        return false; // stub
    }

    // GETTER METHODS

    public int getBudget() {
        return 0; // stub
    } 

    public String getName() {
        return ""; // stub
    }

    public List<Transaction> getTransactions() {
        return List.of(); // stub (List.of() just returns an immutable empty list so the stub doesn't error constantly)
    }

}
