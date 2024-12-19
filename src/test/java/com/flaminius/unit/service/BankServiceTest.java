package com.flaminius.unit.service;

import com.flaminius.model.BankAccount;
import com.flaminius.service.BankService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BankServiceTest {
    private BankService bankService;

    @BeforeEach
    void setUp() {
        bankService = new BankService();
    }

    @Test
    void testCreateNewBankAccount(){
        bankService.createBankAccount();
    }

    /*@Test
    void testWithdrawSuccess() throws Exception {
        BankAccount account = new BankAccount("123", 500.0);
        when(bankService.getBankAccountById("123")).thenReturn(account);

        bankService.withdraw("123", 100.0);

        // Verify balance update
        verify(bankAccountRepository).save(argThat(savedAccount ->
                savedAccount.getId().equals("123") && savedAccount.getBalance() == 400.0));
    }

    @Test
    void testWithdrawInsufficientFunds() {
        BankAccount account = new BankAccount("123", 50.0);
        when(bankAccountRepository.findById("123")).thenReturn(account);

        assertThrows(InsufficientFundsException.class, () -> {
            bankService.withdraw("123", 100.0);
        });

        // Ensure no updates were made to the repository
        verify(bankAccountRepository, never()).save(any());
    }

    @Test
    void testWithdrawAccountNotFound() {
        when(bankAccountRepository.findById("123")).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> {
            bankService.withdraw("123", 100.0);
        });

        // Ensure no updates were made to the repository
        verify(bankAccountRepository, never()).save(any());
    }*/
}