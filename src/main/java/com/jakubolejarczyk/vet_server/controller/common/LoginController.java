package com.jakubolejarczyk.vet_server.controller.common;

import com.jakubolejarczyk.vet_server.controller.base.BaseController;
import com.jakubolejarczyk.vet_server.dto.data.common.LoginDataDto;
import com.jakubolejarczyk.vet_server.dto.metadata.common.LoginMetadataDto;
import com.jakubolejarczyk.vet_server.dto.request.controller.common.LoginRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import com.jakubolejarczyk.vet_server.service.input.get.GetTokenByLoginDetailsInput;
import com.jakubolejarczyk.vet_server.service.response.ResponseService;
import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.service.step.get.GetTokenByLoginDetailsStep;
import jakarta.validation.Valid;
import lombok.val;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class LoginController extends BaseController<LoginRequestDto, LoginDataDto, LoginMetadataDto> {
    private final GetTokenByLoginDetailsStep getTokenByLoginDetailsStep;

    protected LoginController(
            ObjectFactory<HandleValidationService> handleValidationService,
            ObjectFactory<ResponseService<LoginDataDto, LoginMetadataDto>> responseService,
            GetTokenByLoginDetailsStep getTokenByLoginDetailsStep
    ) {
        super(handleValidationService, responseService);
        this.getTokenByLoginDetailsStep = getTokenByLoginDetailsStep;
    }

    @Override
    @PostMapping("login")
    public ResponseEntity<ResponseDto<LoginDataDto, LoginMetadataDto>>
    runController(@Valid @RequestBody LoginRequestDto requestDto) {
        responseService.getObject().cleanUp();
        val email = requestDto.getEmail();
        val password = requestDto.getPassword();
        val getTokenByLoginDetailsResult = getTokenByLoginDetailsStep.runStep(
                new GetTokenByLoginDetailsInput(email, password)
        );
        val success = getTokenByLoginDetailsResult.getSuccess();
        val token = getTokenByLoginDetailsResult.getData();
        val data = new LoginDataDto(token);
        val metadata = new LoginMetadataDto();
        return responseService.getObject().getResponse(success, data, metadata);
    }
}
