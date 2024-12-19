package com.flaminius.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flaminius.HttpResponseUtils;
import com.flaminius.model.BankAccount;

import com.flaminius.model.Transaction;
import com.flaminius.service.BankService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.UUID;

public class BankController {

    private final BankService bankService;

    public BankController() {
        this.bankService = new BankService();
    }

    // Handle GET /bankaccounts/{id}
    public void getBankAccountById(HttpExchange exchange) throws IOException {
        String id = exchange.getRequestURI().getPath().split("/")[2];
        BankAccount bankAccount = bankService.getBankAccountById(Integer.parseInt(id));

        if (bankAccount != null) {
            HttpResponseUtils.sendResponse(exchange, bankAccount, 200);
        } else {
            HttpResponseUtils.sendResponse(exchange, "Bank account not found", 404);
        }
    }

    // Handle POST /bankaccounts
    public void createBankAccount(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {

            BankAccount account = bankService.createBankAccount();

            // Send response using the utility class
            HttpResponseUtils.sendResponse(exchange, account, 201);
        }
    }

    private BankAccount parseBankAccountRequest(HttpExchange exchange) throws IOException {
        return new ObjectMapper().readValue(exchange.getRequestBody(), BankAccount.class);
    }

    // Handle POST /transactions
    public void handleTransaction(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            // Parse the request body and create a new transaction
            Transaction transaction = parseTransactionRequest(exchange);
            bankService.processTransaction(transaction);

            // Send response using the utility class
            HttpResponseUtils.sendResponse(exchange, "Transaction created", 201);
        }
    }

    // Handle GET /transactions/{id}
    public void getTransactionById(HttpExchange exchange) throws IOException {
        String id = exchange.getRequestURI().getPath().split("/")[2];
        /*Transaction transaction = bankService.get(id);

        if (transaction != null) {
            HttpResponseUtils.sendResponse(exchange, transaction, 200);
        } else {

         */
            HttpResponseUtils.sendResponse(exchange, "Transaction not found", 404);
      //  }
    }

    private Transaction parseTransactionRequest(HttpExchange exchange) throws IOException {
        return new ObjectMapper().readValue(exchange.getRequestBody(), Transaction.class);
    }
}