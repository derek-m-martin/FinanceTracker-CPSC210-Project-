package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    private Category testCategory;
    private Transaction testTransaction1;
    private Transaction testTransaction2;
    
    @BeforeEach
    void runBefore() {
        testCategory = new Category("TestCategory", 1000); 
        
        testTransaction1 = new Transaction(100, testCategory); 
        testTransaction2 = new Transaction(350, testCategory);

        testCategory.getTransactions().put(testTransaction1.getId(), testTransaction1);
        testCategory.getTransactions().put(testTransaction2.getId(), testTransaction2);
    }

    @Test
    void testConstructor() {
        assertEquals("TestCategory", testCategory.getName());
        assertEquals(1000, testCategory.getBudget());
        assertEquals(2, testCategory.getTransactions().size());
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
        testCategory.setBudget(400);
        assertTrue(testCategory.isBudgetReached());
    }
}
