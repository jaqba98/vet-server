package com.jakubolejarczyk.vet_server.dto.request.independent;

import com.jakubolejarczyk.vet_server.domain.independent.InvoiceDomain;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import jakarta.validation.constraints.NotBlank;
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

    @NotNull(message = "Invoice Date is required!")
    private LocalDate invoiceDate;

    @NotNull(message = "Due Date is required!")
    private LocalDate dueDate;

    @NotNull(message = "Total Amount is required!")
    private BigDecimal totalAmount;

    @NotNull(message = "Amount Paid is required!")
    private BigDecimal amountPaid;

    @NotNull(message = "Outstanding Amount is required!")
    private BigDecimal outstandingAmount;

    @NotNull(message = "Payment Status is required!")
    @NotBlank(message = "Payment Status cannot be empty!")
    @Size(max = 9, message = "Payment Status cannot be longer than 9 characters!")
    private String paymentStatus;

    @NotNull(message = "Payment Method is required!")
    @NotBlank(message = "Payment Method cannot be empty!")
    @Size(max = 14, message = "Payment Method cannot be longer than 14 characters!")
    private String paymentMethod;

    @NotNull(message = "Notes is required!")
    private String notes;
}
