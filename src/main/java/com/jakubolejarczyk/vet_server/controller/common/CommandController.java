package com.jakubolejarczyk.vet_server.controller.common;

import com.microsoft.cognitiveservices.speech.ResultReason;
import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechRecognitionResult;
import com.microsoft.cognitiveservices.speech.SpeechRecognizer;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class CommandController {

    // Wstrzykujemy klucze i region z pliku konfiguracyjnego
    @Value("${azure.speechKey}")
    private String speechKey;

    @Value("${azure.speechRegion}")
    private String speechRegion;

    // Wstrzykujemy ResourceLoader, który będzie służył do ładowania plików
    private final ResourceLoader resourceLoader;

    @GetMapping("command")
    public String command() {
        try {
            // Wczytujemy plik audio z zasobów
            Resource resource = resourceLoader.getResource("classpath:static/hello-world.wav");
            File audioFile = resource.getFile();

            // Tworzymy konfigurację dla Azure Speech-to-Text
            SpeechConfig speechConfig = SpeechConfig.fromSubscription(speechKey, speechRegion);
            speechConfig.setSpeechRecognitionLanguage("en-US");  // Ustawiamy język rozpoznawania mowy

            // Tworzymy konfigurację audio z pliku
            AudioConfig audioConfig = AudioConfig.fromWavFileInput(audioFile.getAbsolutePath());

            // Inicjalizujemy rozpoznawanie mowy
            SpeechRecognizer speechRecognizer = new SpeechRecognizer(speechConfig, audioConfig);

            // Rozpoczynamy rozpoznawanie mowy (synchronizacja z `get()`)
            Future<SpeechRecognitionResult> task = speechRecognizer.recognizeOnceAsync();
            SpeechRecognitionResult speechRecognitionResult = task.get();  // Czekamy na wynik

            // Sprawdzamy, czy rozpoznano mowę
            if (speechRecognitionResult.getReason() == ResultReason.RecognizedSpeech) {
                return "RECOGNIZED: Text=" + speechRecognitionResult.getText();
            } else {
                return "Speech recognition failed";
            }

        } catch (IOException | InterruptedException | ExecutionException e) {
            // Obsługuje błędy związane z odczytem pliku lub rozpoznawaniem mowy
            e.printStackTrace();
            return "Error occurred: " + e.getMessage();
        }
    }
}
