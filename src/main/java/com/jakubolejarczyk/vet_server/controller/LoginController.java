//package com.jakubolejarczyk.vet_server.controller;
//
//import lombok.AllArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import com.jakubolejarczyk.vet_server.service.AccountService;
//import com.jakubolejarczyk.vet_server.dto.response.LoginResponseDto;
//import com.jakubolejarczyk.vet_server.dto.request.LoginRequestDto;
//import com.jakubolejarczyk.vet_server.dto.response.RegistrationResponseDto;
//
//@RestController
//@RequestMapping("/api/v1/login")
//@AllArgsConstructor
//public class LoginController {
//    private final AccountService accountService;
//
//    @PostMapping
//    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
//        String email = request.getEmail();
//        Boolean exist = accountService.isAccountByEmail(email);
//        if (exist) {
//            RegistrationResponseDto response = new RegistrationResponseDto();
//        }
//
//        Optional<Account> account = accountService.getAccountByEmail(email);
//        if (account.isPresent()) {
//            LoginResponseDto response = new LoginResponseDto();
//            response.setSuccess(true);
//            response.setToken("123");
//            return ResponseEntity.status(HttpStatus.OK).body(response);
//        }
//        LoginResponseDto response = new LoginResponseDto();
//        response.setSuccess(false);
//        response.setToken("");
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//    }
//}
