package com.jakubolejarczyk.vet_server.service.response;

import com.jakubolejarczyk.vet_server.dto.response.ResponseDto;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class ResponseService<TData, TMetaData> {
    private final ArrayList<String> messages = new ArrayList<>();

    public ResponseEntity<ResponseDto<TData, TMetaData>> getResponse(Boolean success, TData data, TMetaData metaData) {
        val body = ResponseDto.<TData, TMetaData>builder()
                .success(success)
                .messages(messages)
                .data(data)
                .metaData(metaData)
                .build();
        val status = success ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(body);
    }

    public void addMessage(String message) {
        messages.addLast(message);
    }

    public void cleanUp() {
        messages.clear();
    }
}
