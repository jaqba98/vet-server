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
        val account = getAccountByTokenStep.runStep(requestDto.getToken());
        if (account.isEmpty()) {
            return responseStep.getStep(false);
        }
        val accountByToken = account.get();
        return responseStep.getStep(isRoleStep.runStep(accountByToken, "vet"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        return handleValidationService.handle(ex);
    }
}
