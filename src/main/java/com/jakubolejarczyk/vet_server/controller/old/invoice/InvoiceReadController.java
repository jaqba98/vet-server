//package com.jakubolejarczyk.vet_server.controller.old.invoice;
//
//import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
//import com.jakubolejarczyk.vet_server.dto.response.Response;
//import com.jakubolejarczyk.vet_server.security.HandleValidationService;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
//import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStepRunner;
//import com.jakubolejarczyk.vet_server.step.get.GetAppointmentsByClinicIdsStepRunner;
//import com.jakubolejarczyk.vet_server.step.get.GetClinicIdsForAccountStepRunner;
//import com.jakubolejarczyk.vet_server.step.get.GetInvoicesByAppointmentsStepRunner;
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
//public class InvoiceReadController extends StepRunnerController {
//    private final GetAccountByTokenStepRunner getAccountByTokenStep;
//    private final GetClinicIdsForAccountStepRunner getClinicIdsForAccountStep;
//    private final GetAppointmentsByClinicIdsStepRunner getAppointmentsByClinicIdsStep;
//    private final GetInvoicesByAppointmentsStepRunner getInvoicesByAppointmentsStep;
//
//    public InvoiceReadController(
//            ObjectFactory<StepStore> stepStoreObjectFactory,
//            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
//            GetAccountByTokenStepRunner getAccountByTokenStep,
//            GetClinicIdsForAccountStepRunner getClinicIdsForAccountStep,
//            GetAppointmentsByClinicIdsStepRunner getAppointmentsByClinicIdsStep,
//            GetInvoicesByAppointmentsStepRunner getInvoicesByAppointmentsStep
//    ) {
//        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
//        this.getAccountByTokenStep = getAccountByTokenStep;
//        this.getClinicIdsForAccountStep = getClinicIdsForAccountStep;
//        this.getAppointmentsByClinicIdsStep = getAppointmentsByClinicIdsStep;
//        this.getInvoicesByAppointmentsStep = getInvoicesByAppointmentsStep;
//    }
//
//    @PostMapping("invoice-read")
//    public ResponseEntity<Response<?, ?>> invoiceRead(@Valid @RequestBody TokenRequest request) {
//        val steps = new ArrayList<StepRunnerModel>();
//        steps.addLast(getAccountByTokenStep);
//        steps.addLast(getClinicIdsForAccountStep);
//        steps.addLast(getAppointmentsByClinicIdsStep);
//        steps.addLast(getInvoicesByAppointmentsStep);
//        String[] dataKeys = {"invoices"};
//        String[] metadataKeys = {};
//        initController(dataKeys, metadataKeys);
//        getStepStore().setItem("token", request.getToken());
//        return runController(steps);
//    }
//}
