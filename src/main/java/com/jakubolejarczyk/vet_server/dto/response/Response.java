package com.jakubolejarczyk.vet_server.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Response<TData, TMetadata> {
    private Boolean success;
    private List<String> messages;
    private TData data;
    private TMetadata metadata;
}
