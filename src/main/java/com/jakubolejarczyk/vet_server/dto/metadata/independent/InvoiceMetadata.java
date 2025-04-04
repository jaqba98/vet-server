package com.jakubolejarczyk.vet_server.dto.metadata.independent;

import com.jakubolejarczyk.vet_server.dto.base.BaseMetadata;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class InvoiceMetadata {
    private BaseMetadata id;

    private BaseMetadata invoiceDate;

    private BaseMetadata dueDate;

    private BaseMetadata totalAmount;

    private BaseMetadata amountPaid;

    private BaseMetadata outstandingAmount;

    private BaseMetadata paymentStatus;

    private BaseMetadata paymentMethod;

    private BaseMetadata notes;
}
