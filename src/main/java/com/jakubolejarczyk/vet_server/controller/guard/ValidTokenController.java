package com.jakubolejarczyk.vet_server.controller.guard;

import com.jakubolejarczyk.vet_server.controller.base.BaseController;
import com.jakubolejarczyk.vet_server.dto.data.base.NullDate;
import com.jakubolejarczyk.vet_server.dto.metadata.base.NullMetadata;
import com.jakubolejarczyk.vet_server.dto.request.base.TokenRequest;
import com.jakubolejarczyk.vet_server.dto.response.Response;
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
public class ValidTokenController extends BaseController<TokenRequest, NullDate, NullMetadata> {
    private final CheckTokenStep checkTokenStep;

    public ValidTokenController(
            ObjectFactory<HandleValidationService> handleValidationService,
            ObjectFactory<ResponseService<NullDate, NullMetadata>> responseService,
            CheckTokenStep checkTokenStep
    ) {
        super(handleValidationService, responseService);
        this.checkTokenStep = checkTokenStep;
    }

    @Override
    @PostMapping("valid-token")
    public ResponseEntity<Response<NullDate, NullMetadata>>
    runController(@Valid @RequestBody TokenRequest requestDto) {
        val response = responseService.getObject();
        response.cleanUp();
        val checkTokenResponse = checkTokenStep.runStep(requestDto.getToken());
        val success = checkTokenResponse.getSuccess();
        val data = new NullDate();
        val metadata = new NullMetadata();
        return response.getResponse(success, data, metadata);
    }
}
