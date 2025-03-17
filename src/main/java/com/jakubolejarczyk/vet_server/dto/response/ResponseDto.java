package com.jakubolejarczyk.vet_server.dto.response;

import lombok.AllArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
public class ResponseDto<TData> {
    private Boolean success;
    private ArrayList<String> errors;
    private TData data;
}
