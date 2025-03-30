package com.jakubolejarczyk.vet_server.step.set;

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
        if (stepStore.hasNotItem("email")) throw new Error("The email is required!");
        if (stepStore.hasNotItem("roleToSet")) throw new Error("The roleToSet is required!");
        if (stepStore.hasNotItem("id")) throw new Error("The id is required!");
        val email = (String) stepStore.getItem("email");
        val roleToSet = (String) stepStore.getItem("roleToSet");
        val id = (Long) stepStore.getItem("id");
        if (roleToSet.equals("vet")) {
            createVet(id);
        }
        else if (roleToSet.equals("client")) {
            createClient(id);
        }
        accountService.updateRoleByEmail(email, roleToSet);
    }

    private void createVet(Long id) {
        val vet = vetService.findByAccountId(id);
        if (vet.isPresent()) {
            return;
        }
        OpeningHours openingHours = OpeningHours.builder()
                .isArchived(false)
                .build();
        OpeningHours newOpeningHours = openingHoursService.create(openingHours);
        Vet newVet = Vet.builder()
                .isArchived(false)
                .accountId(id)
                .openingHoursId(newOpeningHours.getId())
                .build();
        vetService.create(newVet);
    }

    private void createClient(Long id) {
        val client = clientService.findByAccountId(id);
        if (client.isPresent()) {
            return;
        }
        Client newClient = Client.builder()
                .isArchived(false)
                .accountId(id)
                .build();
        clientService.create(newClient);
    }
}
