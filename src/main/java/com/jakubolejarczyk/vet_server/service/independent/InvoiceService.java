package com.jakubolejarczyk.vet_server.service.independent;

import com.jakubolejarczyk.vet_server.model.independent.Invoice;
import com.jakubolejarczyk.vet_server.repository.independent.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InvoiceService {
    private final InvoiceRepository repository;

    public Invoice create(Invoice invoice) {
        return repository.save(invoice);
    }

    public List<Invoice> findAllById(List<Long> ids) {
        return repository.findAllById(ids);
    }

    public void updateIsArchived(List<Long> ids, Boolean isArchived) {
        repository.updateIsArchived(ids, isArchived);
    }
}
