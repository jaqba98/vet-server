package com.jakubolejarczyk.vet_server.domain.dependent;

import com.jakubolejarczyk.vet_server.domain.base.BaseDomain;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface InvoiceDomain extends BaseDomain {
    LocalDate getInvoiceDate();
    LocalDate getDueDate();
    BigDecimal getTotalAmount();
    BigDecimal getAmountPaid();
    BigDecimal getOutstandingAmount();
    String getPaymentStatus();
    String getPaymentMethod();
    String getNotes();
    Long getAppointmentId();
}
