package com.jakubolejarczyk.vet_server.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jakubolejarczyk.vet_server.dto.request.ChooseRoleRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ChooseRoleResponseDto;
import com.jakubolejarczyk.vet_server.service.database.AccountService;
import com.jakubolejarczyk.vet_server.service.security.JWTService;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ChooseRoleController {
    private final JWTService jwt;

    private final AccountService accountService;

    @PostMapping("choose-role")
    public ResponseEntity<ChooseRoleResponseDto> auth(@RequestBody ChooseRoleRequestDto requestDto) {
        String token = requestDto.getToken();
        String role = requestDto.getRole();
        String email = jwt.decodeToken(token);
        accountService.updateRole(email, role);
        ChooseRoleResponseDto responseDto = new ChooseRoleResponseDto(true);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
