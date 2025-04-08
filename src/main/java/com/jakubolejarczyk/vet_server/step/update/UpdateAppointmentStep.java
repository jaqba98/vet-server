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
public class UpdateAppointmentStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final AppointmentService appointmentService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("appointmentRequest")) throw new Error("The appointmentRequest is required!");
        val appointmentRequest = stepStore.getItem("appointmentRequest", Appointment.class);
        val appointmentId = appointmentRequest.getId();
        val currentAppointment = appointmentService.findById(appointmentId);
        if (currentAppointment.isEmpty()) {
            stepStore.setSuccess(false);
            stepStore.addMessage("Failed to update appointment!");
            return;
        }
        currentAppointment.get().setFullName(appointmentRequest.getFullName());
        currentAppointment.get().setDateAndHour(appointmentRequest.getDateAndHour());
        currentAppointment.get().setType(appointmentRequest.getType());
        currentAppointment.get().setStatus(appointmentRequest.getStatus());
        currentAppointment.get().setReason(appointmentRequest.getReason());
        currentAppointment.get().setClinicId(appointmentRequest.getClinicId());
        currentAppointment.get().setVetId(appointmentRequest.getVetId());
        currentAppointment.get().setPetId(appointmentRequest.getPetId());
        val appointmentData = appointmentService.save(currentAppointment.get());
        stepStore.setItem("appointmentData", appointmentData);
    }
}
