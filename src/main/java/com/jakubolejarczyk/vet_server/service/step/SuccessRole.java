package com.jakubolejarczyk.vet_server.service.step;

import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class SuccessRole<TData> {
    public ResponseEntity<ResponseDto<TData>> getSuccessResponse(String message, TData data) {
        val messages = new ArrayList<String>();
        messages.add(message);
        val responseDto = new ResponseDto<>(true, messages, data);
        return ResponseEntity.ok().body(responseDto);
    }
}
