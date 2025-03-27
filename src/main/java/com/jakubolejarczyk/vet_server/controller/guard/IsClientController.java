//package com.jakubolejarczyk.vet_server.controller.guard;
//
//import com.jakubolejarczyk.vet_server.dto.request.guard.GuardRequestDto;
//import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
//import com.jakubolejarczyk.vet_server.service.security.HandleValidationService;
//import com.jakubolejarczyk.vet_server.service.step.get.GetAccountByTokenStep;
//import com.jakubolejarczyk.vet_server.service.step_old.ResponseStep;
//import jakarta.validation.Valid;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.beans.factory.ObjectFactory;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/v1")
//@AllArgsConstructor
//public class IsClientController {
//    private final ObjectFactory<ResponseStep> responseStep;
//    private final ObjectFactory<HandleValidationService> handleValidationService;
//    private final GetAccountByTokenStep getAccountByTokenStep;
//
//    @PostMapping("is-client")
//    public ResponseEntity<ResponseDto> isClient(@Valid @RequestBody GuardRequestDto requestDto) {
//        // Clean response
//        responseStep.getObject().getRidOfMessages();
//        // Get account by token
//        val token = requestDto.getToken();
//        val account = getAccountByTokenStep.runStep(responseStep.getObject(), token);
//        if (account.getError()) {
//            responseStep.getObject().addMessage("You are not a client!");
//            return responseStep.getObject().getStep(false);
//        }
//        val accountData = account.getData();
//        // Check if the account has a client role
//        val role = accountData.getRole();
//        val clientRole = role != null && role.contains("client");
//        // Response
//        return responseStep.getObject().getStep(clientRole);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ResponseDto> handleValidation(MethodArgumentNotValidException ex) {
//        return handleValidationService.getObject().handle(ex);
//    }
//}
