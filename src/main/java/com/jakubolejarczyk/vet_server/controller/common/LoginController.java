package com.jakubolejarczyk.vet_server.controller.common;

import com.jakubolejarczyk.vet_server.dto.request.common.LoginRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.step.base.BaseController;
import com.jakubolejarczyk.vet_server.step.model.StepModel;
import com.jakubolejarczyk.vet_server.store.StepStore;
import lombok.val;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1")
public class LoginController extends BaseController {
    public LoginController(
            ObjectFactory<StepStore> stepStoreObjectFactory,
            ObjectFactory<HandleValidationService> handleValidationServiceObjectFactory
    ) {
        super(stepStoreObjectFactory, handleValidationServiceObjectFactory);
    }

    @PostMapping("login")
    public ResponseEntity<Response<?, ?>> login(@RequestBody LoginRequest request) {
        val steps = new ArrayList<StepModel>();
        String[] dataKeys = {"token"};
        String[] metadataKeys = {};
        initController(dataKeys, metadataKeys);
        getStepStore().setItem("token", "123");
        return runController(steps);
    }
}
