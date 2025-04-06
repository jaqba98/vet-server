//package com.jakubolejarczyk.vet_server.step.create;
//
//import com.jakubolejarczyk.vet_server.model.dependent.Appointment;
//import com.jakubolejarczyk.vet_server.model.dependent.Invoice;
//import com.jakubolejarczyk.vet_server.model.dependent.MedicalRecord;
//import com.jakubolejarczyk.vet_server.service.dependent.AppointmentService;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
//import com.jakubolejarczyk.vet_server.store.StepStore;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//import java.sql.Timestamp;
//
//
//@Service
//@AllArgsConstructor
//public class CreateAppointmentStepRunner implements StepRunnerModel {
//    private final AppointmentService appointmentService;
//
//    @Override
//    public void runStep(StepStore stepStore) {
//        if (stepStore.hasNotItem("requestAppointment")) throw new Error("The requestAppointment is required!");
//        if (stepStore.hasNotItem("invoice")) throw new Error("The invoice is required!");
//        if (stepStore.hasNotItem("medicalRecord")) throw new Error("The medicalRecord is required!");
//        if (stepStore.hasNotItem("clinicId")) throw new Error("The clinicId is required!");
//        val requestAppointment = stepStore.getItem("requestAppointment", Appointment.class);
//        val invoice = stepStore.getItem("invoice", Invoice.class);
//        val medicalRecord = stepStore.getItem("medicalRecord", MedicalRecord.class);
//        val clinicId = stepStore.getItem("clinicId", Long.class);
//        val timestamp = new Timestamp(System.currentTimeMillis());
//        val newAppointment = Appointment.builder()
//                .dateAndHour(timestamp)
//                .type(requestAppointment.getType())
//                .status(requestAppointment.getStatus())
//                .reason(requestAppointment.getReason())
//                .notes(requestAppointment.getNotes())
//                .clinicId(clinicId)
//                .vetId(requestAppointment.getVetId())
//                .petId(requestAppointment.getPetId())
//                .invoiceId(invoice.getId())
//                .medicalRecordId(medicalRecord.getId())
//                .build();
//        val appointment = appointmentService.save(newAppointment);
//        stepStore.setItem("appointment", appointment);
//    }
//}
