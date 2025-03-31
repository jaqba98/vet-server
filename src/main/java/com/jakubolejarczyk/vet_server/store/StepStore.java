package com.jakubolejarczyk.vet_server.store;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Data
public class StepStore {
    private Boolean success = true;
    private List<String> messages = new ArrayList<>();
    private Map<String, Object> data = new HashMap<>();
    private Map<String, Object> metadata = new HashMap<>();
    private Map<String, Object> items = new HashMap<>();
    private String[] dataKeys = {};
    private String[] metadataKeys = {};

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

    public void setItem(String key, Object value) {
        if (Arrays.asList(dataKeys).contains(key)) {
            data.put(key, value);
        } else if (Arrays.asList(metadataKeys).contains(key)) {
            metadata.put(key, value);
        } else {
            items.put(key, value);
        }
    }

    public <T> T getItem(String key, Class<T> type) {
        Object item;
        if (data.containsKey(key)) {
            item = data.get(key);
        } else if (metadata.containsKey(key)) {
            item = metadata.get(key);
        } else if (items.containsKey(key)) {
            item = items.get(key);
        } else {
            throw new NoSuchElementException("The store does not contain key: " + key);
        }
        if (type.isInstance(item)) {
            return type.cast(item);
        }
        throw new ClassCastException("The item is not of the expected type: " + type.getName() + " it should be: "  + item.getClass().getName());
    }

    public <T> List<T> getItemAsArray(String key, Class<T> type) {
        Object value = getItem(key, Object.class);
        if (value instanceof Object[] array) {
            return Arrays.stream(array)
                    .map(type::cast)
                    .toList();
        }
        else if (value instanceof List<?> list) {
            List<T> result = new ArrayList<>();
            for (Object item : list) {
                result.add(type.cast(item));
            }
            return result;
        }
        else {
            return List.of(type.cast(value));
        }
    }

    public Boolean hasItem(String key) {
        return data.containsKey(key) || metadata.containsKey(key) || items.containsKey(key);
    }

    public Boolean hasNotItem(String key) {
        return !hasItem(key);
    }
}
