//package com.jakubolejarczyk.vet_server.step.get.service_clinic;
//
//import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
//import com.jakubolejarczyk.vet_server.model.independent.Clinic;
//import com.jakubolejarczyk.vet_server.service.dependent.ServiceClinicService;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
//import com.jakubolejarczyk.vet_server.store.StepStore;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class GetServiceClinicsByClinicsStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
//    private final ServiceClinicService serviceClinicService;
//
//    @Override
//    public void runStep(StepStore<TData, TMetadata> stepStore) {
//        if (stepStore.hasNotItem("clinicsData")) throw new Error("The clinicsData is required!");
//        val clinicsData = stepStore.getItemAsArray("clinicsData", Clinic.class);
//        val clinicsIds = clinicsData.stream()
//            .map(Clinic::getId)
//            .toList();
//        // Data
//        val serviceClinicsData = serviceClinicService.findAllByClinicIdIn(clinicsIds);
//        // MetaData clinic id
//        val serviceClinicsClientIdMetaData = new BaseMetadata();
//        clinicsData.forEach(clinic -> {
//            serviceClinicsClientIdMetaData.addValue(clinic.getId(), clinic.getFullName());
//        });
//        stepStore.setItem("serviceClinicsClientIdMetaData", serviceClinicsClientIdMetaData);
//    }
//}
