package com.jakubolejarczyk.vet_server.security;

import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.store.StepStore;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Service
@AllArgsConstructor
public class HandleValidationService {
    public ResponseEntity<Response<Null, Null>> handle(StepStore stepStore, MethodArgumentNotValidException ex) {
        stepStore.reset();
        stepStore.setSuccess(false);
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            val message = error.getDefaultMessage();
            stepStore.addMessage(message);
        });
        val success = stepStore.getSuccess();
        val messages = stepStore.getMessages();
        val response = new Response<Null, Null>(success, messages, null, null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
