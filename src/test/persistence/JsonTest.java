package persistence;

import model.Category;
import model.Transaction;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkTransaction(int id, int amount, String description, String categoryName, 
            Transaction transaction) {
        assertEquals(id, transaction.getId());
        assertEquals(amount, transaction.getAmount());
        assertEquals(description, transaction.getDescription());
        assertEquals(categoryName, transaction.getCategory().getName());
    }

    protected void checkCategory(String name, int budget, Category category) {
        assertEquals(name, category.getName());
        assertEquals(budget, category.getBudget());
    }
}
