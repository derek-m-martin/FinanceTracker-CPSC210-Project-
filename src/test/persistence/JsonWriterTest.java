package persistence;

import model.Category;
import model.Transaction;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Category cat = new Category("Test", 150);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCategories() {
        try {
            HashMap<String, Category> categories = createEmptyCategories();
            JsonWriter writer = new JsonWriter("data/testWriterEmptyCategories.json");
            writer.open();
            writer.write(categoriesToJson(categories));
            writer.close();

            JsonReader reader = new JsonReader("data/testWriterEmptyCategories.json");
            categories = reader.readCategories();
            assertEquals(5, categories.size());
            assertEquals(0, categories.get("FOOD").getTransactions().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterNonEmptyCategories() {
        try {
            HashMap<String, Category> categories = createTestCategories();
            JsonWriter writer = new JsonWriter("data/testWriterNonEmptyCategories.json");
            writer.open();
            writer.write(categoriesToJson(categories));
            writer.close();

            JsonReader reader = new JsonReader("data/testWriterNonEmptyCategories.json");
            categories = reader.readCategories();

            assertEquals(5, categories.size());
            Category foodCategory = categories.get("FOOD");
            assertNotNull(foodCategory);
            assertEquals(900, foodCategory.getBudget());

            Transaction transaction = foodCategory.getTransactions().get(1);
            checkTransaction(1, 100, "Grocery shopping", "FOOD", transaction);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    // Helper methods for creating test data
    private HashMap<String, Category> createEmptyCategories() {
        HashMap<String, Category> categories = new HashMap<>();
        categories.put("FOOD", new Category("FOOD", 1000));
        categories.put("TRANSPORTATION", new Category("TRANSPORTATION", 1000));
        categories.put("ENTERTAINMENT", new Category("ENTERTAINMENT", 1000));
        categories.put("HOUSING", new Category("HOUSING", 1000));
        categories.put("MISCELLANEOUS", new Category("MISCELLANEOUS", 1000));
        return categories;
    }

    private HashMap<String, Category> createTestCategories() {
        HashMap<String, Category> categories = createEmptyCategories();
        Category foodCategory = categories.get("FOOD");
        Transaction transaction1 = new Transaction(1, 100, foodCategory, "test1");
        transaction1.setDescription("Grocery shopping");
        foodCategory.getTransactions().put(1, transaction1);
        foodCategory.setBudget(900);

        Category transportationCategory = categories.get("TRANSPORTATION");
        Transaction transaction2 = new Transaction(2, 50, transportationCategory, "test2");
        transaction2.setDescription("Bus fare");
        transportationCategory.getTransactions().put(2, transaction2);
        transportationCategory.setBudget(950);

        return categories;
    }

    // Converts categories to JSON object
    private JSONObject categoriesToJson(HashMap<String, Category> categories) {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Category category : categories.values()) {
            jsonArray.put(category.toJson());
        }
        json.put("categories", jsonArray);
        return json;
    }
}
