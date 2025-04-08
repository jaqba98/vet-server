package com.jakubolejarczyk.vet_server.controller.dependent.invoice;

import com.jakubolejarczyk.vet_server.dto.data.independent.InvoiceData;
import com.jakubolejarczyk.vet_server.dto.metadata.independent.InvoiceMetadata;
import com.jakubolejarczyk.vet_server.dto.request.dependent.InvoiceRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.dependent.Invoice;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.get.account.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.step.response.dependent.invoice.InvoiceUpdateResponseStep;
import com.jakubolejarczyk.vet_server.step.update.UpdateInvoiceStep;
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
public class InvoiceUpdateController extends StepRunnerController<InvoiceData, InvoiceMetadata> {
    private final GetAccountByTokenStep<InvoiceData, InvoiceMetadata> getAccountByTokenStep;
    private final UpdateInvoiceStep<InvoiceData, InvoiceMetadata> updateInvoiceStep;
    private final InvoiceUpdateResponseStep invoiceUpdateResponseStep;

    public InvoiceUpdateController(
        ObjectFactory<StepStore<InvoiceData, InvoiceMetadata>> stepStoreObjectFactory,
        ObjectFactory<HandleValidationService<InvoiceData, InvoiceMetadata>> handleValidationServiceObjectFactory,
        GetAccountByTokenStep<InvoiceData, InvoiceMetadata> getAccountByTokenStep,
        UpdateInvoiceStep<InvoiceData, InvoiceMetadata> updateInvoiceStep,
        InvoiceUpdateResponseStep invoiceUpdateResponseStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.getAccountByTokenStep = getAccountByTokenStep;
        this.updateInvoiceStep = updateInvoiceStep;
        this.invoiceUpdateResponseStep = invoiceUpdateResponseStep;
    }

    @PostMapping("invoice-update")
    public ResponseEntity<Response<InvoiceData, InvoiceMetadata>> invoiceUpdate(
        @Valid @RequestBody InvoiceRequest request
    ) {
        val steps = new ArrayList<StepRunnerModel<InvoiceData, InvoiceMetadata>>();
        steps.addLast(getAccountByTokenStep);
        steps.addLast(updateInvoiceStep);
        initController();
        getStepStore().setItem("token", request.getToken());
        val invoiceRequest = Invoice.builder()
            .id(request.getId())
            .invoiceDate(request.getInvoiceDate())
            .dueDate(request.getDueDate())
            .totalAmount(request.getTotalAmount())
            .amountPaid(request.getAmountPaid())
            .outstandingAmount(request.getOutstandingAmount())
            .paymentStatus(request.getPaymentStatus())
            .paymentMethod(request.getPaymentMethod())
            .notes(request.getNotes())
            .appointmentId(request.getAppointmentId())
            .build();
        getStepStore().setItem("invoiceRequest", invoiceRequest);
        return runController(steps, invoiceUpdateResponseStep);
    }
}
