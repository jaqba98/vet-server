package com.jakubolejarczyk.vet_server.step.update;

import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.service.crud.dependent.ClinicService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateClinicStep implements StepModel {
    private final ClinicService clinicService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("newClinic")) throw new Error("The newClinic is required!");
        if (stepStore.hasNotItem("currentClinic")) throw new Error("The currentClinic is required!");
        val newClinic = stepStore.getItem("newClinic", Clinic.class);
        val currentClinic = stepStore.getItem("currentClinic", Clinic.class);
        val clinic = Clinic.builder()
            .id(currentClinic.getId())
            .name(newClinic.getName())
            .street(newClinic.getStreet())
            .buildingNumber(newClinic.getBuildingNumber())
            .apartmentNumber(newClinic.getApartmentNumber())
            .postalCode(newClinic.getPostalCode())
            .city(newClinic.getCity())
            .province(newClinic.getProvince())
            .country(newClinic.getCountry())
            .email(newClinic.getEmail())
            .phoneNumber(newClinic.getPhoneNumber())
            .isArchived(newClinic.getIsArchived())
            .openingHoursId(currentClinic.getOpeningHoursId())
            .build();
        val updatedClinic = clinicService.create(clinic);
        stepStore.addMessage("The clinic has been updated successfully!");
        stepStore.setItem("clinic", clinic);
    }
}
