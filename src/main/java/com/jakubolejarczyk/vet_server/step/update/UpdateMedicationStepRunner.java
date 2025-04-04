//package com.jakubolejarczyk.vet_server.step.update;
//
//import com.jakubolejarczyk.vet_server.model.dependent.Medication;
//import com.jakubolejarczyk.vet_server.service.dependent.MedicationService;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
//import com.jakubolejarczyk.vet_server.store.StepStore;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class UpdateMedicationStepRunner implements StepRunnerModel {
//    private final MedicationService medicationService;
//
//    @Override
//    public void runStep(StepStore stepStore) {
//        if (stepStore.hasNotItem("requestMedication")) throw new Error("The requestMedication is required!");
//        val requestMedication = stepStore.getItem("requestMedication", Medication.class);
//        val medicationId = requestMedication.getId();
//        val currentMedication = medicationService.findById(medicationId);
//        if (currentMedication.isPresent()) {
//            val newMedication = Medication.builder()
//                    .id(currentMedication.get().getId())
//                    .fullName(requestMedication.getFullName())
//                    .description(requestMedication.getDescription())
//                    .manufacturer(requestMedication.getManufacturer())
//                    .dose(requestMedication.getDose())
//                    .quantityInStock(requestMedication.getQuantityInStock())
//                    .expirationDate(requestMedication.getExpirationDate())
//                    .price(requestMedication.getPrice())
//                    .isAvailable(requestMedication.getIsAvailable())
//                    .clinicId(currentMedication.get().getClinicId())
//                    .build();
//            val medication = medicationService.save(newMedication);
//            stepStore.setItem("medication", medication);
//            return;
//        }
//        stepStore.setSuccess(false);
//        stepStore.addMessage("Failed to update a medication.");
//    }
//}
