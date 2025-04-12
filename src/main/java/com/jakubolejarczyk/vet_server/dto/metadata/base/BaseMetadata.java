package com.jakubolejarczyk.vet_server.dto.metadata.base;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class BaseMetadata {
    private Map<Long, String> values = new HashMap<>();

    public void addValue(Long key, String value) {
        values.put(key, value);
    }
}
