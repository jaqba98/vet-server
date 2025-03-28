package com.jakubolejarczyk.vet_server.controller.common;

import com.jakubolejarczyk.vet_server.controller.base.BaseController;
import com.jakubolejarczyk.vet_server.dto.request.common.RegistrationRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.service.input.create.CreateAccountInput;
import com.jakubolejarczyk.vet_server.service.response.ResponseService;
import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.service.step.create.CreateAccountStep;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lombok.val;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class RegistrationController extends BaseController<RegistrationRequest, Null, Null> {
    private final CreateAccountStep createAccountStep;

    protected RegistrationController(
            ObjectFactory<HandleValidationService> handleValidationService,
            ObjectFactory<ResponseService<Null, Null>> responseService,
            CreateAccountStep createAccountStep
    ) {
        super(handleValidationService, responseService);
        this.createAccountStep = createAccountStep;
    }

    @Override
    @PostMapping("registration")
    public ResponseEntity<Response<Null, Null>>
    runController(@Valid @RequestBody RegistrationRequest requestDto) {
        responseService.getObject().cleanUp();
        val createAccountResponse = createAccountStep.runStep(new CreateAccountInput(
                requestDto.getEmail(),
                requestDto.getPassword(),
                requestDto.getFirstName(),
                requestDto.getLastName()
        ));
        val success = createAccountResponse.getSuccess();
        val message = createAccountResponse.getMessage();
        responseService.getObject().addMessage(message);
        return responseService.getObject().getResponse(success, null, null);
    }
}

