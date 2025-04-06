package com.jakubolejarczyk.vet_server.step.update;

import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.model.independent.Clinic;
import com.jakubolejarczyk.vet_server.service.independent.ClinicService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateClinicByEmploymentStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final ClinicService clinicService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("clinicRequest")) throw new Error("The clinicRequest is required!");
        if (stepStore.hasNotItem("employmentData")) throw new Error("The employmentData is required!");
        val clinicRequest = stepStore.getItem("clinicRequest", Clinic.class);
        val employmentData = stepStore.getItem("employmentData", Employment.class);
        val clinicId = employmentData.getClinicId();
        val currentClinic = clinicService.findById(clinicId);
        if (currentClinic.isEmpty()) {
            stepStore.setSuccess(false);
            stepStore.addMessage("Failed to update clinic!");
            return;
        }
        currentClinic.get().setFullName(clinicRequest.getFullName());
        currentClinic.get().setStreet(clinicRequest.getStreet());
        currentClinic.get().setBuildingNumber(clinicRequest.getBuildingNumber());
        currentClinic.get().setApartmentNumber(clinicRequest.getApartmentNumber());
        currentClinic.get().setPostalCode(clinicRequest.getPostalCode());
        currentClinic.get().setCity(clinicRequest.getCity());
        currentClinic.get().setProvince(clinicRequest.getProvince());
        currentClinic.get().setCountry(clinicRequest.getCountry());
        currentClinic.get().setEmail(clinicRequest.getEmail());
        currentClinic.get().setPhoneNumber(clinicRequest.getPhoneNumber());
        val clinicData = clinicService.save(currentClinic.get());
        stepStore.setItem("clinicData", clinicData);
    }
}
