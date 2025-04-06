//package com.jakubolejarczyk.vet_server.step.update;
//
//import com.jakubolejarczyk.vet_server.model.dependent.OpeningHour;
//import com.jakubolejarczyk.vet_server.service.dependent.OpeningHourService;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
//import com.jakubolejarczyk.vet_server.store.StepStore;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class UpdateOpeningHoursStepRunner implements StepRunnerModel {
//    private final OpeningHourService openingHourService;
//
//    @Override
//    public void runStep(StepStore stepStore) {
//        if (stepStore.hasNotItem("requestOpeningHours")) throw new Error("The requestOpeningHours is required!");
//        val requestOpeningHours = stepStore.getItem("requestOpeningHours", OpeningHour.class);
//        val openingHoursId = requestOpeningHours.getId();
//        val currentOpeningHours = openingHourService.findById(openingHoursId);
//        if (currentOpeningHours.isPresent()) {
//            val newOpeningHours = OpeningHour.builder()
//                    .id(openingHoursId)
//                    .mondayFrom(requestOpeningHours.getMondayFrom())
//                    .mondayTo(requestOpeningHours.getMondayTo())
//                    .tuesdayFrom(requestOpeningHours.getTuesdayFrom())
//                    .tuesdayTo(requestOpeningHours.getTuesdayTo())
//                    .wednesdayFrom(requestOpeningHours.getWednesdayFrom())
//                    .wednesdayTo(requestOpeningHours.getWednesdayTo())
//                    .thursdayFrom(requestOpeningHours.getThursdayFrom())
//                    .thursdayTo(requestOpeningHours.getThursdayTo())
//                    .fridayFrom(requestOpeningHours.getFridayFrom())
//                    .fridayTo(requestOpeningHours.getFridayTo())
//                    .saturdayFrom(requestOpeningHours.getSaturdayFrom())
//                    .saturdayTo(requestOpeningHours.getSaturdayTo())
//                    .sundayFrom(requestOpeningHours.getSundayFrom())
//                    .sundayTo(requestOpeningHours.getSundayTo())
//                    .build();
//            val openingHours = openingHourService.save(newOpeningHours);
//            stepStore.setItem("openingHours", openingHours);
//            return;
//        }
//        stepStore.addMessage("Failed to update an opening hours.");
//    }
//}
