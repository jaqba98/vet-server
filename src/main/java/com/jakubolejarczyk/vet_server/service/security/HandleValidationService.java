package com.jakubolejarczyk.vet_server.service.security;

import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.service.response.ResponseService;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Service
@AllArgsConstructor
public class HandleValidationService {
    private final ObjectFactory<ResponseService<Null, Null>> responseStep;

    public ResponseEntity<Response<Null, Null>> handle(MethodArgumentNotValidException ex) {
        responseStep.getObject().cleanUp();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            val message = error.getDefaultMessage();
            responseStep.getObject().addMessage(message);
        });
        return responseStep.getObject().getResponse(false, null, null);
    }
}
