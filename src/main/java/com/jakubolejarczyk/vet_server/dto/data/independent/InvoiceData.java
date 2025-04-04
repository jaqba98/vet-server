package com.jakubolejarczyk.vet_server.dto.data.independent;

import com.jakubolejarczyk.vet_server.model.independent.Invoice;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class InvoiceData {
    private List<Invoice> invoices;
}
