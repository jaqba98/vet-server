package com.jakubolejarczyk.vet_server.service.database;

import com.jakubolejarczyk.vet_server.model.Account;
import com.jakubolejarczyk.vet_server.model.OpeningHours;
import com.jakubolejarczyk.vet_server.model.Vet;
import com.jakubolejarczyk.vet_server.repository.VetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VetService {
    private final VetRepository vetRepository;

    public void create(Account account, OpeningHours openingHours) {
        Vet vet = new Vet();
        vet.setAccountId(account);
        vet.setOpeningHoursId(openingHours);
        vetRepository.save(vet);
    }
}
