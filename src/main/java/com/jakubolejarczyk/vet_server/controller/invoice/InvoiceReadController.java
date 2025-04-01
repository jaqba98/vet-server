package com.jakubolejarczyk.vet_server.controller.invoice;

import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.base.BaseController;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.get.GetAppointmentsByClinicIdsStep;
import com.jakubolejarczyk.vet_server.step.get.GetClinicIdsForAccountStep;
import com.jakubolejarczyk.vet_server.step.get.GetInvoicesByAppointmentsStep;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import jakarta.validation.Valid;
import lombok.val;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1")
public class InvoiceReadController extends BaseController {
    private final GetAccountByTokenStep getAccountByTokenStep;
    private final GetClinicIdsForAccountStep getClinicIdsForAccountStep;
    private final GetAppointmentsByClinicIdsStep getAppointmentsByClinicIdsStep;
    private final GetInvoicesByAppointmentsStep getInvoicesByAppointmentsStep;

    public InvoiceReadController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStep getAccountByTokenStep,
            GetClinicIdsForAccountStep getClinicIdsForAccountStep,
            GetAppointmentsByClinicIdsStep getAppointmentsByClinicIdsStep,
            GetInvoicesByAppointmentsStep getInvoicesByAppointmentsStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.getClinicIdsForAccountStep = getClinicIdsForAccountStep;
        this.getAppointmentsByClinicIdsStep = getAppointmentsByClinicIdsStep;
        this.getInvoicesByAppointmentsStep = getInvoicesByAppointmentsStep;
    }

    @PostMapping("invoice-read")
    public ResponseEntity<Response<?, ?>> invoiceRead(@Valid @RequestBody TokenRequest request) {
        val steps = new ArrayList<StepModel>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(getClinicIdsForAccountStep);
        steps.addLast(getAppointmentsByClinicIdsStep);
        steps.addLast(getInvoicesByAppointmentsStep);
        String[] dataKeys = {"invoices"};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", request.getToken());
        return runController(steps);
    }
}
