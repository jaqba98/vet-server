//package com.jakubolejarczyk.vet_server.service.security;
//
//import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
//import com.jakubolejarczyk.vet_server.service.step_old.ResponseStep;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.beans.factory.ObjectFactory;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//
//@Service
//@AllArgsConstructor
//public class HandleValidationService {
//    private final ObjectFactory<ResponseStep> responseStep;
//
//    public ResponseEntity<ResponseDto> handle(MethodArgumentNotValidException ex) {
//        responseStep.getObject().getRidOfMessages();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            val message = error.getDefaultMessage();
//            responseStep.getObject().addMessage(message);
//        });
//        return responseStep.getObject().getStep(false);
//    }
//}
