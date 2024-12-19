package com.flaminius;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class HttpResponseUtils {

    public static void sendResponse(HttpExchange exchange, Object response, int statusCode) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        String jsonResponse = new ObjectMapper().writeValueAsString(response);
        exchange.sendResponseHeaders(statusCode, jsonResponse.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(jsonResponse.getBytes());
        }
    }
}