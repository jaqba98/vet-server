package com.jakubolejarczyk.vet_server.controller.common;

import com.jakubolejarczyk.vet_server.controller.base.BaseController;
import com.jakubolejarczyk.vet_server.dto.request.common.LoginRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
import com.jakubolejarczyk.vet_server.dto.response.data.common.LoginData;
import com.jakubolejarczyk.vet_server.service.input.get.GetTokenByLoginDetailsInput;
import com.jakubolejarczyk.vet_server.service.response.ResponseService;
import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.service.step.get.GetTokenByLoginDetailsStep;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lombok.val;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class LoginController extends BaseController<LoginRequest, LoginData, Null> {
    private final GetTokenByLoginDetailsStep getTokenByLoginDetailsStep;

    protected LoginController(
            ObjectFactory<HandleValidationService> handleValidationService,
            ObjectFactory<ResponseService<LoginData, Null>> responseService,
            GetTokenByLoginDetailsStep getTokenByLoginDetailsStep
    ) {
        super(handleValidationService, responseService);
        this.getTokenByLoginDetailsStep = getTokenByLoginDetailsStep;
    }

    @Override
    @PostMapping("login")
    public ResponseEntity<Response<LoginData, Null>>
    runController(@Valid @RequestBody LoginRequest requestDto) {
        responseService.getObject().cleanUp();
        val getTokenByLoginDetailsResponse = getTokenByLoginDetailsStep.runStep(new GetTokenByLoginDetailsInput(
                requestDto.getEmail(),
                requestDto.getPassword()
        ));
        val success = getTokenByLoginDetailsResponse.getSuccess();
        val message = getTokenByLoginDetailsResponse.getMessage();
        val token = getTokenByLoginDetailsResponse.getData();
        val data = new LoginData(token);
        responseService.getObject().addMessage(message);
        return responseService.getObject().getResponse(success, data, null);
    }
}
