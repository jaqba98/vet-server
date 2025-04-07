package com.jakubolejarczyk.vet_server.step.update;

import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.model.dependent.OpeningHour;
import com.jakubolejarczyk.vet_server.service.dependent.OpeningHourService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateOpeningHourByEmploymentStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final OpeningHourService openingHourService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("openingHourRequest")) throw new Error("The openingHourRequest is required!");
        if (stepStore.hasNotItem("employmentData")) throw new Error("The employmentData is required!");
        val openingHourRequest = stepStore.getItem("openingHourRequest", OpeningHour.class);
        val employmentData = stepStore.getItem("employmentData", Employment.class);
        val clinicId = employmentData.getClinicId();
        val currentOpeningHour = openingHourService.findByClinicId(clinicId);
        if (currentOpeningHour.isEmpty()) {
            stepStore.setSuccess(false);
            stepStore.addMessage("Failed to update opening hour!");
            return;
        }
        currentOpeningHour.get().setMondayFrom(openingHourRequest.getMondayFrom());
        currentOpeningHour.get().setMondayTo(openingHourRequest.getMondayTo());
        currentOpeningHour.get().setThursdayFrom(openingHourRequest.getThursdayFrom());
        currentOpeningHour.get().setThursdayTo(openingHourRequest.getThursdayTo());
        currentOpeningHour.get().setWednesdayFrom(openingHourRequest.getWednesdayFrom());
        currentOpeningHour.get().setWednesdayTo(openingHourRequest.getWednesdayTo());
        currentOpeningHour.get().setThursdayFrom(openingHourRequest.getThursdayFrom());
        currentOpeningHour.get().setThursdayTo(openingHourRequest.getThursdayTo());
        currentOpeningHour.get().setFridayFrom(openingHourRequest.getFridayFrom());
        currentOpeningHour.get().setFridayTo(openingHourRequest.getFridayTo());
        currentOpeningHour.get().setSaturdayFrom(openingHourRequest.getSaturdayFrom());
        currentOpeningHour.get().setSaturdayTo(openingHourRequest.getSaturdayTo());
        currentOpeningHour.get().setSundayFrom(openingHourRequest.getSundayFrom());
        currentOpeningHour.get().setSundayTo(openingHourRequest.getSundayTo());
        val openingHourData = openingHourService.save(currentOpeningHour.get());
        stepStore.setItem("openingHourData", openingHourData);
    }
}
