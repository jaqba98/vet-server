package com.olejarczykjakub.vet_server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class SpeechController {

    @Value("${azure.speech.key}")
    private String azureKey;

    @Value("${azure.speech.region}")
    private String azureRegion;

    @PostMapping(value = "/speech-to-text", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String speechToTextRest(@RequestParam("file") MultipartFile file) {
        try {
            File tempWebm = File.createTempFile("audio-", ".webm");
            try (FileOutputStream fos = new FileOutputStream(tempWebm)) {
                fos.write(file.getBytes());
            }
            File tempWav = File.createTempFile("audio-converted-", ".wav");
            ProcessBuilder pb = new ProcessBuilder(
                    "ffmpeg",
                    "-i", tempWebm.getAbsolutePath(),
                    "-ar", "16000",
                    "-ac", "1",
                    "-y",
                    tempWav.getAbsolutePath()
            );
            pb.inheritIO();
            Process p = pb.start();
            if (p.waitFor() != 0) {
                throw new RuntimeException("Konwersja ffmpeg nie powiodła się");
            }
            String url = "https://" + azureRegion + ".stt.speech.microsoft.com/speech/recognition/conversation/cognitiveservices/v1?language=pl-PL";
            HttpClient httpClient = HttpClient.newHttpClient();
            byte[] audioData = Files.readAllBytes(tempWav.toPath());
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Ocp-Apim-Subscription-Key", azureKey)
                    .header("Content-Type", "audio/wav")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(audioData))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            tempWebm.delete();
            tempWav.delete();
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                return "Błąd rozpoznawania: " + response.statusCode() + " " + response.body();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Błąd serwera: " + e.getMessage();
        }
    }
}
