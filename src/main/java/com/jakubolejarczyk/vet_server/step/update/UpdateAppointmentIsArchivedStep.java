package com.jakubolejarczyk.vet_server.step.update;

import com.jakubolejarczyk.vet_server.model.dependent.Appointment;
import com.jakubolejarczyk.vet_server.service.dependent.AppointmentService;
import com.jakubolejarczyk.vet_server.service.independent.InvoiceService;
import com.jakubolejarczyk.vet_server.service.independent.MedicalRecordService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateAppointmentIsArchivedStep implements StepModel {
    private final AppointmentService appointmentService;
    private final InvoiceService invoiceService;
    private final MedicalRecordService medicalRecordService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("appointmentsIds")) throw new Error("The appointmentsIds is required!");
        val appointmentsIds = stepStore.getItemAsArray("appointmentsIds", Long.class);
        val appointments = appointmentService.findAllById(appointmentsIds);
        val invoiceIds = appointments.stream().map(Appointment::getInvoiceId).toList();
        val medicalResourceIds = appointments.stream().map(Appointment::getMedicalRecordId).toList();
        appointmentService.deleteAllById(appointmentsIds);
        invoiceService.deleteAllById(invoiceIds);
        medicalRecordService.deleteAllById(medicalResourceIds);
    }
}
