package ui;

import model.Category;
import model.Transaction;
import java.util.Scanner;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;

public class FinanceTracker {

    private Scanner input;
    private HashMap<String, Category> categories;
    private int nextTransactionId;
    private static final String JSON_STORE = "ProjectStarter/data/financetracker.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: starts the finance tracker console application
    public FinanceTracker() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        categories = new HashMap<>();
        startTracker();
        nextTransactionId = 0;
    }

    // MODIFIES: this
    // EFFECTS: processes input and keeps the program running until quit
    private void startTracker() {
        boolean stop = false;
        String instruction = "";

        initialize();
        
        System.out.println("Would you like to load your previous saved data? (y/n)");
        String loadAnswer = input.next().toLowerCase();
        if (loadAnswer.equals("y")) {
            loadState();
        }

        do {
            display();
            instruction = input.next().toLowerCase();

            if (instruction.equals("q")) {
                System.out.println("Would you like to save your data before quitting? (y/n)");
                String saveAnswer = input.next().toLowerCase();
                if (saveAnswer.equals("y")) {
                    saveState();
                }
                stop = true;
            } else {
                processInput(instruction);
            }
        } while (!stop);
    }

    // MODIFIES: this
    // EFFECTS: manages user inputs
    private void processInput(String instruction) {
        if (instruction.equals("a")) {
            System.out.println();
            handleAddTransaction();
        } else if (instruction.equals("v")) {
            System.out.println();
            handleViewTransactions();
        } else if (instruction.equals("c")) {
            System.out.println();
            showCategories();
        } else if (instruction.equals("e")) {
            System.out.println();
            handleEditTransaction();
        } else if (instruction.equals("b")) {
            System.out.println();
            handleSetBudget();
        } else if (instruction.equals("s")) {
            System.out.println();
            handleSummarize();
        } else if (instruction.equals("r")) {
            System.out.println();
            resetData();
        }
    }

    // EFFECTS: displays options to the user
    private void display() {
        System.out.println("\n Welcome to your Personal Finance Tracker!");
        System.out.println("\n\tSelect from:");
        System.out.println("\tSelect 'a' to add a transaction");
        System.out.println("\tSelect 'v' to view all of your transactions");
        System.out.println("\tSelect 'c' to view your current spending categories");
        System.out.println("\tSelect 'e' to edit, delete, or move a transaction between categories");
        System.out.println("\tSelect 'b' to change a category's budget");
        System.out.println("\tSelect 's' to receive a summary of your finances");
        System.out.println("\tSelect 'r' to reset all saved data");
        System.out.println("\tSelect 'q' to quit the Finance Tracker");
    }

    // MODIFIES: this
    // EFFECTS: initializes the generic categories and input scanner
    private void initialize() {
        categories.put("food", new Category("FOOD", 1000));
        categories.put("transportation", new Category("TRANSPORTATION", 1000));
        categories.put("entertainment", new Category("ENTERTAINMENT", 1000));
        categories.put("housing", new Category("HOUSING", 1000));
        categories.put("miscellaneous", new Category("MISCELLANEOUS", 1000));

        input = new Scanner(System.in);
    }

    // MODIFIES: this
    // EFFECTS: resets all saved data, clearing categories and transactions
    private void resetData() {
        categories.clear();
        initialize();
        saveState();
        System.out.println("All saved data has been reset.");
    }

    // MODIFIES: this
    // EFFECTS: prompts for transaction details and creates the transaction
    private void handleAddTransaction() {
        System.out.println("\n\tPlease enter the transaction amount:");
        int amount = Integer.parseInt(input.next());

        System.out.println("\n\tPlease enter an existing or new category for the transaction:");
        String holder = input.next();
        Category category = findCategory(holder);

        if (category == null) {
            System.out.println("\n\tA category by that name was not located, so the transaction was put into the "
                    + "miscellaneous category. You may move it to a different category by editing it after the "
                    + "transaction is completed.");
            category = findCategory("miscellaneous");
        }

        System.out.println("\n\tOPTIONAL - enter a brief transaction description, say 'skip' if not needed:");
        String desc = input.next();
        if (desc.equalsIgnoreCase("skip")) {
            desc = "";
        }

        Transaction temp = new Transaction(nextTransactionId++, amount, category);
        temp.setDescription(desc);
        category.getTransactions().put(temp.getId(), temp);
        category.setBudget(category.getBudget() - amount);
    }

    // REQUIRES: a non-empty string
    // EFFECTS: finds and returns the category object matching the given name
    public Category findCategory(String toFind) {
        for (HashMap.Entry<String, Category> entry : categories.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(toFind)) {
                return entry.getValue();
            }
        }
        return null;
    }

    // EFFECTS: displays transactions; can be filtered by date or category
    private void handleViewTransactions() {
        for (Category category : categories.values()) {
            for (Transaction transaction : category.getTransactions().values()) {
                System.out.println("ID: " + transaction.getId()
                        + ", Transaction Amount: $" + transaction.getAmount()
                        + ", Description: " + transaction.getDescription()
                        + ", Category: " + transaction.getCategory().getName()
                        + ", Date: " + transaction.getDate());
            }
        }

        System.out.println();
        System.out.println("Enter 'oldest' to filter the list by oldest to newest, 'newest' to filter by newest "
                + "to oldest, 'category' to filter by category, or 'menu' to go back to the menu:");
        String choice = input.next();

        if (choice.equals("oldest")) {
            filterTransactionOldest();
        } else if (choice.equals("newest")) {
            filterTransactionNewest();
        } else if (choice.equals("category")) {
            groupTransactions();
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
            System.out.println("ID: " + transaction.getId()
                    + ", Transaction Amount: $" + transaction.getAmount()
                    + ", Description: " + transaction.getDescription()
                    + ", Category: " + transaction.getCategory().getName()
                    + ", Date: " + transaction.getDate());
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
            System.out.println("ID: " + transaction.getId()
                    + ", Transaction Amount: $" + transaction.getAmount()
                    + ", Description: " + transaction.getDescription()
                    + ", Category: " + transaction.getCategory().getName()
                    + ", Date: " + transaction.getDate());
        }
    }

    // EFFECTS: groups transactions by category
    private void groupTransactions() {
        for (Category category : categories.values()) {
            System.out.println("Category: " + category.getName());
            List<Transaction> transactionList = new ArrayList<>(category.getTransactions().values());
            transactionList.sort(Comparator.comparingInt(Transaction::getId));

            for (Transaction transaction : transactionList) {
                System.out.println("ID: " + transaction.getId()
                        + ", Transaction Amount: $" + transaction.getAmount()
                        + ", Description: " + transaction.getDescription()
                        + ", Date: " + transaction.getDate());
            }
            System.out.println();
        }
    }

    // MODIFIES: category
    // EFFECTS: allows user to set the budget for a category
    private void handleSetBudget() {
        System.out.println("Please specify which category's budget you would like to change:");
        Category toChange = findCategory(input.next());

        System.out.println("Now enter the new budget for that category:");
        toChange.setBudget(Integer.parseInt(input.next()));
    }

    // MODIFIES: transaction
    // EFFECTS: allows user to edit, delete, or move a transaction
    private void handleEditTransaction() {
        System.out.println("Please enter the transaction ID for the transaction you would like to edit:");
        int toSearch = Integer.parseInt(input.next());
        Transaction toEdit = findTransaction(toSearch);

        System.out.println("Found this transaction with the given id: " + toEdit.getId()
                + ", Transaction Amount: $" + toEdit.getAmount()
                + ", Description: " + toEdit.getDescription()
                + ", Date: " + toEdit.getDate());

        System.out.println("\nWhat would you like to do? Enter 'amount' to change the transaction amount, "
                + "'delete' to delete the transaction, 'description' to edit the description, or 'move' to move "
                + "the transaction to a different category:");
        String toDo = input.next();

        if (toDo.equals("amount")) {
            editTransactionAmount(toEdit);
        } else if (toDo.equals("delete")) {
            deleteTransaction(toEdit);
        } else if (toDo.equals("description")) {
            editTransactionDescription(toEdit);
        } else if (toDo.equals("move")) {
            moveTransaction(toEdit);
        }
    }

    // MODIFIES: transaction
    // EFFECTS: edits the amount of a transaction
    private void editTransactionAmount(Transaction toEdit) {
        System.out.println("Please enter the new amount for the transaction:");
        int newAmt = Integer.parseInt(input.next());
        toEdit.setAmount(newAmt);
    }

    // MODIFIES: transaction, categories
    // EFFECTS: deletes a transaction and shifts IDs
    private void deleteTransaction(Transaction toEdit) {
        shiftIds(toEdit.getId());
        toEdit.deleteTransaction();
    }

    // MODIFIES: transaction
    // EFFECTS: edits the description of a transaction
    private void editTransactionDescription(Transaction toEdit) {
        System.out.println("Please enter the new description for the transaction:");
        String newDesc = input.next();
        toEdit.setDescription(newDesc);
    }

    // MODIFIES: transaction, categories
    // EFFECTS: moves a transaction to a different category
    private void moveTransaction(Transaction toEdit) {
        System.out.println("Please enter the new category for the transaction:");
        String newCat = input.next();
        Category newCategory = findCategory(newCat);
        if (newCategory != null) {
            toEdit.moveTransaction(newCategory);
        } else {
            System.out.println("Category not found. Transaction not moved.");
        }
    }

    // REQUIRES: a valid transaction id
    // MODIFIES: Transaction objects
    // EFFECTS: shifts the transaction IDs to maintain consecutive IDs after
    // deletion
    private void shiftIds(int threshold) {
        for (Category category : categories.values()) {
            for (Transaction transaction : category.getTransactions().values()) {
                if (transaction.getId() > threshold) {
                    transaction.setId(transaction.getId() - 1);
                }
            }
        }
    }

    // REQUIRES: an integer
    // EFFECTS: finds and returns the transaction with the given ID
    private Transaction findTransaction(int num) {
        for (Category category : categories.values()) {
            if (category.getTransactions().containsKey(num)) {
                return category.getTransactions().get(num);
            }
        }
        return null;
    }

    // EFFECTS: shows all the categories and current available budgets remaining
    private void showCategories() {
        for (Category category : categories.values()) {
            if (category.getBudget() > 0) {
                System.out.println(category.getName() + " with a remaining budget of: $" + category.getBudget());
            } else if (category.getBudget() == 0) {
                System.out.println(category.getName()
                        + " has reached its allotted budget! If you spend any more in "
                        + "that category, you will be in a deficit!");
            } else {
                System.out.println(category.getName() + " with a budget deficit of: $" + category.getBudget());
            }
        }
    }

    // EFFECTS: provides a summary of the user's spending between two dates
    private void handleSummarize() {
        List<LocalDate> returnedDates = getDates();
        LocalDate first = returnedDates.get(0);
        LocalDate second = returnedDates.get(1);

        int foodAmt = 0;
        int entertainmentAmt = 0;
        int transportationAmt = 0;
        int housingAmt = 0;
        int miscellaneousAmt = 0;

        if (first.isAfter(second)) {
            List<Transaction> returnedTrans = grabTransactions(second, first);
            List<Integer> returnedInts = organizeValues(foodAmt, housingAmt, transportationAmt, entertainmentAmt,
                    miscellaneousAmt, returnedTrans);
            printSummary(returnedInts, second, first);
        } else {
            List<Transaction> returnedTrans = grabTransactions(first, second);
            List<Integer> returnedInts = organizeValues(foodAmt, housingAmt, transportationAmt, entertainmentAmt,
                    miscellaneousAmt, returnedTrans);
            printSummary(returnedInts, first, second);
        }
    }

    // REQUIRES: a list of 5 integers and two LocalDate objects
    // EFFECTS: prints a summary of spending between two dates
    private void printSummary(List<Integer> toPrint, LocalDate startDate, LocalDate endDate) {
        int foodPrint = toPrint.get(0);
        int housingPrint = toPrint.get(1);
        int transportationPrint = toPrint.get(2);
        int entertainmentPrint = toPrint.get(3);
        int miscellaneousPrint = toPrint.get(4);

        System.out.println();
        System.out.println("Between " + startDate + " and " + endDate + " you:");
        System.out.println();
        System.out.println("Spent $" + foodPrint + " on food");
        System.out.println("Spent $" + housingPrint + " on housing");
        System.out.println("Spent $" + entertainmentPrint + " on entertainment");
        System.out.println("Spent $" + transportationPrint + " on transportation");
        System.out.println("Spent an additional $" + miscellaneousPrint + " in uncategorized transactions");
    }

    @SuppressWarnings("methodlength")
    // REQUIRES: five integer values and a non-empty list of Transaction objects
    // MODIFIES: the given integer values
    // EFFECTS: aggregates spending amounts by category
    private List<Integer> organizeValues(int food, int housing, int transportation, int entertainment,
            int miscellaneous, List<Transaction> transactionsSorted) {
        for (Transaction t : transactionsSorted) {
            int amount = t.getAmount();
            String categoryName = t.getCategory().getName().toLowerCase();
            switch (categoryName) {
                case "food":
                    food += amount;
                    break;
                case "housing":
                    housing += amount;
                    break;
                case "entertainment":
                    entertainment += amount;
                    break;
                case "transportation":
                    transportation += amount;
                    break;
                default:
                    miscellaneous += amount;
                    break;
            }
        }
        List<Integer> nums = new ArrayList<>();
        nums.add(food);
        nums.add(housing);
        nums.add(transportation);
        nums.add(entertainment);
        nums.add(miscellaneous);
        return nums;
    }

    // REQUIRES: two LocalDate objects
    // EFFECTS: retrieves all transactions between the given dates (inclusive)
    private List<Transaction> grabTransactions(LocalDate start, LocalDate end) {
        List<Transaction> result = new ArrayList<>();
        for (Category category : categories.values()) {
            for (Transaction transaction : category.getTransactions().values()) {
                LocalDate transDate = transaction.getDate();
                if ((transDate.isAfter(start) || transDate.equals(start))
                        && (transDate.isBefore(end) || transDate.equals(end))) {
                    result.add(transaction);
                }
            }
        }
        return result;
    }

    // EFFECTS: prompts the user for two dates and returns them as a list
    private List<LocalDate> getDates() {
        List<LocalDate> dates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Please enter the first date in the form of dd/mm/yyyy:");
        String firstIn = input.next();
        try {
            dates.add(LocalDate.parse(firstIn, formatter));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format.");
            return getDates();
        }

        System.out.println("Please enter the second date in the same form:");
        String secondIn = input.next();
        try {
            dates.add(LocalDate.parse(secondIn, formatter));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format.");
            return getDates();
        }
        return dates;
    }

    // EFFECTS: saves the categories and their transactions to a json file
    private void saveState() {
        try {
            jsonWriter.open();
            jsonWriter.write(categoriesToJson());
            jsonWriter.close();
            System.out.println("Data saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the categories and their transactions from a json file
    private void loadState() {
        try {
            categories = jsonReader.readCategories();
            System.out.println("Data loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    private JSONObject categoriesToJson() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Category category : categories.values()) {
            jsonArray.put(category.toJson());
        }
        json.put("categories", jsonArray);
        return json;
    }
}