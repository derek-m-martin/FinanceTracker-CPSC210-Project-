package persistence;

import model.Category;
import model.Transaction;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;

public class JsonReader {

    private String source;

    // EFFECTS: assigns variable to provided parameter
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads JSON data from file and returns a map of categories
    public HashMap<String, Category> readCategories() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(source)));
        JSONObject jsonObject = new JSONObject(content);
        return parseCategories(jsonObject);
    }

    // REQUIRES: a json object
    // EFFECTS: parses categories from JSON and returns a populated HashMap of categories
    private HashMap<String, Category> parseCategories(JSONObject jsonObject) {
        HashMap<String, Category> categories = new HashMap<>();
        JSONArray jsonArray = jsonObject.getJSONArray("categories");
    
        for (Object obj : jsonArray) {
            JSONObject jsonCategory = (JSONObject) obj;
            String name = jsonCategory.getString("name");
            int budget = jsonCategory.getInt("budget");
    
            Category category = new Category(name, budget);
    
            JSONArray jsonTransactions = jsonCategory.getJSONArray("transactions");
            addTransactionsToCategory(category, jsonTransactions);
    
            categories.put(name, category);

            category.markAsLoaded();
        }
    
        return categories;
    }
    
    // REQUIRES: a valid category to be provided and a valid jsonarray of transactions
    // MODIFIES: the given Category object by adding the parsed transactions
    private void addTransactionsToCategory(Category category, JSONArray jsonTransactions) {
        for (Object obj : jsonTransactions) {
            JSONObject jsonTransaction = (JSONObject) obj;
            int id = jsonTransaction.getInt("id");
            int amount = jsonTransaction.getInt("amount");
            String description = jsonTransaction.getString("description");
            String dateStr = jsonTransaction.getString("date");
            LocalDate date = LocalDate.parse(dateStr);
    
            Transaction transaction = new Transaction(id, amount, category, description);
            transaction.setDescription(description);
            transaction.setDate(date);
    
            category.getTransactions().put(id, transaction);
        }
    }
}
