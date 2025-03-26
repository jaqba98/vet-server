package com.jakubolejarczyk.vet_server.service.step_old;

import com.jakubolejarczyk.vet_server.model.dependent.Employment;
import com.jakubolejarczyk.vet_server.service.crud.dependent.EmploymentService;
import com.jakubolejarczyk.vet_server.service.model.StepModel;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GetEmploymentByIdsStep {
    private final EmploymentService employmentService;

    public StepModel<ArrayList<Employment>> runStep(ResponseStep responseStep, List<Long> ids) {
        val employment = new ArrayList<>(employmentService.findAllByIds(ids));
        return StepModel.<ArrayList<Employment>>builder()
                .error(false)
                .data(employment)
                .build();
    }
}
