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
        if (stepStore.hasNotItem("clinicRequest")) throw new Error("The clinicRequest is required!");
        val clinicRequest = stepStore.getItem("clinicRequest", Clinic.class);
        val clinicByFullName = clinicService.findByFullName(clinicRequest.getFullName());
        if (clinicByFullName.isPresent()) {
            stepStore.setSuccess(false);
            stepStore.addMessage("The full name of the clinic is already taken!");
            return;
        }
        val clinicByEmail = clinicService.findByEmail(clinicRequest.getEmail());
        if (clinicByEmail.isPresent()) {
            stepStore.setSuccess(false);
            stepStore.addMessage("The email of the clinic is already taken!");
            return;
        }
        val clinic = Clinic.builder()
            .fullName(clinicRequest.getFullName())
            .street(clinicRequest.getStreet())
            .buildingNumber(clinicRequest.getBuildingNumber())
            .apartmentNumber(clinicRequest.getApartmentNumber())
            .postalCode(clinicRequest.getPostalCode())
            .city(clinicRequest.getCity())
            .province(clinicRequest.getProvince())
            .country(clinicRequest.getCountry())
            .email(clinicRequest.getEmail())
            .phoneNumber(clinicRequest.getPhoneNumber())
            .build();
        val clinicData = clinicService.save(clinic);
        stepStore.setItem("clinicData", clinicData);
    }
}
