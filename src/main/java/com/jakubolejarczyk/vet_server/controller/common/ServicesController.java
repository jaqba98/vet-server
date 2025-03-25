package com.jakubolejarczyk.vet_server.controller.common;

import com.jakubolejarczyk.vet_server.dto.base.TokenRequestDto;
import com.jakubolejarczyk.vet_server.dto.request.controller.ClinicRequestDto;
import com.jakubolejarczyk.vet_server.dto.request.controller.ServicesRequestDto;
import com.jakubolejarczyk.vet_server.dto.request.crud.DeleteRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDataDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.model.dependent.Services;
import com.jakubolejarczyk.vet_server.service.crud.dependent.ClinicService;
import com.jakubolejarczyk.vet_server.service.crud.dependent.ServicesService;
import com.jakubolejarczyk.vet_server.service.crud.independent.OpeningHoursService;
import com.jakubolejarczyk.vet_server.service.crud.relation.ClinicAccountService;
import com.jakubolejarczyk.vet_server.service.crud.relation.OwnerService;
import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.service.step.AccountClinicsStep;
import com.jakubolejarczyk.vet_server.service.step.GetAccountByTokenStep;
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
public class ServicesController {
    private final ObjectFactory<ResponseStep> responseStep;
    private final GetAccountByTokenStep getAccountByTokenStep;
    private final ObjectFactory<HandleValidationService> handleValidationService;
    private final ClinicAccountService clinicAccountService;
    private final OwnerService ownerService;
    private final OpeningHoursService openingHoursService;
    private final ClinicService clinicService;
    private final AccountClinicsStep accountClinicsStep;
    private final ServicesService servicesService;

    @PostMapping("service-create")
    public ResponseEntity<ResponseDto> service(@Valid @RequestBody ServicesRequestDto requestDto) {
        // Clean response
        responseStep.getObject().getRidOfMessages();
        // Create a services
        val services = Services.builder()
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .category(requestDto.getCategory())
                .durationMinutes(requestDto.getDescription())
                .price(requestDto.getPrice())
                .isAvailable(requestDto.getIsAvailable())
                .clinicId(requestDto.getClinicId())
                .build();
        servicesService.create(services);
        // Response
        responseStep.getObject().addMessage("The clinic has been established successfully!");
        return responseStep.getObject().getStep(true);
    }

    @PostMapping("service-read")
    public ResponseEntity<ResponseDataDto<ArrayList<Services>>> read(@Valid @RequestBody TokenRequestDto requestDto) {
        // Clean response
        responseStep.getObject().getRidOfMessages();
        // Find all services
        val services = servicesService.read();
        // Response
        responseStep.getObject().addMessage("The clinics have been read successfully!");
        return responseStep.getObject().getStep(true, new ArrayList<>(services));
    }

    @PostMapping("service-update")
    public ResponseEntity<ResponseDataDto<Services>> update(@Valid @RequestBody ServicesRequestDto requestDto) throws Exception {
        // Clean response
        responseStep.getObject().getRidOfMessages();
        // Get the clinic by id
        val clinicId = requestDto.getId();
        val services = servicesService.findById(clinicId);
        if (services.isEmpty()) {
            responseStep.getObject().addMessage("The service does not exist!");
            return responseStep.getObject().getStep(false, Services.builder().build());
        }
        // Merge new clinic with existed clinic
        val servicesToUpdate = services.get();
        servicesToUpdate.setName(requestDto.getName());
        servicesToUpdate.setDescription(requestDto.getDescription());
        servicesToUpdate.setCategory(requestDto.getCategory());
        servicesToUpdate.setDurationMinutes(requestDto.getDurationMinutes());
        servicesToUpdate.setPrice(requestDto.getPrice());
        servicesToUpdate.setIsAvailable(requestDto.getIsAvailable());
        servicesToUpdate.setClinicId(requestDto.getClinicId());
        servicesService.update(servicesToUpdate);
        // Response
        responseStep.getObject().addMessage("The clinic has been updated successfully!");
        return responseStep.getObject().getStep(true, servicesToUpdate);
    }

    @PostMapping("service-delete")
    public ResponseEntity<ResponseDto> delete(@Valid @RequestBody DeleteRequestDto requestDto) {
        // Clean response
        responseStep.getObject().getRidOfMessages();
        // Delete services
        val servicesIds = requestDto.getIds();
        servicesService.deleteAllByIdInBatch(servicesIds);
        // Response
        responseStep.getObject().addMessage("Clinics were deleted successfully!");
        return responseStep.getObject().getStep(true);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        return handleValidationService.getObject().handle(ex);
    }
}
