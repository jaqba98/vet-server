package com.jakubolejarczyk.vet_server.controller.common;

import com.jakubolejarczyk.vet_server.controller.base.BaseController;
import com.jakubolejarczyk.vet_server.dto.request.common.ChooseRoleRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.service.step.get.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.service.step.set.SetAccountRoleStep;
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
public class ChooseRoleController extends BaseController {
    public ChooseRoleController(
            ObjectFactory<StepStore> stepStore,
            ObjectFactory<HandleValidationService> handleValidationService,
            GetAccountByTokenStep getAccountByTokenStep,
            SetAccountRoleStep setAccountRoleStep
    ) {
        super(
                stepStore,
                handleValidationService,
                new String[]{"token"},
                new String[]{},
                new ArrayList<>()
        );
        steps.addLast(getAccountByTokenStep);
        steps.addLast(setAccountRoleStep);
    }

    @PostMapping("choose-role")
    public ResponseEntity<Response<?, ?>> runController(@Valid @RequestBody ChooseRoleRequest requestDto) {
        initController();
        stepStore.getObject().setItem("token", requestDto.getToken());
        stepStore.getObject().setItem("setRole", requestDto.getRole());
        return runController(steps);
    }
}

