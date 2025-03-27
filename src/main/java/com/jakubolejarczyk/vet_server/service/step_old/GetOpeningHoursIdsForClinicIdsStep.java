//package com.jakubolejarczyk.vet_server.service.step_old;
//
//import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
//import com.jakubolejarczyk.vet_server.service.crud.dependent.ClinicService;
//import com.jakubolejarczyk.vet_server.service.model.StepModel;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.stream.Collectors;
//
//@Service
//@AllArgsConstructor
//public class GetOpeningHoursIdsForClinicIdsStep {
//    private final ClinicService clinicService;
//
//    public StepModel<ArrayList<Long>> runStep(ResponseStep responseStep, ArrayList<Long> clinicIds) {
//        try {
//            val openingHoursIds = clinicService.findAllByIds(clinicIds).stream()
//                    .map(Clinic::getOpeningHoursId)
//                    .collect(Collectors.toCollection(ArrayList::new));
//            return StepModel.<ArrayList<Long>>builder()
//                    .error(false)
//                    .data(openingHoursIds)
//                    .build();
//        } catch (Exception e) {
//            responseStep.addMessage("Failed to get opening hours IDs for clinic IDs!");
//            return StepModel.<ArrayList<Long>>builder()
//                    .error(true)
//                    .data(new ArrayList<>())
//                    .build();
//        }
//    }
//}
