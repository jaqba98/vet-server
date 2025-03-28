//package com.jakubolejarczyk.vet_server.service.step_old;
//
//import com.jakubolejarczyk.vet_server.model.dependent.Employment;
//import com.jakubolejarczyk.vet_server.service.crud.dependent.EmploymentService;
//import com.jakubolejarczyk.vet_server.step.model.StepModel;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.stream.Collectors;
//
//@Service
//@AllArgsConstructor
//public class GetClinicIdsForAccountStep {
//    private final EmploymentService employmentService;
//
//    public StepModel<ArrayList<Long>> runStep(ResponseStep responseStep, Long accountId) {
//        val clinicIds = employmentService.findAllByAccountId(accountId).stream()
//                .map(Employment::getClinicId)
//                .collect(Collectors.toCollection(ArrayList::new));
//        return StepModel.<ArrayList<Long>>builder()
//                .error(false)
//                .data(clinicIds)
//                .build();
//    }
//}
