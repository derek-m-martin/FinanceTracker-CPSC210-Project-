package model;

import java.time.LocalDate;
import java.util.UUID;


// represents a transaction which gets a transaction id, amount (dollars), category, date, and description 

public class Transaction {

    private UUID id; // a unique transaction id generated with UUID
    private int amount; // transaction amount in dollars
    private Category category; // category in which the transaction is
    private LocalDate date; // date that the transaction was placed on
    private String description; // an optional short description of the transaction such as "New Door" or "UberEats"

    // REQUIRES: > 0 amount, already existing category, at least an empty string for the description ("")
    // EFFECTS: transaction id is randomly generated by UUID and assigned,
    //          amount is set to the given amount
    //          category is assigned to the given category
    //          date is automatically set to the current date
    //          description is set to the given TransDesc, an empty string is accepted
    public Transaction(int TransAmount, Category category) {
        this.id = UUID.randomUUID();
        this.amount = TransAmount;
        this.category = category;
        this.date = LocalDate.now();
        this.description = "";
        category.getTransactions().put(this.id, this);
    }
    
    // REQUIRES: a valid transaction id and > 0 amt value
    // MODIFIES: this
    // EFFECTS: changes the amount of the transaction to the given amt
    public void setAmount(int amt) {
        this.amount = amt;
    }

    // REQUIRES: a valid transaction id (newDesc CAN be an empty string)
    // MODIFIES: this
    // EFFECTS: changes the description of the transaction to the given newDesc
    public void setDescription(String newDesc) {
        this.description = newDesc;
    }

    // REQUIRES: newCategory must be an already existing category
    // MODIFIES: Transaction, Category
    // EFFECTS: removes the transaction from whichever category it is currently in and inserts it into the newCategory
    public void moveTransaction(Category newCategory) {
        this.category = newCategory;
    }
    

    // GETTER METHODS

    public UUID getId() {
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
