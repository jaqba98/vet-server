package com.jakubolejarczyk.vet_server.service.step;

import com.jakubolejarczyk.vet_server.dto.response.ResponseDataDto;
import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class ResponseStep<TData> {
    private final ArrayList<String> messages = new ArrayList<>();

    public ResponseEntity<ResponseDto> getStep(Boolean success) {
        val responseDto = new ResponseDto(success, messages);
        return ResponseEntity.ok().body(responseDto);
    }

    public ResponseEntity<ResponseDataDto<TData>> getStep(Boolean success, TData data) {
        val responseDto = new ResponseDataDto<TData>(success, messages, data);
        return ResponseEntity.ok().body(responseDto);
    }

    public void addMessage(String message) {
        messages.add(message);
    }
}
