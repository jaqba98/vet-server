package com.jakubolejarczyk.vet_server.step.update;

import com.jakubolejarczyk.vet_server.model.independent.Invoice;
import com.jakubolejarczyk.vet_server.service.independent.InvoiceService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateInvoiceStepRunner implements StepRunnerModel {
    private final InvoiceService invoiceService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("requestInvoice")) throw new Error("The requestInvoice is required!");
        val requestInvoice = stepStore.getItem("requestInvoice", Invoice.class);
        val invoiceId = requestInvoice.getId();
        val currentInvoice = invoiceService.findById(invoiceId);
        if (currentInvoice.isPresent()) {
            val newInvoice = Invoice.builder()
                    .id(currentInvoice.get().getId())
                    .invoiceDate(requestInvoice.getInvoiceDate())
                    .dueDate(requestInvoice.getDueDate())
                    .totalAmount(requestInvoice.getTotalAmount())
                    .amountPaid(requestInvoice.getAmountPaid())
                    .outstandingAmount(requestInvoice.getOutstandingAmount())
                    .paymentStatus(requestInvoice.getPaymentStatus())
                    .paymentMethod(requestInvoice.getPaymentMethod())
                    .notes(requestInvoice.getNotes())
                    .build();
            val invoice = invoiceService.save(newInvoice);
            stepStore.setItem("invoice", invoice);
            return;
        }
        stepStore.setSuccess(false);
        stepStore.addMessage("Failed to update an invoice.");
    }
}
