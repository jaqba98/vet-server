package com.jakubolejarczyk.vet_server.controller.common;

import com.jakubolejarczyk.vet_server.controller.base.BaseController;
import com.jakubolejarczyk.vet_server.dto.request.common.LoginRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.service.step.get.GetTokenByLoginDetailsStep;
import com.jakubolejarczyk.vet_server.service.store.StepStore;
import jakarta.validation.Valid;
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
            ObjectFactory<StepStore> stepStore,
            ObjectFactory<HandleValidationService> handleValidationService,
            GetTokenByLoginDetailsStep getTokenByLoginDetailsStep
    ) {
        super(
                stepStore,
                handleValidationService,
                new String[]{"token"},
                new String[]{},
                new ArrayList<>()
        );
        steps.add(getTokenByLoginDetailsStep);
    }

    @PostMapping("login")
    public ResponseEntity<Response<?, ?>> runController(@Valid @RequestBody LoginRequest requestDto) {
        initController();
        stepStore.getObject().setItem("email", requestDto.getEmail());
        stepStore.getObject().setItem("password", requestDto.getPassword());
        return runController(steps);
    }
}
