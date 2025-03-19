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

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateAccountRole {
    private final AccountService accountService;
    private final VetService vetService;
    private final ClientService clientService;
    private final OpeningHoursService openingHoursService;

    public void updateRole(Account account, String role) {
        if (role.equals("vet")) {
            removeClient(account);
            createVet(account);
            accountService.updateRoleByEmail(account.getEmail(), role);
        }
        else if (role.equals("client")) {
            removeVet(account);
            createClient(account);
            accountService.updateRoleByEmail(account.getEmail(), role);
        }
    }

    private void createVet(Account account) {
        val accountId = account.getId();
        val vetByAccountId = vetService.findByAccountId(accountId);
        if (vetByAccountId.isEmpty()) {
            OpeningHours openingHours = OpeningHours.builder().build();
            Vet vet = Vet.builder()
                .accountId(accountId)
                .openingHoursId(openingHours.getId())
                .build();
            openingHoursService.create(openingHours);
            vetService.create(vet);
        }
    }

    private void createClient(Account account) {
        val accountId = account.getId();
        val clientByAccountId = clientService.findByAccountId(accountId);
        if (clientByAccountId.isEmpty()) {
            Client client = Client.builder()
                    .accountId(account.getId())
                    .build();
            clientService.create(client);
        }
    }

    private void removeVet(Account account) {
        Optional<Vet> vet = vetService.findByAccountId(account.getId());
        if (vet.isPresent()) {
            val vetToRemove = vet.get();
            openingHoursService.delete(vetToRemove.getOpeningHoursId());
            vetService.delete(vetToRemove.getId());
        }
    }

    private void removeClient(Account account) {
        Optional<Client> client = clientService.findByAccountId(account.getId());
        if (client.isPresent()) {
            val clientToRemove = client.get();
            clientService.delete(clientToRemove.getId());
        }
    }
}
