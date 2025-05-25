package com.olejarczykjakub.vet_server.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class AzureLanguageService {
    @Value("${azure.language.endpoint}")
    private String endpoint;

    @Value("${azure.language.key}")
    private String apiKey;

    public String analyzeText(String text) throws IOException, InterruptedException {
        String url = endpoint + "/text/analytics/v3.1/entities/recognition/general";

        HttpClient client = HttpClient.newHttpClient();
        String requestBody = """
    {
      "documents": [
        {
          "id": "1",
          "language": "pl",
          "text": "%s"
        }
      ]
    }
    """.formatted(text);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Ocp-Apim-Subscription-Key", apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
        return response.body();
    }
}
