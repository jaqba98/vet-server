//package com.jakubolejarczyk.vet_server.step.create;
//
//import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
//import com.jakubolejarczyk.vet_server.model.dependent.Employment;
//import com.jakubolejarczyk.vet_server.model.independent.Account;
//import com.jakubolejarczyk.vet_server.service.dependent.EmploymentService;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
//import com.jakubolejarczyk.vet_server.store.StepStore;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class CreateEmploymentStepRunner implements StepRunnerModel {
//    private final EmploymentService employmentService;
//
//    @Override
//    public void runStep(StepStore stepStore) {
//        if (stepStore.hasNotItem("account")) throw new Error("The account is required!");
//        if (stepStore.hasNotItem("clinic")) throw new Error("The clinic is required!");
//        val account = stepStore.getItem("account", Account.class);
//        val clinic = stepStore.getItem("clinic", Clinic.class);
//        val accountId = account.getId();
//        val clinicId = clinic.getId();
//        val newEmployment = Employment.builder()
//                .isOwner(true)
//                .accountId(accountId)
//                .clinicId(clinicId)
//                .build();
//        val employment = employmentService.save(newEmployment);
//        stepStore.setItem("employment", employment);
//    }
//}
