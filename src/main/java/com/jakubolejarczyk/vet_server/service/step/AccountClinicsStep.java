package com.jakubolejarczyk.vet_server.service.step;

import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.service.crud.dependent.ClinicService;
import com.jakubolejarczyk.vet_server.service.crud.dependent.EmploymentService;
import com.jakubolejarczyk.vet_server.service.model.StepResponse;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountClinicsStep {
    private final EmploymentService employmentService;
    private final ClinicService clinicService;

    public StepResponse<ArrayList<Clinic>> runStep(Account accountData) {
        val accountId = accountData.getId();
        val clinicIds = employmentService.findAllByAccountId(accountId)
                .stream()
                .map(Employment::getClinicId)
                .collect(Collectors.toCollection(ArrayList::new));
        val data = new ArrayList<>(clinicService.findAllByIds(clinicIds));
        return StepResponse.<ArrayList<Clinic>>builder()
                .error(false)
                .data(data)
                .build();
    }
}
