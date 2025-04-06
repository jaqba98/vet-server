package com.jakubolejarczyk.vet_server.service.dependent;

import com.jakubolejarczyk.vet_server.model.dependent.Invoice;
import com.jakubolejarczyk.vet_server.repository.dependent.InvoiceRepository;
import com.jakubolejarczyk.vet_server.service.base.BaseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService extends BaseService<Invoice, InvoiceRepository> {
    public InvoiceService(@Qualifier("invoiceRepository") InvoiceRepository repository) {
        super(repository);
    }

    public List<Invoice> findAllByAppointmentIdIn(List<Long> appointmentId) {
        return repository.findAllByAppointmentIdIn(appointmentId);
    }
}
