//package com.jakubolejarczyk.vet_server.controller.common;
//
//import com.jakubolejarczyk.vet_server.dto.base.TokenRequestDto;
//import com.jakubolejarczyk.vet_server.dto.request.controller.ClinicCreateRequestDto;
//import com.jakubolejarczyk.vet_server.dto.request.controller.ClinicUpdateRequestDto;
//import com.jakubolejarczyk.vet_server.dto.request.crud.DeleteRequestDto;
//import com.jakubolejarczyk.vet_server.dto.response.ResponseDataDto;
//import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
//import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
//import com.jakubolejarczyk.vet_server.model.dependent.Employment;
//import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
//import com.jakubolejarczyk.vet_server.service.step.create.CreateClinicStep;
//import com.jakubolejarczyk.vet_server.service.step.create.CreateEmploymentStep;
//import com.jakubolejarczyk.vet_server.service.step.create.CreateOpeningHoursStep;
//import com.jakubolejarczyk.vet_server.service.step.get.GetAccountByTokenStep;
//import com.jakubolejarczyk.vet_server.service.step.get.GetClinicByIdStep;
//import com.jakubolejarczyk.vet_server.service.step.get.GetClinicsByIdsStep;
//import com.jakubolejarczyk.vet_server.service.step_old.*;
//import jakarta.validation.Valid;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.beans.factory.ObjectFactory;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api/v1")
//@AllArgsConstructor
//public class ClinicController {
//    private final ObjectFactory<ResponseStep> responseStep;
//    private final GetAccountByTokenStep getAccountByTokenStep;
//    private final CreateOpeningHoursStep createOpeningHoursStep;
//    private final CreateClinicStep createClinicStep;
//    private final ObjectFactory<HandleValidationService> handleValidationService;
//    private final CreateEmploymentStep createEmploymentStep;
//    private final GetClinicIdsForAccountStep getClinicIdsForAccountStep;
//    private final GetClinicsByIdsStep getClinicsByIdsStep;
//    private final GetClinicIdsForOwnerAccountStep getClinicIdsForOwnerAccountStep;
//    private final CheckPermissionStep checkPermissionStep;
//    private final UpdateClinicStep updateClinicStep;
//    private final GetEmploymentByAccountIdAndClinicIdsInAndIsOwnerStep getEmploymentByAccountIdAndClinicIdsInAndIsOwnerStep;
//    private final UpdateEmploymentIdsIsArchivedStep updateEmploymentIdsIsArchivedStep;
//    private final UpdateClinicIdsIsArchivedStep updateClinicIdsIsArchivedStep;
//    private final GetOpeningHoursIdsForClinicIdsStep getOpeningHoursIdsForClinicIdsStep;
//    private final UpdateOpeningHoursIdsIsArchivedStep updateOpeningHoursIdsIsArchivedStep;
//    private final GetClinicByIdStep getClinicByIdStep;
//
//    @PostMapping("clinic-delete")
//    public ResponseEntity<ResponseDto> delete(@Valid @RequestBody DeleteRequestDto requestDto) {
//        // Init
//        val responseStep = this.responseStep.getObject();
//        responseStep.getRidOfMessages();
//        val clinicIds = requestDto.getIds();
//        // Get Account By Token Step
//        val accountResponse = getAccountByTokenStep.runStep(responseStep, requestDto.getToken());
//        if (accountResponse.getError()) return responseStep.getStep(false);
//        val account = accountResponse.getData();
//        // Get Employment By AccountId And ClinicIdsIn And IsOwner Step
//        var accountId = account.getId();
//        val employmentResponse = getEmploymentByAccountIdAndClinicIdsInAndIsOwnerStep.runStep(responseStep, accountId, clinicIds);
//        if (employmentResponse.getError()) return responseStep.getStep(false);
//        val employment = employmentResponse.getData();
//        // Update Employment Ids Is Archived Step
//        val employmentIds = employment.stream()
//            .map(Employment::getId)
//            .collect(Collectors.toCollection(ArrayList::new));
//        updateEmploymentIdsIsArchivedStep.runStep(responseStep, employmentIds, true);
//        // Update Clinic Ids Is Archived Step
//        val gotClinicIds = employment.stream()
//                .map(Employment::getClinicId)
//                .collect(Collectors.toCollection(ArrayList::new));
//        updateClinicIdsIsArchivedStep.runStep(responseStep, gotClinicIds, true);
//        // Get Opening Hours Ids For Clinic Ids Step
//        val openingHoursIdsResponse = getOpeningHoursIdsForClinicIdsStep.runStep(responseStep, clinicIds);
//        if (openingHoursIdsResponse.getError()) return responseStep.getStep(false);
//        val openingHoursIds = openingHoursIdsResponse.getData();
//        // Update Opening Hours Ids Is Archived Step
//        val openingHoursResponse = updateOpeningHoursIdsIsArchivedStep.runStep(responseStep, openingHoursIds, true);
//        if (openingHoursResponse.getError()) return responseStep.getStep(false);
//        // Return response
//        responseStep.addMessage("The clinic have been deleted successfully!");
//        return responseStep.getStep(true);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ResponseDto> handleValidation(MethodArgumentNotValidException ex) {
//        return handleValidationService.getObject().handle(ex);
//    }
//}
