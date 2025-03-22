package com.jakubolejarczyk.vet_server.service.step;

import com.jakubolejarczyk.vet_server.dto.response.ResponseDataDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class ResponseStep {
    private final ArrayList<String> messages = new ArrayList<>();

    public ResponseEntity<ResponseDto> getStep(Boolean success) {
        val responseDto = new ResponseDto(success, messages);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    public <TData> ResponseEntity<ResponseDataDto<TData>> getStep(Boolean success, TData data) {
        val responseDto = new ResponseDataDto<>(success, messages, data);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    public void addMessage(String message) {
        messages.add(message);
    }

    public void getRidOfMessages() {
        messages.clear();
    }
}
