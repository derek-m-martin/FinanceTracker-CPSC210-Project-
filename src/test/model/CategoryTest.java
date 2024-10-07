package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    private Category testCategory;
    private Transaction testTransaction1;
    private Transaction testTransaction2;
    private HashMap<UUID, Transaction> transactions;
    
    @BeforeEach
    void runBefore() {
        transactions = new HashMap<UUID, Transaction>();

        testTransaction1 = new Transaction(100, null); 
        testTransaction2 = new Transaction(350, null);
        testCategory = new Category("TestCategory", 1000, transactions); 
        
        transactions.put(UUID.randomUUID(), testTransaction1);
        transactions.put(UUID.randomUUID(), testTransaction2);
    }

    @Test
    void testConstructor() {
        assertEquals("TestCategory", testCategory.getName());
        assertEquals(1000, testCategory.getBudget());
        assertEquals(transactions, testCategory.getTransactions());
    }

    @Test
    void testSetName() {
        testCategory.setName("ChangeName");
        assertEquals("ChangeName", testCategory.getName());
    }

    @Test
    void testSetBudget() {
        testCategory.setBudget(500);
        assertEquals(500, testCategory.getBudget());
    }

    @Test
    void testIsBudgetReached() {
        assertFalse(testCategory.isBudgetReached());
        testCategory.setBudget(100);
        assertTrue(testCategory.isBudgetReached());
    }

}
