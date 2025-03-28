package com.jakubolejarczyk.vet_server.service.store;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Data
@ToString
public class StepStore {
    Boolean finish = false;
    Boolean success = true;
    List<String> messages = new ArrayList<>();
    Map<String, Object> data = new HashMap<>();
    Map<String, Object> metadata = new HashMap<>();
    Map<String, Object> items = new HashMap<>();

    public void reset() {
        finish = false;
        success = true;
        messages.clear();
        data.clear();
        metadata.clear();
        items.clear();
    }

    public void addMessage(String message) {
        messages.addLast(message);
    }

    public void setData(String key, Object value) {
        data.put(key, value);
    }

    public void setMetadata(String key, Object value) {
        metadata.put(key, value);
    }

    public void setItem(String key, Object value) {
        items.put(key, value);
    }

    public Object getItem(String key) {
        if (items.containsKey(key)) {
            return items.get(key);
        }
        throw new Error("The store does not contain key: " + key);
    }
}
