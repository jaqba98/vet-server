package com.jakubolejarczyk.vet_server.dto.request.dependent;

import com.jakubolejarczyk.vet_server.domain.dependent.InvoiceDomain;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class InvoiceRequest extends TokenRequest implements InvoiceDomain {
    @NotNull(message = "Id is required!")
    private Long id;

    private LocalDate invoiceDate;

    private LocalDate dueDate;

    private BigDecimal totalAmount;

    private BigDecimal amountPaid;

    private BigDecimal outstandingAmount;

    @Size(max = 9, message = "Payment Status cannot be longer than 9 characters!")
    private String paymentStatus;

    @Size(max = 14, message = "Payment Method cannot be longer than 14 characters!")
    private String paymentMethod;

    private String notes;

    @NotNull(message = "Appointment id is required!")
    private Long appointmentId;
}
