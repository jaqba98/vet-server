//package com.jakubolejarczyk.vet_server.step.update;
//
//import com.jakubolejarczyk.vet_server.model.dependent.MedicalRecord;
//import com.jakubolejarczyk.vet_server.service.dependent.MedicalRecordService;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
//import com.jakubolejarczyk.vet_server.store.StepStore;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class UpdateMedicalRecordStepRunner implements StepRunnerModel {
//    private final MedicalRecordService medicalRecordService;
//
//    @Override
//    public void runStep(StepStore stepStore) {
//        if (stepStore.hasNotItem("requestMedicalRecord")) throw new Error("The requestMedicalRecord is required!");
//        val requestMedicalRecord = stepStore.getItem("requestMedicalRecord", MedicalRecord.class);
//        val medicalRecordId = requestMedicalRecord.getId();
//        val currentMedicalRecord = medicalRecordService.findById(medicalRecordId);
//        if (currentMedicalRecord.isPresent()) {
//            val newMedicalRecord = MedicalRecord.builder()
//                    .id(currentMedicalRecord.get().getId())
//                    .diagnosis(requestMedicalRecord.getDiagnosis())
//                    .treatment(requestMedicalRecord.getTreatment())
//                    .procedures(requestMedicalRecord.getProcedures())
//                    .nextAppointment(requestMedicalRecord.getNextAppointment())
//                    .status(requestMedicalRecord.getStatus())
//                    .notes(requestMedicalRecord.getNotes())
//                    .build();
//            val medicalRecord = medicalRecordService.save(newMedicalRecord);
//            stepStore.setItem("medicalRecord", medicalRecord);
//            return;
//        }
//        stepStore.setSuccess(false);
//        stepStore.addMessage("Failed to update an medical record.");
//    }
//}
