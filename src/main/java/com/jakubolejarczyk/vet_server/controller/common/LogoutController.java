package com.jakubolejarczyk.vet_server.controller.common;

import com.jakubolejarczyk.vet_server.dto.request.controller.LogoutRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import com.jakubolejarczyk.vet_server.service.step.ResponseStep;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class LogoutController {
    private final ObjectFactory<ResponseStep> responseStep;

    @PostMapping("logout")
    public ResponseEntity<ResponseDto> logout(@RequestBody LogoutRequestDto requestDto) {
        // Clean response
        responseStep.getObject().getRidOfMessages();
        // Response
        return responseStep.getObject().getStep(true);
    }
}
