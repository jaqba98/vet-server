//package com.jakubolejarczyk.vet_server.controller.common;
//
//import com.jakubolejarczyk.vet_server.controller.base.BaseController;
//import com.jakubolejarczyk.vet_server.dto.data.LoginData;
//import com.jakubolejarczyk.vet_server.dto.request.common.LoginRequest;
//import com.jakubolejarczyk.vet_server.dto.response.Response;
//import com.jakubolejarczyk.vet_server.service.response.ResponseService;
//import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
//import com.jakubolejarczyk.vet_server.service.step.get.GetTokenByLoginDetailsStep;
//import com.jakubolejarczyk.vet_server.service.store.StepStore;
//import jakarta.validation.Valid;
//import jakarta.validation.constraints.Null;
//import lombok.val;
//import org.springframework.beans.factory.ObjectFactory;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/v1")
//public class LoginController extends BaseController<LoginRequest, LoginData, Null> {
//    private final GetTokenByLoginDetailsStep getTokenByLoginDetailsStep;
//
//    public LoginController(
//            ObjectFactory<HandleValidationService> handleValidationService,
//            ObjectFactory<ResponseService<LoginData, Null>> responseService,
//            ObjectFactory<StepStore> stepStore,
//            GetTokenByLoginDetailsStep getTokenByLoginDetailsStep
//    ) {
//        super(handleValidationService, responseService, stepStore);
//        this.getTokenByLoginDetailsStep = getTokenByLoginDetailsStep;
//    }
//
//    @Override
//    @PostMapping("login")
//    public ResponseEntity<Response<LoginData, Null>>
//    runController(@Valid @RequestBody LoginRequest requestDto) {
//        responseService.getObject().cleanUp();
//        stepStore.getObject().set("email", requestDto.getEmail());
//        stepStore.getObject().set("password", requestDto.getPassword());
//        val getTokenByLoginDetailsResponse = getTokenByLoginDetailsStep.runStep(stepStore.getObject());
//        val success = getTokenByLoginDetailsResponse.getSuccess();
//        val message = getTokenByLoginDetailsResponse.getMessage();
//        val token = (String) stepStore.getObject().get("token");
//        val data = new LoginData(token);
//        responseService.getObject().addMessage(message);
//        return responseService.getObject().getResponse(success, data, null);
//    }
//}
