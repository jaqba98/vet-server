//package com.jakubolejarczyk.vet_server.service.step.create;
//
//import com.jakubolejarczyk.vet_server.model.independent.OpeningHours;
//import com.jakubolejarczyk.vet_server.service.crud.independent.OpeningHoursService;
//import com.jakubolejarczyk.vet_server.service.model.StepModel;
//import com.jakubolejarczyk.vet_server.service.model.StepOutput;
//import jakarta.validation.constraints.Null;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class CreateOpeningHoursStep implements StepModel<Null, OpeningHours> {
//    private final OpeningHoursService openingHoursService;
//
//    @Override
//    public StepOutput<OpeningHours> runStep(Null input) {
//        try {
//            val openingHours = OpeningHours.builder()
//                    .isArchived(false)
//                    .build();
//            val newOpeningHours = openingHoursService.create(openingHours);
//            return StepOutput.<OpeningHours>builder()
//                    .success(true)
//                    .output(newOpeningHours)
//                    .build();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
