//package com.jakubolejarczyk.vet_server.service.step_old;
//
//import com.jakubolejarczyk.vet_server.service.crud.dependent.ClinicService;
//import com.jakubolejarczyk.vet_server.step.model.StepModel;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@AllArgsConstructor
//public class UpdateOpeningHoursIdsIsArchivedStep {
//    private final ClinicService clinicService;
//
//    public StepModel<Boolean> runStep(ResponseStep responseStep, List<Long> ids, Boolean isArchived) {
//        clinicService.updateIsArchived(ids, isArchived);
//        return StepModel.<Boolean>builder()
//                .error(false)
//                .data(true)
//                .build();
//    }
//}
