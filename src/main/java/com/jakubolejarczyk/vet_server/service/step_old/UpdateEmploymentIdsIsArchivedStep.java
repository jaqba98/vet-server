//package com.jakubolejarczyk.vet_server.service.step_old;
//
//import com.jakubolejarczyk.vet_server.service.crud.dependent.EmploymentService;
//import com.jakubolejarczyk.vet_server.step.model.StepModel;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@AllArgsConstructor
//public class UpdateEmploymentIdsIsArchivedStep {
//    private final EmploymentService employmentService;
//
//    public StepModel<Boolean> runStep(ResponseStep responseStep, List<Long> ids, Boolean isArchived) {
//        employmentService.updateIsArchived(ids, isArchived);
//        return StepModel.<Boolean>builder()
//                .error(false)
//                .data(true)
//                .build();
//    }
//}
