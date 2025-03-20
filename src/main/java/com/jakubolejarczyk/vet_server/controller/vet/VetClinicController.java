package com.jakubolejarczyk.vet_server.controller.vet;

import com.jakubolejarczyk.vet_server.dto.request.controller.VetClinicRequestDto;
import com.jakubolejarczyk.vet_server.dto.request.crud.DeleteRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDataDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import com.jakubolejarczyk.vet_server.model.dependent.Client;
import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.model.relation.ClinicAccount;
import com.jakubolejarczyk.vet_server.service.crud.dependent.ClinicService;
import com.jakubolejarczyk.vet_server.service.crud.independent.OpeningHoursService;
import com.jakubolejarczyk.vet_server.service.crud.relation.ClinicAccountService;
import com.jakubolejarczyk.vet_server.service.crud.relation.OwnerService;
import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
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

    @PostMapping("create")
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody VetClinicRequestDto requestDto) {
        return responseStep.getStep(true);
    }

    @PostMapping("read")
    public ResponseEntity<ResponseDataDto<ArrayList<Clinic>>> read(@Valid @RequestBody VetClinicRequestDto requestDto) {
        val account = getAccountByTokenStep.runStep(requestDto.getToken());
        if (account.isEmpty()) {
            return responseStep.getStep(false, new ArrayList<>());
        }
        val accountByToken = account.get();
        val accountId = accountByToken.getId();
        val clinicIds = clinicAccountService.findByAccountId(accountId)
            .stream()
            .map(ClinicAccount::getClinicId)
            .collect(Collectors.toCollection(ArrayList::new));
        val matchedClinics = new ArrayList<>(clinicService.findAllById(clinicIds));
        responseStep.addMessage("The clinics have been read successfully!");
        return responseStep.getStep(true, matchedClinics);
    }

    @PostMapping("update")
    public ResponseEntity<ResponseDto> update(@Valid @RequestBody VetClinicRequestDto requestDto) {
        return responseStep.getStep(true);
    }

    @PostMapping("delete")
    public ResponseEntity<ResponseDto> delete(@Valid @RequestBody DeleteRequestDto requestDto) {
        val account = getAccountByTokenStep.runStep(requestDto.getToken());
        if (account.isEmpty()) {
            return responseStep.getStep(false);
        }
        val accountByToken = account.get();
        val accountId = accountByToken.getId();
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

//public class VetClinicController {
//    private final OpeningHoursService openingHoursService;
//    private final ClinicService clinicService;
//    private final TokenService tokenService;
//    private final AccountService accountService;
//    private final OwnerService ownerService;
//    private final ClinicAccountService clinicAccountService;
//
//    @PostMapping("create")
//    public ResponseEntity<ResponseDto<Clinic>> create(@Valid @RequestBody ClinicRequestDto requestDto) {
//        val messages = new ArrayList<String>();
//        // Create an OpeningHours object
//        val openingHours = openingHoursService.create();
//        // Create a Clinic object
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
//                .openingHoursId(openingHours.getId())
//                .build();
//        val newClinic = clinicService.create(clinic);
//        // Determine account id
//        val token = requestDto.getToken();
//        val email = tokenService.decode(token);
//        val account = accountService.findByEmail(email);
//        if (account.isEmpty()) {
//            messages.add("Account by given email does not exist!");
//            val responseDto = new ResponseDto<>(false, messages, clinic);
//            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
//        }
//        val accountId = account.get().getId();
//        // Create an Owner object
//        ownerService.create(accountId, newClinic.getId());
//        // Create a Clinic_Account object
//        clinicAccountService.create(accountId, newClinic.getId());
//        // Return the response dto
//        messages.add("The clinic has been established successfully!");
//        val responseDto = new ResponseDto<>(true, messages, clinic);
//        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
//    }
//
//    @PostMapping("update")
//    public ResponseEntity<ResponseDto<Clinic>> update(@Valid @RequestBody ClinicRequestDto requestDto) {
//        val messages = new ArrayList<String>();
//        val token = requestDto.getToken();
//        val email = tokenService.decode(token);
//        val account = accountService.findByEmail(email);
//        if (account.isEmpty()) {
//            messages.add("Account by given email does not exist!");
//            val responseDto = new ResponseDto<Clinic>(false, messages, Clinic.builder().build());
//            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
//        }
//        val accountId = account.get().getId();
//        val clinicId = requestDto.getId();
//        val relation = clinicAccountService.findByAccountIdAndClinicId(accountId, clinicId);
//        if (relation.isEmpty()) {
//            messages.add("You do not have permission to update!");
//            val responseDto = new ResponseDto<Clinic>(false, messages, Clinic.builder().build());
//            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
//        }
//        Optional<Clinic> clinic = clinicService.findById(clinicId);
//        if (clinic.isEmpty()) {
//            messages.add("The clinic does not exist!");
//            val responseDto = new ResponseDto<Clinic>(false, messages, Clinic.builder().build());
//            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
//        }
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
//        messages.add("The clinic has been updated successfully!");
//        val responseDto = new ResponseDto<>(true, messages, clinicToUpdate);
//        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
//    }
//}
