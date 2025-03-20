package com.jakubolejarczyk.vet_server.controller.common;

import com.jakubolejarczyk.vet_server.dto.request.controller.RegistrationRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.service.step.RegistrationStep;
import com.jakubolejarczyk.vet_server.service.step.ResponseStep;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class RegistrationController {
    private final HandleValidationService handleValidationService;
    private final RegistrationStep registrationStep;
    private final ResponseStep responseStep;

    @PostMapping("registration")
    public ResponseEntity<ResponseDto> registration(@Valid @RequestBody RegistrationRequestDto requestDto) {
        val email = requestDto.getEmail();
        val password = requestDto.getPassword();
        val firstName = requestDto.getFirstName();
        val lastName = requestDto.getLastName();
        registrationStep.runStep(email, password, firstName, lastName);
        responseStep.addMessage("The account has been created successfully!");
        return responseStep.getStep(true);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        return handleValidationService.handle(ex);
    }
}

