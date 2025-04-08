package com.jakubolejarczyk.vet_server.step.get.appointment;

import com.jakubolejarczyk.vet_server.model.independent.Clinic;
import com.jakubolejarczyk.vet_server.service.dependent.AppointmentService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAppointmentsByClinicsStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final AppointmentService appointmentService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("clinicsData")) throw new Error("The clinicsData is required!");
        val clinicsData = stepStore.getItemAsArray("clinicsData", Clinic.class);
        val clinicsIds = clinicsData.stream()
            .map(Clinic::getId)
            .toList();
        val appointmentsData = appointmentService.findAllByClinicIdIn(clinicsIds);
        stepStore.setItem("appointmentsData", appointmentsData);
    }
}
