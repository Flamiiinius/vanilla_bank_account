package com.flaminius.integration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flaminius.Main;
import com.flaminius.model.BankAccount;
import com.flaminius.model.Transaction;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankServerIntegrationTest {

    private static final String BASE_URL = "http://localhost:8080";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    void startServer() throws IOException {
        Main.main(new String[0]); // Start the server
    }

    @AfterAll
    void stopServer() {
        // Assuming we can programmatically stop the server if needed
        System.out.println("Tests completed");
    }

    @Test
    void testCreateBankAccount() throws IOException, InterruptedException {
        // Prepare the request body
        String requestBody = """
                {
                    "id": 1,
                    "money": 1000
                }
                """;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/bankaccounts"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(201, response.statusCode());
        assertNotNull(response.body());
        System.out.println("response: " + response.body());

        ObjectMapper objectMapper = new ObjectMapper();
        BankAccount bankAccount = objectMapper.readValue(response.body(), BankAccount.class);

        assertEquals(bankAccount.getBalance(),0.0);
    }

    /*@Test
    void testGetBankAccountById() throws IOException, InterruptedException {
        int bankAccountId = 1;

        // Send the request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/bankaccounts/" + bankAccountId))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Assert the response
        assertEquals(200, response.statusCode());
        assertNotNull(response.body());

        // Deserialize and verify
        BankAccount account = objectMapper.readValue(response.body(), BankAccount.class);
        assertEquals(1, account.getId());
        assertEquals(1000, account.getMoney());
    }

    @Test
    void testCreateTransaction() throws IOException, InterruptedException {
        // Prepare the request body
        String requestBody = """
                {
                    "type": "deposit",
                    "amount": 500
                }
                """;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/transactions"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        // Send the request
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Assert the response
        assertEquals(201, response.statusCode());
        assertNotNull(response.body());
        System.out.println(response.body());
    }

    @Test
    void testGetTransactionById() throws IOException, InterruptedException {
        int transactionId = 1;

        // Send the request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/transactions/" + transactionId))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Assert the response
        assertEquals(200, response.statusCode());
        assertNotNull(response.body());

        // Deserialize and verify
        Transaction transaction = objectMapper.readValue(response.body(), Transaction.class);
        assertEquals(transactionId, transaction.getId());
        assertEquals("deposit", transaction.getType());
    }*/
}