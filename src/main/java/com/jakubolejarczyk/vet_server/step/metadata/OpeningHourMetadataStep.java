package com.jakubolejarczyk.vet_server.step.metadata;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import com.jakubolejarczyk.vet_server.dto.data.independent.OpeningHourData;
import com.jakubolejarczyk.vet_server.dto.metadata.independent.OpeningHourMetadata;
import com.jakubolejarczyk.vet_server.model.dependent.Employment;
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
public class OpeningHourMetadataStep implements StepRunnerModel<OpeningHourData, OpeningHourMetadata> {
    private final OpeningHourService openingHourService;

    @Override
    public void runStep(StepStore<OpeningHourData, OpeningHourMetadata> stepStore) {
        if (stepStore.hasNotItem("employmentsData")) throw new Error("The employmentsData is required!");
        if (stepStore.hasNotItem("clinicsData")) throw new Error("The clinicsData is required!");
        val myClinicIds = stepStore.getItemAsArray("employmentsData", Employment.class).stream()
            .filter(Employment::getIsOwner)
            .map(Employment::getClinicId)
            .distinct()
            .toList();
        val myOpeningHourIds = openingHourService.findAllByClinicIdIn(myClinicIds).stream()
            .map(OpeningHour::getId)
            .distinct()
            .toList();
        val clinicId = new BaseMetadata();
        stepStore.getItemAsArray("clinicsData", Clinic.class).forEach((clinic) -> clinicId.addValue(clinic.getId(), clinic.getFullName()));
        stepStore.setItem("myOpeningHourIds", myOpeningHourIds);
        stepStore.setItem("clinicId", clinicId);
    }
}
