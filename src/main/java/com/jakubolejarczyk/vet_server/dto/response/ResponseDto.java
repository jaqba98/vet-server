package com.jakubolejarczyk.vet_server.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class ResponseDto<TData, TMetadata> {
    private Boolean success;
    private ArrayList<String> messages;
    private TData data;
    private TMetadata metadata;
}
