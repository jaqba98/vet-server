package com.jakubolejarczyk.vet_server.service.security;

import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import jakarta.validation.constraints.Null;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;

@Service
public class HandleValidationService {
    public ResponseEntity<ResponseDto<Null>> handle(MethodArgumentNotValidException ex) {
        val errors = new ArrayList<String>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String message = error.getDefaultMessage();
            errors.add(message);
        });
        val responseDto = new ResponseDto<Null>(false, errors, null);
        return ResponseEntity.ok().body(responseDto);
    }
}
