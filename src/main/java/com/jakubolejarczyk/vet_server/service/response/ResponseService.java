package com.jakubolejarczyk.vet_server.service.response;

import com.jakubolejarczyk.vet_server.dto.response.Response;
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

    public ResponseEntity<Response<TData, TMetaData>> getResponse(Boolean success, TData data, TMetaData metadata) {
        val body = new Response<>(success, messages, data, metadata);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    public void addMessage(String message) {
        messages.addLast(message);
    }

    public void cleanUp() {
        messages.clear();
    }
}
