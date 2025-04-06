package com.jakubolejarczyk.vet_server.step.create;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import com.jakubolejarczyk.vet_server.model.dependent.OpeningHour;
import com.jakubolejarczyk.vet_server.model.independent.Clinic;
import com.jakubolejarczyk.vet_server.service.dependent.OpeningHourService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateOpeningHourStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final OpeningHourService openingHourService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("clinicData")) throw new Error("The clinicData is required!");
        val clinicData = stepStore.getItem("clinicData", Clinic.class);
        val openingHour = OpeningHour.builder()
            .clinicId(clinicData.getId())
            .build();
        val openingHourData = openingHourService.save(openingHour);
        // Data
        stepStore.setItem("openingHourData", openingHourData);
        // MetaData clinic id
        val createOpeningHourClinicIdMetaData = new BaseMetadata();
        createOpeningHourClinicIdMetaData.addValue(clinicData.getId(), clinicData.getFullName());
        stepStore.setItem("createOpeningHourClinicIdMetaData", createOpeningHourClinicIdMetaData);
    }
}
