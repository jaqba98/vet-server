package com.jakubolejarczyk.vet_server.controller.invoice;

import com.jakubolejarczyk.vet_server.dto.request.independent.InvoiceRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.model.independent.Invoice;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
import com.jakubolejarczyk.vet_server.step.check.CheckAccountPermissionToClinicStepRunner;
import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStepRunner;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.step.success.SuccessUpdateInvoiceStepRunner;
import com.jakubolejarczyk.vet_server.step.update.UpdateInvoiceStepRunner;
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
public class InvoiceUpdateController extends StepRunnerController {
    private final UpdateInvoiceStepRunner updateInvoiceStep;
    private final SuccessUpdateInvoiceStepRunner successUpdateInvoiceStep;

    public InvoiceUpdateController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
            GetAccountByTokenStepRunner getAccountByTokenStep,
            CheckAccountPermissionToClinicStepRunner checkAccountPermissionToClinicStep,
            UpdateInvoiceStepRunner updateInvoiceStep,
            SuccessUpdateInvoiceStepRunner successUpdateInvoiceStep
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
        this.updateInvoiceStep = updateInvoiceStep;
        this.successUpdateInvoiceStep = successUpdateInvoiceStep;
    }

    @PostMapping("invoice-update")
    public ResponseEntity<Response<?, ?>> invoiceUpdate(@Valid @RequestBody InvoiceRequest request) {
        val steps = new ArrayList<StepRunnerModel>();
        steps.addLast(updateInvoiceStep);
        steps.addLast(successUpdateInvoiceStep);
        String[] dataKeys = {"invoice"};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", request.getToken());
        val requestInvoice = Invoice.builder()
                .id(request.getId())
                .invoiceDate(request.getInvoiceDate())
                .dueDate(request.getDueDate())
                .totalAmount(request.getTotalAmount())
                .amountPaid(request.getAmountPaid())
                .outstandingAmount(request.getOutstandingAmount())
                .paymentStatus(request.getPaymentStatus())
                .paymentMethod(request.getPaymentMethod())
                .notes(request.getNotes())
                .build();
        getStepStore().setItem("requestInvoice", requestInvoice);
        return runController(steps);
    }
}
