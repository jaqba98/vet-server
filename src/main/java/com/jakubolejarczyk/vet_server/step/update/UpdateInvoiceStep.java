package com.jakubolejarczyk.vet_server.step.update;

import com.jakubolejarczyk.vet_server.model.dependent.Invoice;
import com.jakubolejarczyk.vet_server.service.dependent.InvoiceService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateInvoiceStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final InvoiceService invoiceService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("invoiceRequest")) throw new Error("The invoiceRequest is required!");
        val invoiceRequest = stepStore.getItem("invoiceRequest", Invoice.class);
        val invoiceId = invoiceRequest.getId();
        val currentInvoice = invoiceService.findById(invoiceId);
        if (currentInvoice.isEmpty()) {
            stepStore.setSuccess(false);
            stepStore.addMessage("Failed to update invoice!");
            return;
        }
        currentInvoice.get().setInvoiceDate(invoiceRequest.getInvoiceDate());
        currentInvoice.get().setDueDate(invoiceRequest.getDueDate());
        currentInvoice.get().setTotalAmount(invoiceRequest.getTotalAmount());
        currentInvoice.get().setAmountPaid(invoiceRequest.getAmountPaid());
        currentInvoice.get().setOutstandingAmount(invoiceRequest.getOutstandingAmount());
        currentInvoice.get().setPaymentStatus(invoiceRequest.getPaymentStatus());
        currentInvoice.get().setPaymentMethod(invoiceRequest.getPaymentMethod());
        currentInvoice.get().setNotes(invoiceRequest.getNotes());
        currentInvoice.get().setAppointmentId(invoiceRequest.getAppointmentId());
        val invoiceData = invoiceService.save(currentInvoice.get());
        stepStore.setItem("invoiceData", invoiceData);
    }
}
