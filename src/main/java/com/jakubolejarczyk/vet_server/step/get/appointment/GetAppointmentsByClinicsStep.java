//package com.jakubolejarczyk.vet_server.step.get.appointment;
//
//import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
//import com.jakubolejarczyk.vet_server.model.independent.Clinic;
//import com.jakubolejarczyk.vet_server.service.dependent.AppointmentService;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
//import com.jakubolejarczyk.vet_server.store.StepStore;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class GetAppointmentsByClinicsStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
//    private final AppointmentService appointmentService;
//
//    @Override
//    public void runStep(StepStore<TData, TMetadata> stepStore) {
//        if (stepStore.hasNotItem("clinicsData")) throw new Error("The clinicsData is required!");
//        val clinicsData = stepStore.getItemAsArray("clinicsData", Clinic.class);
//        val clinicsIds = clinicsData.stream()
//            .map(Clinic::getId)
//            .toList();
//        val appointmentsData = appointmentService.findAllByClinicIdIn(clinicsIds);
//        // Data
//        stepStore.setItem("appointmentsData", appointmentsData);
//        // MetaData clinic id
//        val appointmentsClinicIdMetaData = new BaseMetadata();
//        clinicsData.forEach(clinic -> {
//            appointmentsClinicIdMetaData.addValue(clinic.getId(), clinic.getFullName());
//        });
//        stepStore.setItem("appointmentsClinicIdMetaData", appointmentsClinicIdMetaData);
//        // todo: MetaData vet id
//        // todo: MetaData pet id
//        // todo: MetaData invoice id
//        // todo: MetaData medical record id
//    }
//}
