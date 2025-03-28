package com.jakubolejarczyk.vet_server.controller.guard;

import com.jakubolejarczyk.vet_server.controller.base.BaseController;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.service.response.ResponseService;
import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.service.step.check.CheckTokenStep;
import com.jakubolejarczyk.vet_server.service.store.StepStore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lombok.val;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ValidTokenController extends BaseController<TokenRequest, Null, Null> {
    private final CheckTokenStep checkTokenStep;

    public ValidTokenController(
            ObjectFactory<HandleValidationService> handleValidationService,
            ObjectFactory<ResponseService<Null, Null>> responseService,
            ObjectFactory<StepStore> stepStore,
            CheckTokenStep checkTokenStep
    ) {
        super(handleValidationService, responseService, stepStore);
        this.checkTokenStep = checkTokenStep;
    }

    @Override
    @PostMapping("valid-token")
    public ResponseEntity<Response<Null, Null>> runController(@Valid @RequestBody TokenRequest requestDto) {
        val response = responseService.getObject();
        response.cleanUp();
        stepStore.getObject().set("token", requestDto.getToken());
        val checkTokenResponse = checkTokenStep.runStep(stepStore.getObject());
        val success = checkTokenResponse.getSuccess();
        val message = checkTokenResponse.getMessage();
        response.addMessage(message);
        return response.getResponse(success, null, null);
    }
}
