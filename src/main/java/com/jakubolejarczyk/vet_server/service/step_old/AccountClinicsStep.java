package com.jakubolejarczyk.vet_server.service.step_old;

import com.jakubolejarczyk.vet_server.model.dependent.Clinic;
import com.jakubolejarczyk.vet_server.model.independent.Account;
import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.service.crud.dependent.ClinicService;
import com.jakubolejarczyk.vet_server.service.crud.dependent.EmploymentService;
import com.jakubolejarczyk.vet_server.service.model.StepModel;
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

    public StepModel<ArrayList<Clinic>> runStep(Account accountData) {
        val accountId = accountData.getId();
        val clinicIds = employmentService.findAllByAccountId(accountId)
                .stream()
                .map(Employment::getClinicId)
                .collect(Collectors.toCollection(ArrayList::new));
        val data = new ArrayList<>(clinicService.findAllByIds(clinicIds));
        return StepModel.<ArrayList<Clinic>>builder()
                .error(false)
                .data(data)
                .build();
    }
}
