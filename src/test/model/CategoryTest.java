package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    private Category testCategory;
    private Transaction testTransaction1;
    private Transaction testTransaction2;
    private List<Transaction> transactions;
    private int budgetCountTemporary;
    
    @BeforeEach
    void runBefore() {
        testTransaction1 = new Transaction(100, null, "TestDesc1"); 
        testTransaction2 = new Transaction(350, null, "TestDesc2"); 
        
        transactions = new ArrayList<>();
        transactions.add(testTransaction1);
        transactions.add(testTransaction2);
        
        testCategory = new Category("TestCategory", 1000, transactions);
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
    Boolean testIsBudgetReached() {
        budgetCountTemporary = 0;
        for (Transaction t : transactions) {
            budgetCountTemporary += t.getAmount();
        }
        
        if (budgetCountTemporary >= testCategory.getBudget()) {
            return true;
        }
        else {
            return false;
        }
    }

}
