package com.jakubolejarczyk.vet_server.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDto<TData, TMetadata> {
    private Boolean success;
    private ArrayList<String> messages;
    private TData data;
    private TMetadata metadata;
}
