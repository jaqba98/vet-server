package com.jakubolejarczyk.vet_server.service.independent;

import com.jakubolejarczyk.vet_server.model.independent.Invoice;
import com.jakubolejarczyk.vet_server.repository.independent.InvoiceRepository;
import com.jakubolejarczyk.vet_server.service.base.BaseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService extends BaseService<Invoice, InvoiceRepository> {
    public InvoiceService(@Qualifier("invoiceRepository") InvoiceRepository repository) {
        super(repository);
    }
}
