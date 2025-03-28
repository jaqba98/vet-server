package com.jakubolejarczyk.vet_server.controller.guard;

import com.jakubolejarczyk.vet_server.controller.base.BaseController;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.service.model.StepModel;
import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.service.step.get.GetAccountByTokenStep;
import com.jakubolejarczyk.vet_server.service.store.StepStore;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1")
public class GetAccountController extends BaseController {
    ArrayList<StepModel> steps = new ArrayList<>();

    public GetAccountController(
            ObjectFactory<StepStore> stepStore,
            ObjectFactory<HandleValidationService> handleValidationService,
            GetAccountByTokenStep getAccountByTokenStep
    ) {
        super(stepStore, handleValidationService);
        steps.add(getAccountByTokenStep);
    }

    @PostMapping("get-account")
    public ResponseEntity<Response<?, ?>> runController(@RequestBody TokenRequest requestDto) {
        stepStore.getObject().reset();
        stepStore.getObject().setItem("token", requestDto.getToken());
        return runController(steps);
    }
}
