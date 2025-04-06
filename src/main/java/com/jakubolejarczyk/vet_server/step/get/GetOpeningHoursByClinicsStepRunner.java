//package com.jakubolejarczyk.vet_server.step.get;
//
//import com.jakubolejarczyk.vet_server.model.independent.Clinic;
//import com.jakubolejarczyk.vet_server.service.dependent.OpeningHourService;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
//import com.jakubolejarczyk.vet_server.store.StepStore;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class GetOpeningHoursByClinicsStepRunner implements StepRunnerModel {
//    private final OpeningHourService openingHourService;
//
//    @Override
//    public void runStep(StepStore stepStore) {
//        if (stepStore.hasNotItem("clinics")) throw new Error("The clinics is required!");
//        val clinics = stepStore.getItemAsArray("clinics", Clinic.class);
//        val openingHoursId = clinics.stream()
//                .map(Clinic::getOpeningHourId)
//                .toList();
//        val openingHours = openingHourService.findAllById(openingHoursId);
//        stepStore.setItem("openingHours", openingHours);
//    }
//}
