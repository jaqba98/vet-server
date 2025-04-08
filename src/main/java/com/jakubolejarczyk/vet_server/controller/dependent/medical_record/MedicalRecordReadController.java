//package com.jakubolejarczyk.vet_server.controller.old.medical_record;
//
//import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
//import com.jakubolejarczyk.vet_server.dto.response.Response;
//import com.jakubolejarczyk.vet_server.security.HandleValidationService;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
//import com.jakubolejarczyk.vet_server.step.get.*;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
//import com.jakubolejarczyk.vet_server.store.StepStore;
//import jakarta.validation.Valid;
//import lombok.val;
//import org.springframework.beans.factory.ObjectFactory;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//
//@RestController
//@RequestMapping("/api/v1")
//public class MedicalRecordReadController extends StepRunnerController {
//    private final GetAccountByTokenStepRunner getAccountByTokenStep;
//    private final GetClinicIdsForAccountStepRunner getClinicIdsForAccountStep;
//    private final GetAppointmentsByClinicIdsStepRunner getAppointmentsByClinicIdsStep;
//    private final GetMedicalRecordsByAppointmentsStepRunner getMedicalRecordsByAppointmentsStep;
//
//    public MedicalRecordReadController(
//            ObjectFactory<StepStore> stepStoreObjectFactory,
//            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
//            GetAccountByTokenStepRunner getAccountByTokenStep,
//            GetClinicIdsForAccountStepRunner getClinicIdsForAccountStep,
//            GetAppointmentsByClinicIdsStepRunner getAppointmentsByClinicIdsStep,
//            GetMedicalRecordsByAppointmentsStepRunner getMedicalRecordsByAppointmentsStep
//    ) {
//        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
//        this.getAccountByTokenStep = getAccountByTokenStep;
//        this.getClinicIdsForAccountStep = getClinicIdsForAccountStep;
//        this.getAppointmentsByClinicIdsStep = getAppointmentsByClinicIdsStep;
//        this.getMedicalRecordsByAppointmentsStep = getMedicalRecordsByAppointmentsStep;
//    }
//
//    @PostMapping("medical-record-read")
//    public ResponseEntity<Response<?, ?>> medicalRecordRead(@Valid @RequestBody TokenRequest request) {
//        val steps = new ArrayList<StepRunnerModel>();
//        steps.addLast(getAccountByTokenStep);
//        steps.addLast(getClinicIdsForAccountStep);
//        steps.addLast(getAppointmentsByClinicIdsStep);
//        steps.addLast(getMedicalRecordsByAppointmentsStep);
//        String[] dataKeys = {"medicalRecords"};
//        String[] metadataKeys = {};
//        initController(dataKeys, metadataKeys);
//        getStepStore().setItem("token", request.getToken());
//        return runController(steps);
//    }
//}
