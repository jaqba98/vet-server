package com.jakubolejarczyk.vet_server.controller;

import com.jakubolejarczyk.vet_server.dto.request.controller.ChooseRoleRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.controller.ChooseRoleResponseDto;
import com.jakubolejarczyk.vet_server.model.Account;
import com.jakubolejarczyk.vet_server.model.OpeningHours;
import com.jakubolejarczyk.vet_server.service.database.AccountService;
import com.jakubolejarczyk.vet_server.service.database.ClientService;
import com.jakubolejarczyk.vet_server.service.database.OpeningHoursService;
import com.jakubolejarczyk.vet_server.service.database.VetService;
import com.jakubolejarczyk.vet_server.service.security.TokenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ChooseRoleController {
    private final TokenService tokenService;

    private final AccountService accountService;

    private final VetService vetService;

    private final OpeningHoursService openingHoursService;

    private final ClientService clientService;

    @PostMapping("choose-role")
    public ResponseEntity<ChooseRoleResponseDto> chooseRolePost(@Valid @RequestBody ChooseRoleRequestDto requestDto) {
        String token = requestDto.getToken();
        String role = requestDto.getRole();
        String email = tokenService.decode(token);
        Optional<Account> account = accountService.findByEmail(email);
        if (account.isEmpty()) {
            ChooseRoleResponseDto responseDto = new ChooseRoleResponseDto(false, new ArrayList<>(), role);
            return ResponseEntity.ok().body(responseDto);
        }
        accountService.updateRole(email, role);
        if (role.equals("vet")) {
//            OpeningHours openingHours = openingHoursService.create();
//            vetService.create(account.get(), openingHours);
        }
        else if (role.equals("client")) {
            clientService.create(account.get());
        }
        ChooseRoleResponseDto responseDto = new ChooseRoleResponseDto(true, new ArrayList<>(), role);
        return ResponseEntity.ok().body(responseDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ChooseRoleResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        ArrayList<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String message = error.getDefaultMessage();
            errors.add(message);
        });
        ChooseRoleResponseDto responseDto = new ChooseRoleResponseDto(false, errors, "");
        return ResponseEntity.ok().body(responseDto);
    }
}
