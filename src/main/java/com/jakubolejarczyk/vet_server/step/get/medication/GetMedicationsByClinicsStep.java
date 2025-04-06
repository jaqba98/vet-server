package com.jakubolejarczyk.vet_server.step.get.medication;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import com.jakubolejarczyk.vet_server.model.independent.Clinic;
import com.jakubolejarczyk.vet_server.service.dependent.MedicationService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetMedicationsByClinicsStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final MedicationService medicationService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("clinicsData")) throw new Error("The clinicsData is required!");
        val clinicsData = stepStore.getItemAsArray("clinicsData", Clinic.class);
        val clinicIds = clinicsData.stream()
            .map(Clinic::getId)
            .toList();
        val medicationsData = medicationService.findAllByClinicIdIn(clinicIds);
        // Data
        stepStore.setItem("medicationsData", medicationsData);
        // MetaData clinic id
        val medicationsClinicIdMetaData = new BaseMetadata();
        clinicsData.forEach(clinic -> {
            medicationsClinicIdMetaData.addValue(clinic.getId(), clinic.getFullName());
        });
    }
}
