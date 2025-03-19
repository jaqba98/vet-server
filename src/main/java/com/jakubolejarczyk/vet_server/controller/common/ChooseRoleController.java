package com.jakubolejarczyk.vet_server.controller.common;

import com.jakubolejarczyk.vet_server.dto.request.controller.ChooseRoleRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.service.step.GetAccountByToken;
import com.jakubolejarczyk.vet_server.service.step.SuccessRole;
import com.jakubolejarczyk.vet_server.service.step.UpdateAccountRole;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ChooseRoleController {
    private final GetAccountByToken<String> getAccountByToken;
    private final UpdateAccountRole updateAccountRole;
    private final HandleValidationService handleValidationService;
    private final SuccessRole<String> successRole;

    @PostMapping("choose-role")
    public ResponseEntity<ResponseDto<String>> chooseRole(@Valid @RequestBody ChooseRoleRequestDto requestDto) {
        val account = getAccountByToken.getAccount(requestDto.getToken());
        if (account.isEmpty()) {
            return getAccountByToken.buildErrorResponse("");
        }
        this.updateAccountRole.updateRole(account.get(), requestDto.getRole());
        return successRole.getSuccessResponse("The role has been chosen!", "");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<String>> handleValidation(MethodArgumentNotValidException ex) {
        return handleValidationService.handle(ex);
    }
}
