package com.jakubolejarczyk.vet_server.step.set;

import com.jakubolejarczyk.vet_server.model.dependent.Vet;
import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.model.independent.OpeningHour;
import com.jakubolejarczyk.vet_server.service.dependent.VetService;
import com.jakubolejarczyk.vet_server.service.independent.AccountService;
import com.jakubolejarczyk.vet_server.service.independent.OpeningHourService;
import com.jakubolejarczyk.vet_server.step_runner.StepRunnerModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SetAccountRoleStep<TData, TMetadata> implements StepRunnerModel<TData, TMetadata> {
    private final AccountService accountService;
    private final VetService vetService;
    private final OpeningHourService openingHourService;

    @Override
    public void runStep(StepStore<TData, TMetadata> stepStore) {
        if (stepStore.hasNotItem("role")) throw new Error("The role is required!");
        if (stepStore.hasNotItem("account")) throw new Error("The account is required!");
        val role = stepStore.getItem("role", String.class);
        val account = stepStore.getItem("account", Account.class);
        if (role.equals("vet")) {
            createVet(account.getId());
        }
        accountService.updateRoleByEmail(account.getEmail(), role);
    }

    private void createVet(Long accountId) {
        val vet = vetService.findByAccountId(accountId);
        if (vet.isPresent()) return;
        OpeningHour openingHour = OpeningHour.builder().build();
        OpeningHour newOpeningHour = openingHourService.save(openingHour);
        Vet newVet = Vet.builder()
            .accountId(accountId)
            .openingHourId(newOpeningHour.getId())
            .build();
        vetService.save(newVet);
    }
}
