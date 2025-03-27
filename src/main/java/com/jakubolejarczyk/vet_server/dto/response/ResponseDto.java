package com.jakubolejarczyk.vet_server.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@Getter
@Setter
@SuperBuilder
public class ResponseDto<TData, TMetaData> {
    private Boolean success;
    private ArrayList<String> messages;
    private TData data;
    private TMetaData metaData;
}
