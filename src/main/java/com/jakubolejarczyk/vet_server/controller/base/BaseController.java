package com.jakubolejarczyk.vet_server.controller.base;

import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.service.model.StepModel;
import com.jakubolejarczyk.vet_server.service.response.ResponseService;
import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.service.store.StepStore;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public abstract class BaseController<TRequest, TData, TMetadata> {
    protected final ObjectFactory<StepStore> stepStore;
    private final ObjectFactory<ResponseService<TData, TMetadata>> responseService;
    private final ObjectFactory<HandleValidationService> handleValidationService;

    public ResponseEntity<Response<TData, TMetadata>> runController(ArrayList<StepModel> steps) {
        responseService.getObject().cleanUp();
        for (val step : steps) {
            val response = step.runStep(stepStore.getObject());
            val success = response.getSuccess();
            val message = response.getMessage();
            if (!success) {
                responseService.getObject().addMessage(message);
                return responseService.getObject().getResponse(false, null, null);
            }
        }
        responseService.getObject().addMessage("Jej");
        return responseService.getObject().getResponse(true, null, null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<Null, Null>> handleValidation(MethodArgumentNotValidException ex) {
        return handleValidationService.getObject().handle(ex);
    }
}
