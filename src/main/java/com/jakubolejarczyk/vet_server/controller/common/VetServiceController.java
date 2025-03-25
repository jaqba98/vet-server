package com.jakubolejarczyk.vet_server.controller.common;

import com.jakubolejarczyk.vet_server.dto.base.TokenRequestDto;
import com.jakubolejarczyk.vet_server.dto.request.controller.VetServiceRequestDto;
import com.jakubolejarczyk.vet_server.dto.request.crud.DeleteRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDataDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import com.jakubolejarczyk.vet_server.model.dependent.VetService;
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
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class VetServiceController {
    private final ObjectFactory<ResponseStep> responseStep;
    private final GetAccountByTokenStep getAccountByTokenStep;
    private final ObjectFactory<HandleValidationService> handleValidationService;
    private final ClinicAccountService clinicAccountService;
    private final OwnerService ownerService;
    private final OpeningHoursService openingHoursService;
    private final ClinicService clinicService;
    private final AccountClinicsStep accountClinicsStep;

    @PostMapping("vet-service-create")
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody VetServiceRequestDto requestDto) {
        // Clean response
        responseStep.getObject().getRidOfMessages();
//        // Get account by token
//        val token = requestDto.getToken();
//        val account = getAccountByTokenStep.runStep(token);
//        if (!account.getSuccess()) {
//            responseStep.getObject().addMessage("Failed to create clinic!");
//            return responseStep.getObject().getStep(false);
//        }
//        val accountData = account.getData();
//        val accountId = accountData.getId();
//        // Create an opening hours
//        OpeningHours openingHours = OpeningHours.builder().build();
//        val newOpeningHours = openingHoursService.create(openingHours);
//        // Create a clinic
//        val clinic = Clinic.builder()
//                .name(requestDto.getName())
//                .street(requestDto.getStreet())
//                .buildingNumber(requestDto.getBuildingNumber())
//                .apartmentNumber(requestDto.getApartmentNumber())
//                .postalCode(requestDto.getPostalCode())
//                .city(requestDto.getCity())
//                .province(requestDto.getProvince())
//                .country(requestDto.getCountry())
//                .email(requestDto.getEmail())
//                .phoneNumber(requestDto.getPhoneNumber())
//                .openingHoursId(newOpeningHours.getId())
//                .build();
//        val newClinic = clinicService.create(clinic);
//        // Create an owner
//        Owner owner = Owner.builder()
//                .accountId(accountId)
//                .clinicId(newClinic.getId())
//                .build();
//        ownerService.create(owner);
//        // Create a clinic_account
//        ClinicAccount clinicAccount = ClinicAccount.builder()
//                .accountId(accountId)
//                .clinicId(newClinic.getId())
//                .build();
//        clinicAccountService.create(clinicAccount);
        // Response
        responseStep.getObject().addMessage("The vet service has been established successfully!");
        return responseStep.getObject().getStep(true);
    }

    @PostMapping("vet-service-read")
    public ResponseEntity<ResponseDataDto<ArrayList<VetService>>> read(@Valid @RequestBody TokenRequestDto requestDto) {
        // Clean response
        responseStep.getObject().getRidOfMessages();
//        // Get account by token
//        val token = requestDto.getToken();
//        val account = getAccountByTokenStep.runStep(token);
//        if (!account.getSuccess()) {
//            responseStep.getObject().addMessage("Failed to read clinics!");
//            return responseStep.getObject().getStep(false, new ArrayList<>());
//        }
//        val accountData = account.getData();
//        // Find all clinics owned by the account
//        val clinics = accountClinicsStep.runStep(accountData);
        // Response
        responseStep.getObject().addMessage("The vet services have been read successfully!");
        return responseStep.getObject().getStep(true, new ArrayList<>());
    }

    @PostMapping("vet-service-update")
    public ResponseEntity<ResponseDataDto<VetService>> update(@Valid @RequestBody VetServiceRequestDto requestDto) throws Exception {
        // Clean response
        responseStep.getObject().getRidOfMessages();
//        // Get account by token
//        val token = requestDto.getToken();
//        val account = getAccountByTokenStep.runStep(token);
//        if (!account.getSuccess()) {
//            responseStep.getObject().addMessage("Failed to update clinic!");
//            return responseStep.getObject().getStep(false, Clinic.builder().build());
//        }
//        val accountData = account.getData();
//        val accountId = accountData.getId();
//        val clinicId = requestDto.getId();
//        // Check permission to clinic
//        val ownerRelation = ownerService.findByAccountIdAndClinicId(accountId, clinicId);
//        System.out.println(accountId + " / " +  clinicId + " / " + ownerRelation);
//        if (ownerRelation.isEmpty()) {
//            responseStep.getObject().addMessage("You do not have permission to update!");
//            return responseStep.getObject().getStep(false, Clinic.builder().build());
//        }
//        // Get the clinic by id
//        val clinic = clinicService.findById(clinicId);
//        if (clinic.isEmpty()) {
//            responseStep.getObject().addMessage("The clinic does not exist!");
//            return responseStep.getObject().getStep(false, Clinic.builder().build());
//        }
//        // Merge new clinic with existed clinic
//        val clinicToUpdate = clinic.get();
//        clinicToUpdate.setName(requestDto.getName());
//        clinicToUpdate.setStreet(requestDto.getStreet());
//        clinicToUpdate.setBuildingNumber(requestDto.getBuildingNumber());
//        clinicToUpdate.setApartmentNumber(requestDto.getApartmentNumber());
//        clinicToUpdate.setPostalCode(requestDto.getPostalCode());
//        clinicToUpdate.setCity(requestDto.getCity());
//        clinicToUpdate.setProvince(requestDto.getProvince());
//        clinicToUpdate.setCountry(requestDto.getCountry());
//        clinicToUpdate.setEmail(requestDto.getEmail());
//        clinicToUpdate.setPhoneNumber(requestDto.getPhoneNumber());
//        clinicService.update(clinicToUpdate);
        // Response
        responseStep.getObject().addMessage("The vet service has been updated successfully!");
        return responseStep.getObject().getStep(true, VetService.builder().build());
    }

    @PostMapping("vet-service-delete")
    public ResponseEntity<ResponseDto> delete(@Valid @RequestBody DeleteRequestDto requestDto) {
        // Clean response
        responseStep.getObject().getRidOfMessages();
//        // Get account by token
//        val token = requestDto.getToken();
//        val account = getAccountByTokenStep.runStep(token);
//        if (!account.getSuccess()) {
//            responseStep.getObject().addMessage("Failed to delete clinic!");
//            return responseStep.getObject().getStep(false);
//        }
//        val accountData = account.getData();
//        val accountId = accountData.getId();
//        val clinicIds = requestDto.getIds();
//        val openingHoursIds = clinicService.findAllById(clinicIds).stream().map(Clinic::getOpeningHoursId).toList();
//        // Delete clinic account relations
//        val clinicAccountRelations = clinicAccountService.findByAccountIdAndClinicIdIn(accountId, clinicIds);
//        clinicAccountService.deleteAllInBatch(clinicAccountRelations);
//        // Delete owner relations
//        val ownerRelations = ownerService.findByAccountIdAndClinicIdIn(accountId, clinicIds);
//        ownerService.deleteAllInBatch(ownerRelations);
//        // Delete clinics
//        clinicService.deleteAllByIdInBatch(clinicIds);
//        // Delete opening hours
//        openingHoursService.deleteAllByIdInBatch(openingHoursIds);
        // Response
        responseStep.getObject().addMessage("Vet services were deleted successfully!");
        return responseStep.getObject().getStep(true);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        return handleValidationService.getObject().handle(ex);
    }
}
