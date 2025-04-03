package com.jakubolejarczyk.vet_server.step.create;

import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.model.independent.OpeningHour;
import com.jakubolejarczyk.vet_server.service.dependent.ClinicService;
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
        if (stepStore.hasNotItem("requestClinic")) throw new Error("The requestClinic is required!");
        val openingHours = stepStore.getItem("openingHours", OpeningHour.class);
        val requestClinic = stepStore.getItem("requestClinic", Clinic.class);
        val openingHoursId = openingHours.getId();
        val newClinic = Clinic.builder()
                .entityName(requestClinic.getEntityName())
                .street(requestClinic.getStreet())
                .buildingNumber(requestClinic.getBuildingNumber())
                .apartmentNumber(requestClinic.getApartmentNumber())
                .postalCode(requestClinic.getPostalCode())
                .city(requestClinic.getCity())
                .province(requestClinic.getProvince())
                .country(requestClinic.getCountry())
                .email(requestClinic.getEmail())
                .phoneNumber(requestClinic.getPhoneNumber())
                .openingHourId(openingHoursId)
                .build();
        val clinic = clinicService.save(newClinic);
        stepStore.setItem("clinic", clinic);
    }
}
