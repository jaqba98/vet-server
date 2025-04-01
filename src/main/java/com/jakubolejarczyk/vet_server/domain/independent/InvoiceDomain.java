package com.jakubolejarczyk.vet_server.domain.independent;

import com.jakubolejarczyk.vet_server.domain.base.BaseDomain;

import java.math.BigDecimal;
import java.sql.Date;

public interface InvoiceDomain extends BaseDomain {
    Date getInvoiceDate();
    Date getDueDate();
    BigDecimal getTotalAmount();
    BigDecimal getAmountPaid();
    BigDecimal getOutstandingAmount();
    String getPaymentStatus();
    String getPaymentMethod();
    String getNotes();
}
