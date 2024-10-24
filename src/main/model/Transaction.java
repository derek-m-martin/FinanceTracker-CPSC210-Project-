package model;

import java.time.LocalDate;

import org.json.JSONObject;

// Represents a transaction which includes an ID, amount, category, date, and description

public class Transaction {

    private int id; // Unique transaction ID
    private int amount; // Transaction amount in dollars
    private Category category; // Category of the transaction
    private LocalDate date; // Date of the transaction
    private String description; // Optional description of the transaction

    // REQUIRES: amount > 0, category is valid
    // EFFECTS: initializes a transaction with the given ID, amount, and category;
    // date is set to current date
    public Transaction(int id, int transAmount, Category category) {
        this.id = id;
        this.amount = transAmount;
        this.category = category;
        this.date = LocalDate.now();
        this.description = "";
        category.getTransactions().put(this.id, this);
    }

    // REQUIRES: amt > 0
    // MODIFIES: this
    // EFFECTS: sets the transaction amount
    public void setAmount(int amt) {
        this.amount = amt;
    }

    // MODIFIES: this
    // EFFECTS: sets the transaction description
    public void setDescription(String newDesc) {
        this.description = newDesc;
    }

    // REQUIRES: newCategory must be an already existing category
    // MODIFIES: this, category budgets
    // EFFECTS: moves the transaction to the new category and updates budgets
    public void moveTransaction(Category newCategory) {
        this.category.setBudget(this.category.getBudget() + this.amount);
        this.category.getTransactions().remove(this.id);
        newCategory.setBudget(newCategory.getBudget() - this.amount);
        newCategory.getTransactions().put(this.id, this);
        this.category = newCategory;
    }

    // MODIFIES: this, category budget
    // EFFECTS: deletes the transaction and updates the category's budget
    public void deleteTransaction() {
        // Add the amount back to the category's budget
        category.setBudget(category.getBudget() + this.amount);
        // Remove transaction from category
        category.getTransactions().remove(this.id);
    }

    // MODIFIES: this
    // EFFECTS: sets a new ID for the transaction
    public void setId(int newId) {
        this.id = newId;
    }
    
    // MODIFIES: this
    // EFFECTS: sets a new date for the transaction
    public void setDate(LocalDate date) {
        this.date = date;
    }    

    // EFFECTS: takes a transaction object and converts it into JSON format
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("amount", amount);
        json.put("description", description);
        json.put("date", date.toString()); 
        return json;
    }

    // Getter methods

    public int getId() {
        return this.id;
    }

    public int getAmount() {
        return this.amount;
    }

    public Category getCategory() {
        return this.category;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getDescription() {
        return this.description;
    }
}
