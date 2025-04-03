package com.jakubolejarczyk.vet_server.step.create;

import com.jakubolejarczyk.vet_server.model.independent.Invoice;
import com.jakubolejarczyk.vet_server.service.independent.InvoiceService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;


@Service
@AllArgsConstructor
public class CreateInvoiceStep implements StepModel {
    private final InvoiceService invoiceService;

    @Override
    public void runStep(StepStore stepStore) {
        val localDate = LocalDate.now();
        val now = Date.valueOf(localDate).toLocalDate();
        val newInvoice = Invoice.builder()
                .invoiceDate(now)
                .dueDate(now)
                .totalAmount(BigDecimal.valueOf(10))
                .amountPaid(BigDecimal.valueOf(10))
                .outstandingAmount(BigDecimal.valueOf(10))
                .paymentStatus("aaa")
                .paymentMethod("bbb")
                .notes("abc")
                .build();
        val invoice = invoiceService.save(newInvoice);
        stepStore.setItem("invoice", invoice);
    }
}
