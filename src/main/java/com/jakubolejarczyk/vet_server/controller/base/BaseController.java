package com.jakubolejarczyk.vet_server.controller.base;

import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.service.model.StepModel;
import com.jakubolejarczyk.vet_server.service.store.StepStore;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public abstract class BaseController {
    public final ObjectFactory<StepStore> stepStore;

    public ResponseEntity<Response<?, ?>> runController(ArrayList<StepModel> steps) {
        for (val step : steps) {
            step.runStep(stepStore.getObject());
            if (stepStore.getObject().getFinish()) break;
        }
        val success = stepStore.getObject().getSuccess();
        val messages = stepStore.getObject().getMessages();
        val data = stepStore.getObject().getData();
        val metadata = stepStore.getObject().getMetadata();
        val response = new Response<>(success, messages, data, metadata);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
