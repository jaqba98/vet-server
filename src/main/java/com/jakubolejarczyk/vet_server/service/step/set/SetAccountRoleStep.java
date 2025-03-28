package com.jakubolejarczyk.vet_server.service.step.set;

import com.jakubolejarczyk.vet_server.model.dependent.Client;
import com.jakubolejarczyk.vet_server.model.dependent.Vet;
import com.jakubolejarczyk.vet_server.model.independent.OpeningHours;
import com.jakubolejarczyk.vet_server.service.crud.dependent.ClientService;
import com.jakubolejarczyk.vet_server.service.crud.dependent.VetService;
import com.jakubolejarczyk.vet_server.service.crud.independent.AccountService;
import com.jakubolejarczyk.vet_server.service.crud.independent.OpeningHoursService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SetAccountRoleStep implements StepModel {
    private final AccountService accountService;
    private final VetService vetService;
    private final ClientService clientService;
    private final OpeningHoursService openingHoursService;

    @Override
    public void runStep(StepStore stepStore) {
        val accountId = (Long) stepStore.getItem("accountId");
        val email = (String) stepStore.getItem("email");
        val role = (String) stepStore.getItem("setRole");
        if (role.equals("vet")) {
            createVet(accountId);
        }
        else if (role.equals("client")) {
            createClient(accountId);
        }
        accountService.updateRoleByEmail(email, role);
    }

    private void createVet(Long accountId) {
        val vet = vetService.findByAccountId(accountId);
        if (vet.isPresent()) {
            return;
        }
        OpeningHours openingHours = OpeningHours.builder()
                .isArchived(false)
                .build();
        OpeningHours newOpeningHours = openingHoursService.create(openingHours);
        Vet newVet = Vet.builder()
                .accountId(accountId)
                .openingHoursId(newOpeningHours.getId())
                .isArchived(false)
                .build();
        vetService.create(newVet);
    }

    private void createClient(Long accountId) {
        val client = clientService.findByAccountId(accountId);
        if (client.isPresent()) {
            return;
        }
        Client newClient = com.jakubolejarczyk.vet_server.model.dependent.Client.builder()
                .accountId(accountId)
                .isArchived(false)
                .build();
        clientService.create(newClient);
    }
}
