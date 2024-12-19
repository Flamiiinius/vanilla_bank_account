package com.flaminius.model;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TransactionTest {
    @Test
    void testIdClashWithConcurrency() throws InterruptedException {
        final int numThreads = 100;
        final int transactionsPerThread = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        Set<Long> transactionIds = Collections.synchronizedSet(new HashSet<>());
        Set<Long> duplicates = Collections.synchronizedSet(new HashSet<>());

        for (int i = 0; i < numThreads; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < transactionsPerThread; j++) {
                    Transaction transaction = new Transaction(1, TransactionType.DEPOSIT, 100.0);
                    // If the ID already exists in the set, it's a duplicate
                    if (!transactionIds.add(transaction.getId())) {
                        duplicates.add(transaction.getId());
                    }
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        // Assert that there were ID clashes
        assertTrue(duplicates.isEmpty(), "ID clashes should occur with the current implementation.");
    }
}