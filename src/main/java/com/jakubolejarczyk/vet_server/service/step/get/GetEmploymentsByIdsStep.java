//package com.jakubolejarczyk.vet_server.service.step.get;
//
//import com.jakubolejarczyk.vet_server.model.dependent.Employment;
//import com.jakubolejarczyk.vet_server.service.crud.dependent.EmploymentService;
//import com.jakubolejarczyk.vet_server.service.model.StepModel;
//import com.jakubolejarczyk.vet_server.service.model.StepOutput;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@AllArgsConstructor
//public class GetEmploymentsByIdsStep implements StepModel<List<Long>, List<Employment>> {
//    private final EmploymentService employmentService;
//
//    @Override
//    public StepOutput<List<Employment>> runStep(List<Long> employmentsIds) {
//        try {
//            val employments = employmentService.findAllById(employmentsIds);
//            return StepOutput.<List<Employment>>builder()
//                    .success(true)
//                    .output(employments)
//                    .build();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
