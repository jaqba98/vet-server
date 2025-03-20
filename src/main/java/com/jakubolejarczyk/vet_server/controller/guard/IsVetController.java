package com.jakubolejarczyk.vet_server.controller.guard;

import com.jakubolejarczyk.vet_server.dto.request.guard.GuardRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.service.step.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.service.step.IsRoleStep;
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
public class IsVetController {
    private final ResponseStep responseStep;
    private final HandleValidationService handleValidationService;
    private final GetAccountByTokenStep getAccountByTokenStep;
    private final IsRoleStep isRoleStep;

    @PostMapping("is-vet")
    public ResponseEntity<ResponseDto> isVet(@Valid @RequestBody GuardRequestDto requestDto) {
        // Get account by token
        val token = requestDto.getToken();
        val account = getAccountByTokenStep.runStep(token);
        if (!account.getSuccess()) {
            responseStep.addMessage("You are not a vet!");
            return responseStep.getStep(false);
        }
        val accountData = account.getData();
        // ...
        return responseStep.getStep(isRoleStep.runStep(accountData, "vet"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        return handleValidationService.handle(ex);
    }
}
