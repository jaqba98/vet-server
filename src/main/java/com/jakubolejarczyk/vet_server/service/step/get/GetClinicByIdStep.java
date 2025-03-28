//package com.jakubolejarczyk.vet_server.service.step.get;
//
//import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
//import com.jakubolejarczyk.vet_server.service.crud.dependent.ClinicService;
//import com.jakubolejarczyk.vet_server.step.model.StepModel;
//import com.jakubolejarczyk.vet_server.service.model.StepOutput;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class GetClinicByIdStep implements StepModel<Long, Clinic> {
//    private final ClinicService clinicService;
//
//    @Override
//    public StepOutput<Clinic> runStep(Long clinicId) {
//        try {
//            val clinic = clinicService.findById(clinicId);
//            if (clinic.isPresent()) {
//                return StepOutput.<Clinic>builder()
//                        .success(true)
//                        .output(clinic.get())
//                        .build();
//            }
//            return StepOutput.<Clinic>builder()
//                    .success(false)
//                    .build();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
