//package com.jakubolejarczyk.vet_server.controller.old.clinic_opening_hours;
//
//import com.jakubolejarczyk.vet_server.dto.request.independent.OpeningHourRequest;
//import com.jakubolejarczyk.vet_server.dto.response.Response;
//import com.jakubolejarczyk.vet_server.model.independent.OpeningHour;
//import com.jakubolejarczyk.vet_server.security.HandleValidationService;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerController;
//import com.jakubolejarczyk.vet_server.step.get.GetAccountByTokenStepRunner;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
//import com.jakubolejarczyk.vet_server.step.success.SuccessUpdateOpeningHoursStepRunner;
//import com.jakubolejarczyk.vet_server.step.update.UpdateOpeningHoursStepRunner;
//import com.jakubolejarczyk.vet_server.store.StepStore;
//import jakarta.validation.Valid;
//import lombok.val;
//import org.springframework.beans.factory.ObjectFactory;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//
//@RestController
//@RequestMapping("/api/v1")
//public class ClinicOpeningHoursUpdateController extends StepRunnerController {
//    private final GetAccountByTokenStepRunner getAccountByTokenStep;
//    private final UpdateOpeningHoursStepRunner updateOpeningHoursStep;
//    private final SuccessUpdateOpeningHoursStepRunner successUpdateOpeningHoursStep;
//
//    public ClinicOpeningHoursUpdateController(
//            ObjectFactory<StepStore> stepStoreObjectFactory,
//            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory,
//            GetAccountByTokenStepRunner getAccountByTokenStep,
//            UpdateOpeningHoursStepRunner updateOpeningHoursStep,
//            SuccessUpdateOpeningHoursStepRunner successUpdateOpeningHoursStep
//    ) {
//        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
//        this.getAccountByTokenStep = getAccountByTokenStep;
//        this.updateOpeningHoursStep = updateOpeningHoursStep;
//        this.successUpdateOpeningHoursStep = successUpdateOpeningHoursStep;
//    }
//
//    @PostMapping("clinic-opening-hours-update")
//    public ResponseEntity<Response<?, ?>> clinicOpeningHoursUpdate(@Valid @RequestBody OpeningHourRequest request) {
//        val steps = new ArrayList<StepRunnerModel>();
//        steps.addLast(getAccountByTokenStep);
//        steps.addLast(updateOpeningHoursStep);
//        steps.addLast(successUpdateOpeningHoursStep);
//        String[] dataKeys = {"openingHours"};
//        String[] metadataKeys = {};
//        initController(dataKeys, metadataKeys);
//        getStepStore().setItem("token", request.getToken());
//        val requestOpeningHours = OpeningHour.builder()
//                .id(request.getId())
//                .mondayFrom(request.getMondayFrom())
//                .mondayTo(request.getMondayTo())
//                .tuesdayFrom(request.getTuesdayFrom())
//                .tuesdayTo(request.getTuesdayTo())
//                .wednesdayFrom(request.getWednesdayFrom())
//                .wednesdayTo(request.getWednesdayTo())
//                .thursdayFrom(request.getThursdayFrom())
//                .thursdayTo(request.getTuesdayTo())
//                .fridayFrom(request.getFridayFrom())
//                .fridayTo(request.getFridayTo())
//                .saturdayFrom(request.getSaturdayFrom())
//                .saturdayTo(request.getSaturdayTo())
//                .sundayFrom(request.getSundayFrom())
//                .sundayTo(request.getSundayTo())
//                .build();
//        getStepStore().setItem("requestOpeningHours", requestOpeningHours);
//        return runController(steps);
//    }
//}
