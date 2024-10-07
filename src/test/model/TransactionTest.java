package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionTest {

    private Transaction testTransaction;
    private Category testCategory1;
    private Category testCategory2;
    private HashMap<UUID, Transaction> transactions;

    @BeforeEach
    void runBefore() {
        transactions = new HashMap<>();
        testCategory1 = new Category("TestCategory1", 500, transactions);
        testCategory2 = new Category("TestCategory2", 1250, transactions);
        testTransaction = new Transaction(100, testCategory1);
    }

    @Test
    void testConstructor() {
        assertEquals(100, testTransaction.getAmount());
        assertEquals("", testTransaction.getDescription());
        assertEquals(testCategory1, testTransaction.getCategory());
        assertEquals(LocalDate.now(), testTransaction.getDate());
        assertNotNull(testTransaction.getId()); // tests that the UUID is not null (it exists)
        assertDoesNotThrow(() -> UUID.fromString(testTransaction.getId().toString())); // converts the non-null transaction id to a string 
        // then checks if it conforms to UUID format without throwing an exception
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
