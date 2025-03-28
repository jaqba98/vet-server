package com.jakubolejarczyk.vet_server.controller.base;

import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.service.response.ResponseService;
import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.service.store.StepStore;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Service
@AllArgsConstructor
public abstract class BaseController<TRequest, TData, TMetadata> {
    private final ObjectFactory<HandleValidationService> handleValidationService;
    protected final ObjectFactory<ResponseService<TData, TMetadata>> responseService;
    protected final ObjectFactory<StepStore> stepStore;

    public abstract ResponseEntity<Response<TData, TMetadata>> runController(TRequest request);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<Null, Null>> handleValidation(MethodArgumentNotValidException ex) {
        return handleValidationService.getObject().handle(ex);
    }
}
