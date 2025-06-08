package com.olejarczykjakub.vet_server.controller;

import com.olejarczykjakub.vet_server.service.AzureLanguageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LanguageController {
    private final AzureLanguageService azureLanguageService;

    public LanguageController(AzureLanguageService azureLanguageService) {
        this.azureLanguageService = azureLanguageService;
    }

    @PostMapping("/analyze")
    public ResponseEntity<String> analyzeText(@RequestBody Map<String, String> payload) {
        String text = payload.get("text");
        try {
            String analysisResult = azureLanguageService.analyzeText(text);
            return ResponseEntity.ok(analysisResult);
        } catch (IOException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
