package com.jakubolejarczyk.vet_server.controller.guard;

import com.jakubolejarczyk.vet_server.controller.base.BaseController;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.service.model.StepModel;
import com.jakubolejarczyk.vet_server.service.response.ResponseService;
import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.service.step.check.CheckTokenStep;
import com.jakubolejarczyk.vet_server.service.store.StepStore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1")
public class ValidTokenController extends BaseController<TokenRequest, Null, Null> {
    private final CheckTokenStep checkTokenStep;

    public ValidTokenController(
            ObjectFactory<StepStore> stepStore,
            ObjectFactory<ResponseService<Null, Null>> responseService,
            ObjectFactory<HandleValidationService> handleValidationService,
            CheckTokenStep checkTokenStep
    ) {
        super(stepStore, responseService, handleValidationService);
        this.checkTokenStep = checkTokenStep;
    }

    @PostMapping("valid-token")
    public ResponseEntity<Response<Null, Null>> runController(@RequestBody TokenRequest requestDto) {
        stepStore.getObject().set("token", "");
        ArrayList<StepModel> steps = new ArrayList<>();
        steps.add(this.checkTokenStep);
        return runController(steps);
    }
}
