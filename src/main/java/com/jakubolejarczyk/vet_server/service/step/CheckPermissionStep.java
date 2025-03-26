
package com.jakubolejarczyk.vet_server.service.step;

import com.jakubolejarczyk.vet_server.service.model.StepResponse;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class CheckPermissionStep {
    public StepResponse<Boolean> runStep(ResponseStep responseStep, ArrayList<Long> ids, Long id) {
        try {
            val firstId = ids.stream().filter(currId -> currId.equals(id)).findFirst();
            if (firstId.isPresent()) {
                return StepResponse.<Boolean>builder()
                        .error(false)
                        .data(true)
                        .build();
            }
            responseStep.addMessage("You do not have permission to perform this action!");
            return StepResponse.<Boolean>builder()
                    .error(true)
                    .data(false)
                    .build();
        } catch (Exception e) {
            responseStep.addMessage("You do not have permission to perform this action!");
            return StepResponse.<Boolean>builder()
                    .error(true)
                    .data(false)
                    .build();
        }
    }
}
