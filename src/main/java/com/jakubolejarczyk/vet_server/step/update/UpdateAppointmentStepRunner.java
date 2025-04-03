package com.jakubolejarczyk.vet_server.step.update;

import com.jakubolejarczyk.vet_server.model.dependent.Appointment;
import com.jakubolejarczyk.vet_server.service.dependent.AppointmentService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateAppointmentStepRunner implements StepRunnerModel {
    private final AppointmentService appointmentService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("requestAppointment")) throw new Error("The requestAppointment is required!");
        val requestAppointment = stepStore.getItem("requestAppointment", Appointment.class);
        val appointmentId = requestAppointment.getId();
        val currentAppointment = appointmentService.findById(appointmentId);
        if (currentAppointment.isPresent()) {
            val newAppointment = Appointment.builder()
                    .id(currentAppointment.get().getId())
                    .dateAndHour(requestAppointment.getDateAndHour())
                    .type(requestAppointment.getType())
                    .status(requestAppointment.getStatus())
                    .reason(requestAppointment.getReason())
                    .notes(requestAppointment.getNotes())
                    .clinicId(requestAppointment.getClinicId())
                    .vetId(requestAppointment.getVetId())
                    .petId(requestAppointment.getPetId())
                    .invoiceId(requestAppointment.getInvoiceId())
                    .medicalRecordId(requestAppointment.getMedicalRecordId())
                    .build();
            val appointment = appointmentService.save(newAppointment);
            stepStore.setItem("appointment", appointment);
            return;
        }
        stepStore.setSuccess(false);
        stepStore.addMessage("Failed to update an appointment.");
    }
}
