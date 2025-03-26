package com.jakubolejarczyk.vet_server.controller.common;

import com.jakubolejarczyk.vet_server.dto.request.controller.RegistrationRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.service.step_old.CreateAccountStep;
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
public class RegistrationController {
    private final ObjectFactory<HandleValidationService> handleValidationService;
    private final CreateAccountStep createAccountStep;
    private final ObjectFactory<ResponseStep> responseStep;

    @PostMapping("registration")
    public ResponseEntity<ResponseDto> registration(@Valid @RequestBody RegistrationRequestDto requestDto) {
        // Clean response
        responseStep.getObject().getRidOfMessages();
        // Create account
        val email = requestDto.getEmail();
        val password = requestDto.getPassword();
        val firstName = requestDto.getFirstName();
        val lastName = requestDto.getLastName();
        createAccountStep.runStep(email, password, firstName, lastName);
        // Response
        responseStep.getObject().addMessage("The account has been created successfully!");
        return responseStep.getObject().getStep(true);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        return handleValidationService.getObject().handle(ex);
    }
}

