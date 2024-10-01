package model;

import java.util.Date;

// represents a transaction which gets a transaction id, amount (dollars), category, date, and description 

public class Transaction {

    private int id; // transaction id
    private int amount; // transaction amount in dollars
    private Category category; // category in which the transaction is
    private Date date; // date that the transaction was placed on
    private String description; // an optional short description of the transaction such as "New Door" or "UberEats"

    // REQUIRES: > 0 amount, already existing category, at least an empty string for the description ("")
    // EFFECTS: transaction id is randomly generated 5 digits and assigned, 
    //          amount is set to the given amount
    //          category is assigned to the given category
    //          date is automatically set to the current date
    //          description is set to the given TransDesc, an empty string is accepted
    public Transaction(int TransAmount, Category category, String TransDesc) {
        // stub
    }

    // REQUIRES: a valid transaction id and > 0 amt value
    // MODIFIES: this
    // EFFECTS: changes the amount of the transaction to the given amt
    public void setAmount(int amt) {
        // stub
    }

    // REQUIRES: a valid transaction id (newDesc CAN be an empty string)
    // MODIFIES: this
    // EFFECTS: changes the description of the transaction to the given newDesc
    public void setDescription(String newDesc) {
        // stub
    }

    // REQUIRES: newCategory must be an already existing category
    // MODIFIES: Transaction, Category
    // EFFECTS: removes the transaction from whichever category it is currently in and inserts it into the newCategory
    public void moveTransaction(Category newCategory) {
        // stub
    }

    // GETTER METHODS

    public int getId() {
        return 0; // stub
    }

    public int getAmount() {
        return 0; // stub
    }

    public String getCategory() {
        return ""; // stub
    }

    public Date getDate() {
        return null; // stub
    }

    public String getDescription() {
        return ""; // stub
    }
    
}
