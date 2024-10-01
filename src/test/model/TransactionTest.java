package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionTest {

    private Transaction testTransaction;
    private Category testCategory1;
    private Category testCategory2;
    private List<Transaction> transactions;

    @BeforeEach
    void runBefore() {
        transactions = new ArrayList<>();
        testCategory1 = new Category("TestCategory1", 500, transactions);
        testCategory2 = new Category("TestCategory2", 1250, transactions);
        testTransaction = new Transaction(100, testCategory1, "TestDescription");
    }

    @Test
    void testConstructor() {
        assertEquals(100, testTransaction.getAmount());
        assertEquals("TestDescription", testTransaction.getDescription());
        assertEquals(null, testTransaction.getCategory());
    }

    @Test 
    void testSetAmount() {
        testTransaction.setAmount(500);
        assertEquals(500, testTransaction.getAmount());
    }

    @Test
    void testSetDescription() {
        testTransaction.setDescription("Tested");
        assertEquals("Tested", testTransaction.getDescription());
    }

    @Test
    void testMoveTransaction() {
        assertEquals(testCategory1, testTransaction.getCategory());
        testTransaction.moveTransaction(testCategory2);
        assertEquals(testCategory2, testTransaction.getCategory());
    }
}
