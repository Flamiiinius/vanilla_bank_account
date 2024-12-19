package com.flaminius;

import com.flaminius.controller.BankController;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        // Create an HTTP server on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Create and bind controllers to routes
        server.createContext("/transactions", exchange -> new BankController().handleTransaction(exchange));

        server.createContext("/bankaccounts", exchange -> new BankController().createBankAccount(exchange));

        server.createContext("/bankaccounts/{id}", exchange -> new BankController().getBankAccountById(exchange));

        server.createContext("/transactions/{id}", exchange -> new BankController().getTransactionById(exchange));

        server.setExecutor(null);
        server.start();
        System.out.println("Server started on port 8080...");
    }


}