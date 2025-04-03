package com.jakubolejarczyk.vet_server.step.get;

import com.jakubolejarczyk.vet_server.service.dependent.AppointmentService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAppointmentsByClinicIdsStep implements StepModel {
    private final AppointmentService appointmentService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("clinicIds")) throw new Error("The clinicIds is required!");
        val clinicIds = stepStore.getItemAsArray("clinicIds", Long.class);
        val appointments = appointmentService.findAllByClinicIdIn(clinicIds);
        stepStore.setItem("appointments", appointments);
    }
}
