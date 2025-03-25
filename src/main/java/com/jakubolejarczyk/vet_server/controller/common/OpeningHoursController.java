package com.jakubolejarczyk.vet_server.controller.common;

import com.jakubolejarczyk.vet_server.dto.base.TokenRequestDto;
import com.jakubolejarczyk.vet_server.dto.request.controller.OpeningHoursRequestDto;
import com.jakubolejarczyk.vet_server.dto.request.crud.DeleteRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDataDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import com.jakubolejarczyk.vet_server.model.independent.OpeningHours;
import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.service.step.ResponseStep;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class OpeningHoursController {
    private final ObjectFactory<ResponseStep> responseStep;
    private final ObjectFactory<HandleValidationService> handleValidationService;

    @PostMapping("opening-hours-create")
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody OpeningHoursRequestDto requestDto) {
        // Variables
        val objectResponseStep = responseStep.getObject();
        // Clean response
        objectResponseStep.getRidOfMessages();
        // Response
        objectResponseStep.addMessage("The opening hours has been created successfully!");
        return objectResponseStep.getStep(true);
    }

    @PostMapping("opening-hours-read")
    public ResponseEntity<ResponseDataDto<ArrayList<OpeningHours>>> read(@Valid @RequestBody TokenRequestDto requestDto) {
        // Variables
        val objectResponseStep = responseStep.getObject();
        // Clean response
        objectResponseStep.getRidOfMessages();
        // Response
        objectResponseStep.addMessage("The opening hours has been read successfully!");
        return objectResponseStep.getStep(true, new ArrayList<>());
    }

    @PostMapping("opening-hours-update")
    public ResponseEntity<ResponseDataDto<OpeningHours>> update(@Valid @RequestBody OpeningHoursRequestDto requestDto) {
        // Variables
        val objectResponseStep = responseStep.getObject();
        // Clean response
        objectResponseStep.getRidOfMessages();
        // Response
        objectResponseStep.addMessage("The opening hours has been updated successfully!");
        return objectResponseStep.getStep(true, OpeningHours.builder().build());
    }

    @PostMapping("opening-hours-delete")
    public ResponseEntity<ResponseDto> delete(@Valid @RequestBody DeleteRequestDto requestDto) {
        // Variables
        val objectResponseStep = responseStep.getObject();
        // Clean response
        objectResponseStep.getRidOfMessages();
        // Response
        objectResponseStep.addMessage("The opening hours has been deleted successfully!");
        return objectResponseStep.getStep(true);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        return handleValidationService.getObject().handle(ex);
    }
}
