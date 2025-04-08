package com.jakubolejarczyk.vet_server.step.create;

import com.jakubolejarczyk.vet_server.model.dependent.Appointment;
import com.jakubolejarczyk.vet_server.model.dependent.Invoice;
import com.jakubolejarczyk.vet_server.service.dependent.InvoiceService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateInvoiceStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final InvoiceService invoiceService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("appointmentData")) throw new Error("The appointmentData is required!");
        val appointmentData = stepStore.getItem("appointmentData", Appointment.class);
        val invoice = Invoice.builder()
            .appointmentId(appointmentData.getId())
            .build();
        val invoiceData = invoiceService.save(invoice);
        stepStore.setItem("invoiceData", invoiceData);
    }
}
