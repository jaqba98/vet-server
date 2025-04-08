package com.jakubolejarczyk.vet_server.step.delete;

import com.jakubolejarczyk.vet_server.model.dependent.Invoice;
import com.jakubolejarczyk.vet_server.model.dependent.MedicalRecord;
import com.jakubolejarczyk.vet_server.service.dependent.AppointmentService;
import com.jakubolejarczyk.vet_server.service.dependent.InvoiceService;
import com.jakubolejarczyk.vet_server.service.dependent.MedicalRecordService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteAppointmentStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final AppointmentService appointmentService;
    private final InvoiceService invoiceService;
    private final MedicalRecordService medicalRecordService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("appointmentsIds")) throw new Error("The appointmentsIds is required!");
        val appointmentsIds = stepStore.getItemAsArray("appointmentsIds", Long.class);
        val invoiceIds = invoiceService.findAllByAppointmentIdIn(appointmentsIds).stream()
            .map(Invoice::getAppointmentId)
            .toList();
        invoiceService.deleteAllById(invoiceIds);
        val medicalRecordIds = medicalRecordService.findAllByAppointmentIdIn(appointmentsIds).stream()
            .map(MedicalRecord::getAppointmentId)
            .toList();
        medicalRecordService.deleteAllById(medicalRecordIds);
        appointmentService.deleteAllById(appointmentsIds);
    }
}
