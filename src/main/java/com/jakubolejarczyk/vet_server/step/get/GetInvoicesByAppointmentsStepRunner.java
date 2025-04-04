//package com.jakubolejarczyk.vet_server.step.get;
//
//import com.jakubolejarczyk.vet_server.model.dependent.Appointment;
//import com.jakubolejarczyk.vet_server.service.independent.InvoiceService;
//import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
//import com.jakubolejarczyk.vet_server.store.StepStore;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class GetInvoicesByAppointmentsStepRunner implements StepRunnerModel {
//    private final InvoiceService invoiceService;
//
//    @Override
//    public void runStep(StepStore stepStore) {
//        if (stepStore.hasNotItem("appointments")) throw new Error("The appointments is required!");
//        val appointments = stepStore.getItemAsArray("appointments", Appointment.class);
//        val invoicesIds = appointments.stream().map(Appointment::getInvoiceId).toList();
//        val invoices = invoiceService.findAllById(invoicesIds);
//        stepStore.setItem("invoices", invoices);
//    }
//}
