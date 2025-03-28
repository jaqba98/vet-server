package com.jakubolejarczyk.vet_server.service.store;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StepStore {
    Map<String, Object> data = new HashMap<>();

    public void set(String key, Object value) {
        data.put(key, value);
    }

    public Object get(String key) {
        if (data.containsKey(key)) {
            return data.get(key);
        }
        throw new Error("The store does not contain key: " + key);
    }
}
