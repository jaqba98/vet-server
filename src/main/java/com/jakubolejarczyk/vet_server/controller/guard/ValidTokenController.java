package com.jakubolejarczyk.vet_server.controller.guard;

import com.jakubolejarczyk.vet_server.controller.base.BaseController;
import com.jakubolejarczyk.vet_server.dto.data.guard.ValidTokenDataDto;
import com.jakubolejarczyk.vet_server.dto.metadata.guard.ValidTokenMetadataDto;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequestDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import com.jakubolejarczyk.vet_server.service.response.ResponseService;
import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
import com.jakubolejarczyk.vet_server.service.step.check.CheckTokenStep;
import jakarta.validation.Valid;
import lombok.val;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ValidTokenController extends BaseController<TokenRequestDto, ValidTokenDataDto, ValidTokenMetadataDto> {
    private final CheckTokenStep checkTokenStep;

    protected ValidTokenController(
            ObjectFactory<HandleValidationService> handleValidationService,
            ObjectFactory<ResponseService<ValidTokenDataDto, ValidTokenMetadataDto>> responseService,
            CheckTokenStep checkTokenStep
    ) {
        super(handleValidationService, responseService);
        this.checkTokenStep = checkTokenStep;
    }

    @Override
    @PostMapping("valid-token")
    public ResponseEntity<ResponseDto<ValidTokenDataDto, ValidTokenMetadataDto>>
    runController(@Valid @RequestBody TokenRequestDto requestDto) {
        responseService.getObject().cleanUp();
        val token = requestDto.getToken();
        val checkTokenResponse = checkTokenStep.runStep(token);
        val success = checkTokenResponse.getSuccess();
        val data = new ValidTokenDataDto();
        val metadata = new ValidTokenMetadataDto();
        return responseService.getObject().getResponse(success, data, metadata);
    }
}
