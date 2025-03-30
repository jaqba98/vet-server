package com.jakubolejarczyk.vet_server.step.base;

import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public abstract class BaseController {
    private final ObjectFactory<StepStore> stepStoreObjectFactory;
    private final ObjectFactory<HandleValidationService> handleValidationService;

    public void initController(String[] dataKeys, String[] metadataKeys) {
        val stepStore = stepStoreObjectFactory.getObject();
        stepStore.reset();
        stepStore.setDataKeys(dataKeys);
        stepStore.setMetadataKeys(metadataKeys);
    }

    protected ResponseEntity<Response<?, ?>> runController(ArrayList<StepModel> steps) {
        val stepStore = stepStoreObjectFactory.getObject();
        for (val step : steps) {
            step.runStep(stepStore);
            val success = stepStore.getSuccess();
            if (!success) break;
        }
        val success = stepStore.getSuccess();
        val messages = stepStore.getMessages();
        val data = stepStore.getData();
        val metadata = stepStore.getMetadata();
        val response = new Response<>(success, messages, data, metadata);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    protected StepStore getStepStore() {
        return stepStoreObjectFactory.getObject();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<Null, Null>> handleValidation(MethodArgumentNotValidException ex) {
        val stepStore = stepStoreObjectFactory.getObject();
        return handleValidationService.getObject().handle(stepStore, ex);
    }
}
