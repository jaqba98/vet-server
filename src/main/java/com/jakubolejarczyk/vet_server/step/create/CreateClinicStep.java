package com.jakubolejarczyk.vet_server.step.create;

import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.model.independent.OpeningHours;
import com.jakubolejarczyk.vet_server.service.crud.dependent.ClinicService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateClinicStep implements StepModel {
    private final ClinicService clinicService;

    @Override
    public void runStep(StepStore stepStore) {
        if (stepStore.hasNotItem("openingHours")) throw new Error("The openingHours is required!");
        if (stepStore.hasNotItem("clinic")) throw new Error("The clinic is required!");
        val openingHours = stepStore.getItem("openingHours", OpeningHours.class);
        val openingHoursId = openingHours.getId();
        val clinic = stepStore.getItem("clinic", Clinic.class);
        val newClinic = Clinic.builder()
                .name(clinic.getName())
                .street(clinic.getStreet())
                .buildingNumber(clinic.getBuildingNumber())
                .apartmentNumber(clinic.getApartmentNumber())
                .postalCode(clinic.getPostalCode())
                .city(clinic.getCity())
                .province(clinic.getProvince())
                .country(clinic.getCountry())
                .email(clinic.getEmail())
                .phoneNumber(clinic.getPhoneNumber())
                .isArchived(false)
                .openingHoursId(openingHoursId)
                .build();
        stepStore.addMessage("A new clinic has been created!");
    }
}
