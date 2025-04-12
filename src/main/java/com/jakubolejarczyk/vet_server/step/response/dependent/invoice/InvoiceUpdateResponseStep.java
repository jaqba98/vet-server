package com.jakubolejarczyk.vet_server.step.response.dependent.invoice;

import com.jakubolejarczyk.vet_server.dto.data.dependent.InvoiceData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.InvoiceMetadata;
import com.jakubolejarczyk.vet_server.model.dependent.Invoice;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class InvoiceUpdateResponseStep implements StepRunnerModel<InvoiceData, InvoiceMetadata> {
    @Override
    public void runStep(StepStore<InvoiceData, InvoiceMetadata> stepStore) {
        if (stepStore.getSuccess()) {
            if (stepStore.hasNotItem("invoiceData")) throw new Error("The invoiceData is required!");
            val invoiceData = stepStore.getItem("invoiceData", Invoice.class);
            val data = new InvoiceData(Collections.singletonList(invoiceData));
            stepStore.addMessage("Invoice was updated correctly!");
            stepStore.setData(data);
        } else {
            if (stepStore.hasNotItem("invoiceRequest")) throw new Error("The invoiceRequest is required!");
            val invoiceRequest = stepStore.getItem("invoiceRequest", Invoice.class);
            val data = new InvoiceData(Collections.singletonList(invoiceRequest));
            stepStore.setData(data);
        }
    }
}
