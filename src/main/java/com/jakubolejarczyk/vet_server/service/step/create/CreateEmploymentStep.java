//package com.jakubolejarczyk.vet_server.service.step.create;
//
//import com.jakubolejarczyk.vet_server.model.dependent.Employment;
//import com.jakubolejarczyk.vet_server.service.crud.dependent.EmploymentService;
//import com.jakubolejarczyk.vet_server.service.input.create.CreateEmploymentInput;
//import com.jakubolejarczyk.vet_server.step.model.StepModel;
//import com.jakubolejarczyk.vet_server.service.model.StepOutput;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class CreateEmploymentStep implements StepModel<CreateEmploymentInput, Employment> {
//    private final EmploymentService employmentService;
//
//    @Override
//    public StepOutput<Employment> runStep(CreateEmploymentInput input) {
//        try {
//            val isOwner = input.isOwner();
//            val accountId = input.accountId();
//            val clinicId = input.clinicId();
//            val employment = Employment.builder()
//                    .isOwner(isOwner)
//                    .isArchived(false)
//                    .accountId(accountId)
//                    .clinicId(clinicId)
//                    .build();
//            val newEmployment = employmentService.create(employment);
//            return StepOutput.<Employment>builder()
//                    .success(true)
//                    .output(newEmployment)
//                    .build();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
