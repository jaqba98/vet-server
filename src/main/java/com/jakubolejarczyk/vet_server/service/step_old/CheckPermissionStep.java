
package com.jakubolejarczyk.vet_server.service.step_old;

import com.jakubolejarczyk.vet_server.service.model.StepModel;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class CheckPermissionStep {
    public StepModel<Boolean> runStep(ResponseStep responseStep, ArrayList<Long> ids, Long id) {
        try {
            val firstId = ids.stream().filter(currId -> currId.equals(id)).findFirst();
            if (firstId.isPresent()) {
                return StepModel.<Boolean>builder()
                        .error(false)
                        .data(true)
                        .build();
            }
            responseStep.addMessage("You do not have permission to perform this action!");
            return StepModel.<Boolean>builder()
                    .error(true)
                    .data(false)
                    .build();
        } catch (Exception e) {
            responseStep.addMessage("You do not have permission to perform this action!");
            return StepModel.<Boolean>builder()
                    .error(true)
                    .data(false)
                    .build();
        }
    }
}
