//package com.jakubolejarczyk.vet_server.step.update;
//
//import com.jakubolejarczyk.vet_server.service.dependent.MedicationService;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
//import com.jakubolejarczyk.vet_server.store.StepStore;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class UpdateMedicationIsArchivedStepRunner implements StepRunnerModel {
//    private final MedicationService medicationService;
//
//    @Override
//    public void runStep(StepStore stepStore) {
//        if (stepStore.hasNotItem("medicationIds")) throw new Error("The medicationIds is required!");
//        val medicationIds = stepStore.getItemAsArray("medicationIds", Long.class);
//        medicationService.deleteAllById(medicationIds);
//    }
//}
