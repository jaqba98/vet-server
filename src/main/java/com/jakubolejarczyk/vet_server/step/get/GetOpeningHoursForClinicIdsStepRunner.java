//package com.jakubolejarczyk.vet_server.step.get;
//
//import com.jakubolejarczyk.vet_server.model.independent.Clinic;
//import com.jakubolejarczyk.vet_server.service.independent.ClinicService;
//import com.jakubolejarczyk.vet_server.service.dependent.OpeningHourService;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
//import com.jakubolejarczyk.vet_server.store.StepStore;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class GetOpeningHoursForClinicIdsStepRunner implements StepRunnerModel {
//    private final ClinicService clinicService;
//    private final OpeningHourService openingHourService;
//
//    @Override
//    public void runStep(StepStore stepStore) {
//        if (stepStore.hasNotItem("clinicIds")) throw new Error("The clinicIds is required!");
//        val clinicIds = stepStore.getItemAsArray("clinicIds", Long.class);
//        val openingHoursIds = clinicService.findAllById(clinicIds).stream()
//                .map(Clinic::getOpeningHourId)
//                .toList();
//        val openingHours = openingHourService.findAllById(openingHoursIds);
//        stepStore.setItem("openingHours", openingHours);
//    }
//}
