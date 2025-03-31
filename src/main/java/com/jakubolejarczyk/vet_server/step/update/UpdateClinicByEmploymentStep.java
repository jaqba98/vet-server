package com.jakubolejarczyk.vet_server.step.update;

import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.service.dependent.ClinicService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateClinicByEmploymentStep implements StepModel {
    private final ClinicService clinicService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("requestClinic")) throw new Error("The requestClinic is required!");
        if (stepStore.hasNotItem("employment")) throw new Error("The employment is required!");
        val requestClinic = stepStore.getItem("requestClinic", Clinic.class);
        val employment = stepStore.getItem("employment", Employment.class);
        val clinicId = employment.getClinicId();
        val currentClinic = clinicService.findById(clinicId);
        if (currentClinic.isPresent()) {
            val clinic = Clinic.builder()
                    .id(currentClinic.get().getId())
                    .isArchived(currentClinic.get().getIsArchived())
                    .name(requestClinic.getName())
                    .street(requestClinic.getStreet())
                    .buildingNumber(requestClinic.getBuildingNumber())
                    .apartmentNumber(requestClinic.getApartmentNumber())
                    .postalCode(requestClinic.getPostalCode())
                    .city(requestClinic.getCity())
                    .province(requestClinic.getProvince())
                    .country(requestClinic.getCountry())
                    .email(requestClinic.getEmail())
                    .phoneNumber(requestClinic.getPhoneNumber())
                    .openingHoursId(currentClinic.get().getOpeningHoursId())
                    .build();
            val updatedClinic = clinicService.create(clinic);
            stepStore.setItem("updatedClinic", updatedClinic);
            return;
        }
        stepStore.setSuccess(false);
        stepStore.addMessage("Failed to update clinic!");
    }
}
