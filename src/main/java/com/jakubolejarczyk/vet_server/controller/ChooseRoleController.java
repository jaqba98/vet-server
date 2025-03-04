package com.jakubolejarczyk.vet_server.controller;

import com.jakubolejarczyk.vet_server.dto.request.ChooseRoleRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ChooseRoleResponseDto;
import com.jakubolejarczyk.vet_server.service.database.AccountService;
import com.jakubolejarczyk.vet_server.service.security.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ChooseRoleController {
    private final TokenService tokenService;

    private final AccountService accountService;

    @PostMapping("choose-role")
    public ResponseEntity<ChooseRoleResponseDto> chooseRolePost(@RequestBody ChooseRoleRequestDto requestDto) {
        String token = requestDto.getToken();
        String role = requestDto.getRole();
        String email = tokenService.decode(token);
        accountService.updateRole(email, role);
        ChooseRoleResponseDto responseDto = ChooseRoleResponseDto.builder()
                .success(true)
                .role(role)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
