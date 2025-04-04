package com.jakubolejarczyk.vet_server.step_runner;

import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
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

import java.util.List;

@Service
@AllArgsConstructor
public class StepRunnerController<TData, TMetadata> {
    private final ObjectFactory<StepStore<TData, TMetadata>> stepStoreObjectFactory;
    private final ObjectFactory<HandleValidationService> handleValidationService;

    public void initController() {
        val stepStore = stepStoreObjectFactory.getObject();
        stepStore.reset();
    }

    protected ResponseEntity<Response<TData, TMetadata>> runController(List<StepRunnerModel<TData, TMetadata>> steps) {
        val stepStore = stepStoreObjectFactory.getObject();
        for (val step : steps) {
            step.runStep(stepStore);
            if (!stepStore.getSuccess()) break;
        }
        val success = stepStore.getSuccess();
        val messages = stepStore.getMessages();
        val data = stepStore.getData();
        val metadata = stepStore.getMetadata();
        val response = new Response<>(success, messages, data, metadata);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    protected StepStore<TData, TMetadata> getStepStore() {
        return stepStoreObjectFactory.getObject();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<Null, Null>> handleValidation(MethodArgumentNotValidException ex) {
        val stepStore = stepStoreObjectFactory.getObject();
        return handleValidationService.getObject().handle(stepStore, ex);
    }
}
