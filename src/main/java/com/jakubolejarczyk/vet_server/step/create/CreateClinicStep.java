package com.jakubolejarczyk.vet_server.step.create;

import com.jakubolejarczyk.vet_server.model.independent.Clinic;
import com.jakubolejarczyk.vet_server.service.independent.ClinicService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateClinicStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final ClinicService clinicService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("requestClinic")) throw new Error("The requestClinic is required!");
        val requestClinic = stepStore.getItem("requestClinic", Clinic.class);
        val clinicData = Clinic.builder()
            .fullName(requestClinic.getFullName())
            .street(requestClinic.getStreet())
            .buildingNumber(requestClinic.getBuildingNumber())
            .apartmentNumber(requestClinic.getApartmentNumber())
            .postalCode(requestClinic.getPostalCode())
            .city(requestClinic.getCity())
            .province(requestClinic.getProvince())
            .country(requestClinic.getCountry())
            .email(requestClinic.getEmail())
            .phoneNumber(requestClinic.getPhoneNumber())
            .build();
        clinicService.save(clinicData);
        // Data
        stepStore.setItem("clinicData", clinicData);
    }
}
