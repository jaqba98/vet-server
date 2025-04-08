package com.jakubolejarczyk.vet_server.step.metadata;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import com.jakubolejarczyk.vet_server.dto.data.independent.InvoiceData;
import com.jakubolejarczyk.vet_server.dto.metadata.independent.InvoiceMetadata;
import com.jakubolejarczyk.vet_server.model.dependent.Appointment;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InvoiceMetadataStep implements StepRunnerModel<InvoiceData, InvoiceMetadata> {
    @Override
    public void runStep(StepStore<InvoiceData, InvoiceMetadata> stepStore) {
        if (stepStore.hasNotItem("appointmentsData")) throw new Error("The appointmentsData is required!");
        val appointmentId = new BaseMetadata();
        stepStore.getItemAsArray("appointmentsData", Appointment.class).forEach(appointment -> appointmentId.addValue(appointment.getId(), appointment.getFullName()));
        stepStore.setItem("appointmentId", appointmentId);
    }
}
