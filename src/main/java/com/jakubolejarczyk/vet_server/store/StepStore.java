package com.jakubolejarczyk.vet_server.store;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Data
public class StepStore {
    Boolean success = true;
    List<String> messages = new ArrayList<>();
    Map<String, Object> data = new HashMap<>();
    Map<String, Object> metadata = new HashMap<>();
    Map<String, Object> items = new HashMap<>();
    String[] dataKeys = {};
    String[] metadataKeys = {};

    public void reset() {
        success = true;
        messages.clear();
        data.clear();
        metadata.clear();
        items.clear();
    }

    public void addMessage(String message) {
        messages.addFirst(message);
    }

    public void setItem(String key, String value) {
        if (Arrays.asList(dataKeys).contains(key)) {
            data.put(key, value);
        } else if (Arrays.asList(metadataKeys).contains(key)) {
            metadata.put(key, value);
        } else {
            items.put(key, value);
        }
    }

    public Object getItem(String key) {
        if (data.containsKey(key)) {
            return data.get(key);
        } else if (metadata.containsKey(key)) {
            return metadata.get(key);
        } else if (items.containsKey(key)) {
            return items.get(key);
        } else {
            throw new Error("The store does not contain key: " + key);
        }
    }

    public Boolean hasItem(String key) {
        return data.containsKey(key) || metadata.containsKey(key) || items.containsKey(key);
    }

    public Boolean hasNotItem(String key) {
        return !hasItem(key);
    }
}
