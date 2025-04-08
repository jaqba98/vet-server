package com.jakubolejarczyk.vet_server.step.create;

import com.jakubolejarczyk.vet_server.model.dependent.Appointment;
import com.jakubolejarczyk.vet_server.service.dependent.AppointmentService;
import com.jakubolejarczyk.vet_server.service.dependent.PetService;
import com.jakubolejarczyk.vet_server.service.dependent.VetService;
import com.jakubolejarczyk.vet_server.service.independent.ClinicService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateAppointmentStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final AppointmentService appointmentService;
    private final ClinicService clinicService;
    private final VetService vetService;
    private final PetService petService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("appointmentRequest")) throw new Error("The appointmentRequest is required!");
        val appointmentRequest = stepStore.getItem("appointmentRequest", Appointment.class);
        val clinic = clinicService.findById(appointmentRequest.getClinicId());
        if (clinic.isEmpty()) {
            stepStore.setSuccess(false);
            stepStore.addMessage("The clinic does not exist!");
            return;
        }
        val vet = vetService.findById(appointmentRequest.getVetId());
        if (vet.isEmpty()) {
            stepStore.setSuccess(false);
            stepStore.addMessage("The vet does not exist!");
            return;
        }
        val pet = petService.findById(appointmentRequest.getPetId());
        if (pet.isEmpty()) {
            stepStore.setSuccess(false);
            stepStore.addMessage("The pet does not exist!");
            return;
        }
        val appointment = Appointment.builder()
            .fullName(appointmentRequest.getFullName())
            .dateAndHour(appointmentRequest.getDateAndHour())
            .type(appointmentRequest.getType())
            .status(appointmentRequest.getStatus())
            .reason(appointmentRequest.getReason())
            .notes(appointmentRequest.getNotes())
            .clinicId(appointmentRequest.getClinicId())
            .vetId(appointmentRequest.getVetId())
            .petId(appointmentRequest.getPetId())
            .build();
        val appointmentData = appointmentService.save(appointment);
        stepStore.setItem("appointmentData", appointmentData);
    }
}
