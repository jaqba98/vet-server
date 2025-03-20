package com.jakubolejarczyk.vet_server.service.step;

import com.jakubolejarczyk.vet_server.model.dependent.Client;
import com.jakubolejarczyk.vet_server.model.dependent.Vet;
import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.model.independent.OpeningHours;
import com.jakubolejarczyk.vet_server.service.crud.dependent.ClientService;
import com.jakubolejarczyk.vet_server.service.crud.dependent.VetService;
import com.jakubolejarczyk.vet_server.service.crud.independent.AccountService;
import com.jakubolejarczyk.vet_server.service.crud.independent.OpeningHoursService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateAccountRoleStep {
    private final AccountService accountService;
    private final VetService vetService;
    private final ClientService clientService;
    private final OpeningHoursService openingHoursService;

    public void runStep(Account account, String role) {
        val id = account.getId();
        val email = account.getEmail();
        if (role.equals("vet")) {
            createVet(id);
        }
        else if (role.equals("client")) {
            createClient(id);
        }
        accountService.updateRoleByEmail(email, role);
    }

    private void createVet(Long accountId) {
        val oldVet = vetService.findByAccountId(accountId);
        if (oldVet.isPresent()) {
            return;
        }
        OpeningHours openingHours = OpeningHours.builder().build();
        Vet vet = Vet.builder()
                .accountId(accountId)
                .openingHoursId(openingHours.getId())
                .build();
        openingHoursService.create(openingHours);
        vetService.create(vet);
    }

    private void createClient(Long accountId) {
        val oldClient = clientService.findByAccountId(accountId);
        if (oldClient.isPresent()) {
            return;
        }
        Client client = Client.builder()
                .accountId(accountId)
                .build();
        clientService.create(client);
    }
}
