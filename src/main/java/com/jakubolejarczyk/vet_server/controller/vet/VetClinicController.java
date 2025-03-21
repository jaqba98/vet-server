package com.jakubolejarczyk.vet_server.controller.vet;

import com.jakubolejarczyk.vet_server.dto.request.controller.VetClinicRequestDto;
import com.jakubolejarczyk.vet_server.dto.request.crud.DeleteRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDataDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.model.independent.OpeningHours;
import com.jakubolejarczyk.vet_server.model.relation.ClinicAccount;
import com.jakubolejarczyk.vet_server.model.relation.Owner;
import com.jakubolejarczyk.vet_server.service.crud.dependent.ClinicService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/vet-clinic")
@AllArgsConstructor
public class VetClinicController {
    private final ResponseStep responseStep;
    private final GetAccountByTokenStep getAccountByTokenStep;
    private final HandleValidationService handleValidationService;
    private final ClinicAccountService clinicAccountService;
    private final OwnerService ownerService;
    private final OpeningHoursService openingHoursService;
    private final ClinicService clinicService;
    private final AccountClinicsStep accountClinicsStep;

    @PostMapping("create")
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody VetClinicRequestDto requestDto) {
        // Create an OpeningHours object
        OpeningHours openingHours = OpeningHours.builder().build();
        val newOpeningHours = openingHoursService.create(openingHours);
        // Create a Clinic object
        val clinic = Clinic.builder()
                .name(requestDto.getName())
                .street(requestDto.getStreet())
                .buildingNumber(requestDto.getBuildingNumber())
                .apartmentNumber(requestDto.getApartmentNumber())
                .postalCode(requestDto.getPostalCode())
                .city(requestDto.getCity())
                .province(requestDto.getProvince())
                .country(requestDto.getCountry())
                .email(requestDto.getEmail())
                .phoneNumber(requestDto.getPhoneNumber())
                .openingHoursId(openingHours.getId())
                .build();
        val newClinic = clinicService.create(clinic);
        // ...
        // Get account by token
        val token = requestDto.getToken();
        val account = getAccountByTokenStep.runStep(token);
        if (!account.getSuccess()) {
            responseStep.addMessage("Failed to create clinic!");
            return responseStep.getStep(false);
        }
        val accountData = account.getData();

        // ...
        val accountId = accountData.getId();
        // Create an Owner object
        Owner owner = Owner.builder()
                .accountId(accountId)
                .clinicId(newClinic.getId())
                .build();
        ownerService.create(owner);
        // Create a Clinic_Account object
        ClinicAccount clinicAccount = ClinicAccount.builder()
                .accountId(accountId)
                .clinicId(newClinic.getId())
                .build();
        clinicAccountService.create(clinicAccount);
        // Return the response dto
        responseStep.addMessage("The clinic has been established successfully!");
        return responseStep.getStep(true);
    }

    @PostMapping("read")
    public ResponseEntity<ResponseDataDto<ArrayList<Clinic>>> read(@Valid @RequestBody VetClinicRequestDto requestDto) {
        // Get account by token
        val token = requestDto.getToken();
        val account = getAccountByTokenStep.runStep(token);
        if (!account.getSuccess()) {
            responseStep.addMessage("Failed to read clinics!");
            return responseStep.getStep(false, new ArrayList<>());
        }
        val accountData = account.getData();
        // Find all clinics owned by the account
        val clinics = accountClinicsStep.runStep(accountData);
        // Response
        responseStep.addMessage("The clinics have been read successfully!");
        return responseStep.getStep(true, clinics.getData());
    }

    @PostMapping("update")
    public ResponseEntity<ResponseDto> update(@Valid @RequestBody VetClinicRequestDto requestDto) throws Exception {
        // Get account by token
        val token = requestDto.getToken();
        val account = getAccountByTokenStep.runStep(token);
        if (!account.getSuccess()) {
            responseStep.addMessage("Failed to update clinic!");
            return responseStep.getStep(false);
        }
        val accountData = account.getData();
        // ...
        val accountId = accountData.getId();
        val clinicId = requestDto.getId();

        val own = ownerService.findByAccountIdAndClinicId(accountId, clinicId);
        if (own.isEmpty()) {
            responseStep.addMessage("You do not have permission to update!");
            return responseStep.getStep(false);
        }
        val clinic = clinicService.findById(clinicId);
        if (clinic.isEmpty()) {
            responseStep.addMessage("The clinic does not exist!");
            return responseStep.getStep(false);
        }
        val clinicToUpdate = clinic.get();
        clinicToUpdate.setName(requestDto.getName());
        clinicToUpdate.setStreet(requestDto.getStreet());
        clinicToUpdate.setBuildingNumber(requestDto.getBuildingNumber());
        clinicToUpdate.setApartmentNumber(requestDto.getApartmentNumber());
        clinicToUpdate.setPostalCode(requestDto.getPostalCode());
        clinicToUpdate.setCity(requestDto.getCity());
        clinicToUpdate.setProvince(requestDto.getProvince());
        clinicToUpdate.setCountry(requestDto.getCountry());
        clinicToUpdate.setEmail(requestDto.getEmail());
        clinicToUpdate.setPhoneNumber(requestDto.getPhoneNumber());
        clinicService.update(clinicToUpdate);
        responseStep.addMessage("The clinic has been updated successfully!");
        return responseStep.getStep(true);
    }

    @PostMapping("delete")
    public ResponseEntity<ResponseDto> delete(@Valid @RequestBody DeleteRequestDto requestDto) {
        // Get account by token
        val token = requestDto.getToken();
        val account = getAccountByTokenStep.runStep(token);
        if (!account.getSuccess()) {
            responseStep.addMessage("Failed to delete clinic!");
            return responseStep.getStep(false);
        }
        val accountData = account.getData();
        // ...
        val accountId = accountData.getId();
        val clinicIds = requestDto.getIds();
        // Delete clinic account relations
        val clinicAccountRelations = clinicAccountService.findByAccountIdAndClinicIdIn(accountId, clinicIds);
        clinicAccountService.deleteAllInBatch(clinicAccountRelations);
        // Delete owner relations
        val ownerRelations = ownerService.findByAccountIdAndClinicIdIn(accountId, clinicIds);
        ownerService.deleteAllInBatch(ownerRelations);
        // Delete opening hour
        val openingHoursIds = clinicService.findAllById(clinicIds).stream().map(Clinic::getOpeningHoursId).toList();
        openingHoursService.deleteAllByIdInBatch(openingHoursIds);
        // Delete clinics
        clinicService.deleteAllByIdInBatch(clinicIds);
        responseStep.addMessage("Clinics were deleted successfully!");
        return responseStep.getStep(true);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        return handleValidationService.handle(ex);
    }
}
