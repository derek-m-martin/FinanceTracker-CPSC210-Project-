package model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionTest {

    private Transaction testTransaction;
    private Category testCategory1;
    private Category testCategory2;

    @BeforeEach
    void runBefore() {
        testCategory1 = new Category("TestCategory1", 500);
        testCategory2 = new Category("TestCategory2", 1250);
        testTransaction = new Transaction(1, 100, testCategory1);
    }

    @Test
    void testConstructor() {
        assertEquals(100, testTransaction.getAmount());
        assertEquals("", testTransaction.getDescription());
        assertEquals(testCategory1, testTransaction.getCategory());
        assertEquals(LocalDate.now(), testTransaction.getDate());
        assertEquals(1, testTransaction.getId());
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

    @Test
    void testDeleteTransaction() {
        assertEquals(testTransaction, testCategory1.getTransactions().get(1));
        testTransaction.deleteTransaction();
        assertEquals(null, testCategory1.getTransactions().get(1));
    }

    @Test
    void testSetId() {
        assertEquals(1, testTransaction.getId());
        testTransaction.setId(5);
        assertEquals(5, testTransaction.getId());
    }
}
