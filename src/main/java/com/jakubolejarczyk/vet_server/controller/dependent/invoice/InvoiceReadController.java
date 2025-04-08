package com.jakubolejarczyk.vet_server.controller.dependent.invoice;

import com.jakubolejarczyk.vet_server.dto.data.independent.InvoiceData;
import com.jakubolejarczyk.vet_server.dto.metadata.independent.InvoiceMetadata;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.get.appointment.GetAppointmentsByClinicsStep;
import com.jakubolejarczyk.vet_server.step.get.clinic.GetClinicsByEmploymentsStep;
import com.jakubolejarczyk.vet_server.step.get.employment.GetEmploymentsByAccountStep;
import com.jakubolejarczyk.vet_server.step.get.invoice.GetInvoicesByAppointmentsStep;
import com.jakubolejarczyk.vet_server.step.metadata.InvoiceMetadataStep;
import com.jakubolejarczyk.vet_server.step.response.dependent.invoice.InvoiceResponseStep;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
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
public class InvoiceReadController extends StepRunnerController<InvoiceData, InvoiceMetadata> {
    private final GetAccountByTokenStep<InvoiceData, InvoiceMetadata> getAccountByTokenStep;
    private final GetEmploymentsByAccountStep<InvoiceData, InvoiceMetadata> getEmploymentsByAccountStep;
    private final GetClinicsByEmploymentsStep<InvoiceData, InvoiceMetadata> getClinicsByEmploymentsStep;
    private final GetAppointmentsByClinicsStep<InvoiceData, InvoiceMetadata> getAppointmentsByClinicsStep;
    private final GetInvoicesByAppointmentsStep<InvoiceData, InvoiceMetadata> getInvoicesByAppointmentsStep;
    private final InvoiceMetadataStep invoiceMetadataStep;
    private final InvoiceResponseStep invoiceResponseStep;

    public InvoiceReadController(
        ObjectFactory<StepStore<InvoiceData, InvoiceMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<InvoiceData, InvoiceMetadata> getAccountByTokenStep,
        GetEmploymentsByAccountStep<InvoiceData, InvoiceMetadata> getEmploymentsByAccountStep,
        GetClinicsByEmploymentsStep<InvoiceData, InvoiceMetadata> getClinicsByEmploymentsStep,
        GetAppointmentsByClinicsStep<InvoiceData, InvoiceMetadata> getAppointmentsByClinicsStep,
        GetInvoicesByAppointmentsStep<InvoiceData, InvoiceMetadata> getInvoicesByAppointmentsStep,
        InvoiceMetadataStep invoiceMetadataStep,
        InvoiceResponseStep invoiceResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.getEmploymentsByAccountStep = getEmploymentsByAccountStep;
        this.getClinicsByEmploymentsStep = getClinicsByEmploymentsStep;
        this.getAppointmentsByClinicsStep = getAppointmentsByClinicsStep;
        this.getInvoicesByAppointmentsStep = getInvoicesByAppointmentsStep;
        this.invoiceMetadataStep = invoiceMetadataStep;
        this.invoiceResponseStep = invoiceResponseStep;
    }

    @PostMapping("invoice-read")
    public ResponseEntity<Response<InvoiceData, InvoiceMetadata>> invoiceRead(
        @Valid @RequestBody TokenRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<InvoiceData, InvoiceMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(getEmploymentsByAccountStep);
        steps.addLast(getClinicsByEmploymentsStep);
        steps.addLast(getAppointmentsByClinicsStep);
        steps.addLast(getInvoicesByAppointmentsStep);
        steps.addLast(invoiceMetadataStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        return runController(steps, invoiceResponseStep);
    }
}
