package com.jakubolejarczyk.vet_server.dto.request.invoice;

import com.jakubolejarczyk.vet_server.domain.independent.InvoiceDomain;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
public class InvoiceRequest extends TokenRequest implements InvoiceDomain {
    private Long id;

    @NotNull(message = "Is archived is required!")
    private Boolean isArchived;

    @NotNull(message = "Invoice Date is required!")
    private Date invoiceDate;

    @NotNull(message = "Due Date is required!")
    private Date dueDate;

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
    @NotBlank(message = "Notes cannot be empty!")
    @Size(max = 255, message = "Notes cannot be longer than 255 characters!")
    private String notes;
}
