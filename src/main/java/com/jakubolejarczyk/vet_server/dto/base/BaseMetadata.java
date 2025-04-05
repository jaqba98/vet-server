package com.jakubolejarczyk.vet_server.dto.base;

import lombok.Data;

import java.util.Map;

@Data
public class BaseMetadata {
    private Map<Long, String> values;

    public void addValue(Long key, String value) {
        values.put(key, value);
    }
}
