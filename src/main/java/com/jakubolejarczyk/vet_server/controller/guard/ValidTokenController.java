package com.jakubolejarczyk.vet_server.controller.guard;

import com.jakubolejarczyk.vet_server.dto.request.guard.GuardRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.service.security.TokenService;
import com.jakubolejarczyk.vet_server.service.step_old.ResponseStep;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ValidTokenController {
    private final ObjectFactory<ResponseStep> responseStep;
    private final TokenService tokenService;
    private final ObjectFactory<HandleValidationService> handleValidationService;

    @PostMapping("valid-token")
    public ResponseEntity<ResponseDto> validToken(@Valid @RequestBody GuardRequestDto requestDto) {
        // Clean response
        responseStep.getObject().getRidOfMessages();
        // Check if the token is valid
        val token = requestDto.getToken();
        val isValid = tokenService.verify(token);
        // Response
        return responseStep.getObject().getStep(isValid);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        return handleValidationService.getObject().handle(ex);
    }
}
