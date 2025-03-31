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
        if (stepStore.hasNotItem("name")) throw new Error("The name is required!");
        if (stepStore.hasNotItem("street")) throw new Error("The street is required!");
        if (stepStore.hasNotItem("buildingNumber")) throw new Error("The buildingNumber is required!");
        if (stepStore.hasNotItem("apartmentNumber")) throw new Error("The apartmentNumber is required!");
        if (stepStore.hasNotItem("postalCode")) throw new Error("The postalCode is required!");
        if (stepStore.hasNotItem("city")) throw new Error("The city is required!");
        if (stepStore.hasNotItem("province")) throw new Error("The province is required!");
        if (stepStore.hasNotItem("country")) throw new Error("The country is required!");
        if (stepStore.hasNotItem("email")) throw new Error("The email is required!");
        if (stepStore.hasNotItem("phoneNumber")) throw new Error("The phoneNumber is required!");
        val openingHours = stepStore.getItem("openingHours", OpeningHours.class);
        val openingHoursId = openingHours.getId();
        val name = stepStore.getItem("name", String.class);
        val street = stepStore.getItem("street", String.class);
        val buildingNumber = stepStore.getItem("buildingNumber", String.class);
        val apartmentNumber = stepStore.getItem("apartmentNumber", String.class);
        val postalCode = stepStore.getItem("postalCode", String.class);
        val city = stepStore.getItem("city", String.class);
        val province = stepStore.getItem("province", String.class);
        val country = stepStore.getItem("country", String.class);
        val email = stepStore.getItem("email", String.class);
        val phoneNumber = stepStore.getItem("phoneNumber", String.class);
        val clinic = Clinic.builder()
                .name(name)
                .street(street)
                .buildingNumber(buildingNumber)
                .apartmentNumber(apartmentNumber)
                .postalCode(postalCode)
                .city(city)
                .province(province)
                .country(country)
                .email(email)
                .phoneNumber(phoneNumber)
                .isArchived(false)
                .openingHoursId(openingHoursId)
                .build();
        val newClinic = clinicService.create(clinic);
        stepStore.setItem("clinic", newClinic);
    }
}
