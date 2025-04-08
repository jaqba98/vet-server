package com.jakubolejarczyk.vet_server.step.response.dependent.invoice;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import com.jakubolejarczyk.vet_server.dto.data.independent.InvoiceData;
import com.jakubolejarczyk.vet_server.dto.metadata.independent.InvoiceMetadata;
import com.jakubolejarczyk.vet_server.model.dependent.Invoice;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InvoiceResponseStep implements StepRunnerModel<InvoiceData, InvoiceMetadata> {
    @Override
    public void runStep(StepStore<InvoiceData, InvoiceMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            if (stepStore.hasNotItem("invoicesData")) throw new Error("The invoicesData is required!");
            if (stepStore.hasNotItem("appointmentId")) throw new Error("The appointmentId is required!");
            val invoicesData = stepStore.getItemAsArray("invoicesData", Invoice.class);
            val appointmentId = stepStore.getItem("appointmentId", BaseMetadata.class);
            val data = new InvoiceData(invoicesData);
            val metadata = new InvoiceMetadata(appointmentId);
            stepStore.addMessage("Invoices were read correctly!");
            stepStore.setData(data);
            stepStore.setMetadata(metadata);
        }
    }
}
