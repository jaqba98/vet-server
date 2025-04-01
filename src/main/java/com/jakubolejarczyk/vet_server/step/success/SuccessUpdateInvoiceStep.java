package com.jakubolejarczyk.vet_server.step.success;

import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SuccessUpdateInvoiceStep implements StepModel {
    @Override
    public void runStep(StepStore stepStore) {
        stepStore.addMessage("The invoice has been updated successfully!");
    }
}
