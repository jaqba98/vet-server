package com.jakubolejarczyk.vet_server.controller;

import java.util.Map;

import com.jakubolejarczyk.vet_server.model.Account;
import com.jakubolejarczyk.vet_server.repository.AccountRepository;
import com.jakubolejarczyk.vet_server.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.jakubolejarczyk.vet_server.util.ErrorHandlerUtil;
import com.jakubolejarczyk.vet_server.dto.request.RegistrationRequestDto;

@RestController
@RequestMapping("/api/v1")
public class RegistrationController {
    private final ErrorHandlerUtil errorHandlerUtil;

    private final PasswordEncoder passwordEncoder;

    private final AccountService accountService;

    @Autowired
    public RegistrationController(
            ErrorHandlerUtil errorHandlerUtil,
            PasswordEncoder passwordEncoder,
            AccountService accountService
    ) {
        this.errorHandlerUtil = errorHandlerUtil;
        this.passwordEncoder = passwordEncoder;
        this.accountService = accountService;
    }

    @PostMapping("registration")
    public ResponseEntity<?> registration(@Valid @RequestBody RegistrationRequestDto request, BindingResult result) {
        /*
        * 1. Zwalidowanie czy wartości przekazane w request są poprawne +
        * 2. Wyciągnięcie wszystkich wartości z request +
        * 3. Zahashowanie hasła +
        * 4. Zapisanie wartości z request do nowego modelu domain od rejestracji +
        * 5. Zweryfikowaine czy istnieje już taki użytkownik
        * 6. Zapisanie nowego rekordu do bazy danych
        * */
        // Request validation
        Map<String, String> errors = errorHandlerUtil.getErrors(result);
        if (!errors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(request);
        }
        // Pull out all properties out of the request
        String email = request.getEmail();
        String password = request.getPassword();
        String confirmPassword = request.getConfirmPassword();
        String firstName = request.getFirstName();
        String lastName = request.getLastName();
        String role = request.getRole();
        // Hash the password
        String hashPassword = passwordEncoder.encode(password);
        // Create new account ready to save
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(hashPassword);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setRole(role);
        account.setPictureUrl("");
        account.setIsVerified(false);
        accountService.createAccount(account);
        return ResponseEntity.status(HttpStatus.OK).body(hashPassword);
    }
}
