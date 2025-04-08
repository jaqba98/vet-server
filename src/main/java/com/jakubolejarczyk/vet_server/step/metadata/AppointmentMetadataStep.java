package com.jakubolejarczyk.vet_server.step.metadata;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import com.jakubolejarczyk.vet_server.dto.data.dependent.AppointmentData;
import com.jakubolejarczyk.vet_server.dto.metadata.dependent.AppointmentMetadata;
import com.jakubolejarczyk.vet_server.model.dependent.Pet;
import com.jakubolejarczyk.vet_server.model.dependent.Vet;
import com.jakubolejarczyk.vet_server.model.independent.Clinic;
import com.jakubolejarczyk.vet_server.service.dependent.VetService;
import com.jakubolejarczyk.vet_server.service.independent.AccountService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppointmentMetadataStep implements StepRunnerModel<AppointmentData, AppointmentMetadata> {
    private final VetService vetService;
    private final AccountService accountService;

    @Override
    public void runStep(StepStore<AppointmentData, AppointmentMetadata> stepStore) {
        if (stepStore.hasNotItem("clinicsData")) throw new Error("The clinicsData is required!");
        if (stepStore.hasNotItem("petsData")) throw new Error("The petsData is required!");
        val clinicId = new BaseMetadata();
        stepStore.getItemAsArray("clinicsData", Clinic.class).forEach(clinic -> {
            clinicId.addValue(clinic.getId(), clinic.getFullName());
        });
        stepStore.setItem("clinicId", clinicId);
        val petId = new BaseMetadata();
        stepStore.getItemAsArray("petsData", Pet.class).forEach(pet -> {
            petId.addValue(pet.getId(), pet.getFullName());
        });
        stepStore.setItem("petId", petId);
        val vetId = new BaseMetadata();
        val accountIds = vetService.findAll().stream()
            .map(Vet::getAccountId)
            .distinct()
            .toList();
        val accounts = accountService.findAllById(accountIds);
        accountIds.forEach((accountId) -> {
            val currAccount = accounts.stream()
                .filter((item) -> item.getId().equals(accountId))
                .findFirst();
            if (currAccount.isPresent()) {
                val fullName = currAccount.get().getFirstName() + " " + currAccount.get().getLastName();
                vetId.addValue(accountId, fullName);
            }
        });
        stepStore.setItem("vetId", vetId);
    }
}
