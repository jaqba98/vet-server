package com.jakubolejarczyk.vet_server.controller.guard;

import com.jakubolejarczyk.vet_server.dto.request.guard.GuardRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDataDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.service.step.GetAccountByTokenStep;
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
public class GetAccountController {
    private final ResponseStep responseStep;
    private final HandleValidationService handleValidationService;
    private final GetAccountByTokenStep getAccountByTokenStep;

    @PostMapping("get-account")
    public ResponseEntity<ResponseDataDto<Account>> getAccount(@Valid @RequestBody GuardRequestDto requestDto) {
        val account = getAccountByTokenStep.runStep(requestDto.getToken());
        if (account.isEmpty()) {
            return responseStep.getStep(false, Account.builder().build());
        }
        val accountByToken = account.get();
        Account newAccount = Account.builder()
                .email(accountByToken.getEmail())
                .firstName(accountByToken.getFirstName())
                .lastName(accountByToken.getLastName())
                .role(accountByToken.getRole())
                .pictureUrl(accountByToken.getPictureUrl())
                .build();
        return responseStep.getStep(true, newAccount);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        return handleValidationService.handle(ex);
    }
}

