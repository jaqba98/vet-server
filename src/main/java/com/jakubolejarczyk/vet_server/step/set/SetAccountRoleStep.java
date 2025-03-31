package com.jakubolejarczyk.vet_server.step.set;

import com.jakubolejarczyk.vet_server.model.dependent.Client;
import com.jakubolejarczyk.vet_server.model.dependent.Vet;
import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.model.independent.OpeningHours;
import com.jakubolejarczyk.vet_server.service.dependent.ClientService;
import com.jakubolejarczyk.vet_server.service.dependent.VetService;
import com.jakubolejarczyk.vet_server.service.independent.AccountService;
import com.jakubolejarczyk.vet_server.service.independent.OpeningHoursService;
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
        if (stepStore.hasNotItem("email")) throw new Error("The email is required!");
        if (stepStore.hasNotItem("roleToSet")) throw new Error("The roleToSet is required!");
        if (stepStore.hasNotItem("account")) throw new Error("The account is required!");
        val email = stepStore.getItem("email", String.class);
        val roleToSet = stepStore.getItem("roleToSet", String.class);
        val account = stepStore.getItem("account", Account.class);
        if (roleToSet.equals("vet")) {
            createVet(account.getId());
        }
        else if (roleToSet.equals("client")) {
            createClient(account.getId());
        }
        accountService.updateRoleByEmail(email, roleToSet);
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
                .isArchived(false)
                .accountId(accountId)
                .openingHoursId(newOpeningHours.getId())
                .build();
        vetService.create(newVet);
    }

    private void createClient(Long accountId) {
        val client = clientService.findByAccountId(accountId);
        if (client.isPresent()) {
            return;
        }
        Client newClient = Client.builder()
                .isArchived(false)
                .accountId(accountId)
                .build();
        clientService.create(newClient);
    }
}
