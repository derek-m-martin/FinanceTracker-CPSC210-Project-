package persistence;

import model.Category;
import model.Transaction;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/nofile.json");
        try {
            HashMap<String, Category> categories = reader.readCategories();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCategories() {
        JsonReader reader = new JsonReader("data/testReaderEmptyCategories.json");
        try {
            HashMap<String, Category> categories = reader.readCategories();
            assertEquals(5, categories.size());
            assertEquals(0, categories.get("FOOD").getTransactions().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderNonEmptyCategories() {
        JsonReader reader = new JsonReader("data/testReaderNonEmptyCategories.json");
        try {
            HashMap<String, Category> categories = reader.readCategories();
            assertEquals(5, categories.size());

            Category foodCategory = categories.get("FOOD");
            assertNotNull(foodCategory);
            assertEquals(900, foodCategory.getBudget());
            assertEquals(1, foodCategory.getTransactions().size());

            Transaction transaction = foodCategory.getTransactions().get(1);
            checkTransaction(1, 100, "Grocery shopping", "FOOD", transaction);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
