package com.jakubolejarczyk.vet_server.service.store;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Data
@ToString
public class StepStore {
    Boolean finish = false;
    Boolean success = true;
    List<String> messages = new ArrayList<>();
    Map<String, Object> data = new HashMap<>();
    Map<String, Object> metadata = new HashMap<>();
    Map<String, Object> item = new HashMap<>();
    String[] dataKeys = {};
    String[] metadataKeys = {};

    public void reset() {
        finish = false;
        success = true;
        messages.clear();
        data.clear();
        metadata.clear();
        item.clear();
    }

    public void addMessage(String message) {
        messages.addLast(message);
    }

    public void setItem(String key, Object value) {
        if (Arrays.asList(dataKeys).contains(key)) {
            data.put(key, value);
            return;
        }
        if (Arrays.asList(metadataKeys).contains(key)) {
            metadata.put(key, value);
            return;
        }
        item.put(key, value);
    }

    public Object getItem(String key) {
        if (Arrays.asList(dataKeys).contains(key) && data.containsKey(key)) {
            return data.get(key);
        }
        if (Arrays.asList(metadataKeys).contains(key) && metadata.containsKey(key)) {
            return metadata.get(key);
        }
        if (item.containsKey(key)) {
            return item.get(key);
        }
        throw new Error("The store does not contain key: " + key);
    }
}
