//package com.jakubolejarczyk.vet_server.step.create;
//
//import com.jakubolejarczyk.vet_server.model.independent.MedicalRecord;
//import com.jakubolejarczyk.vet_server.service.independent.MedicalRecordService;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
//import com.jakubolejarczyk.vet_server.store.StepStore;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//import java.sql.Date;
//import java.time.LocalDate;
//
//@Service
//@AllArgsConstructor
//public class CreateMedicalRecordStepRunner implements StepRunnerModel {
//    private final MedicalRecordService medicalRecordService;
//
//    @Override
//    public void runStep(StepStore stepStore) {
//        val localDate = LocalDate.now();
//        val now = Date.valueOf(localDate).toLocalDate();
//        val newMedicalRecord = MedicalRecord.builder()
//                .diagnosis("")
//                .treatment("")
//                .procedures("")
//                .nextAppointment(now)
//                .status("")
//                .notes("")
//                .build();
//        val medicalRecord = medicalRecordService.save(newMedicalRecord);
//        stepStore.setItem("medicalRecord", medicalRecord);
//    }
//}
