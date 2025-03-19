package com.jakubolejarczyk.vet_server.controller.common;

import com.jakubolejarczyk.vet_server.dto.request.controller.LogoutRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.service.step.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.service.step.SuccessResponseStep;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class LogoutController {
    private final GetAccountByTokenStep<String> getAccountByTokenStep;
    private final SuccessResponseStep<String> successResponseStep;
    private final HandleValidationService handleValidationService;

    @PostMapping("logout")
    public ResponseEntity<ResponseDto<String>> logout(@Valid @RequestBody LogoutRequestDto requestDto) {
        val account = getAccountByTokenStep.getAccount(requestDto.getToken());
        if (account.isEmpty()) {
            return getAccountByTokenStep.buildErrorResponse("");
        }
        return successResponseStep.getSuccessResponse("Logged out", "");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<String>> handleValidation(MethodArgumentNotValidException ex) {
        return handleValidationService.handle(ex);
    }
}
