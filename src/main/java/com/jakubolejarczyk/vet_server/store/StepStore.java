package com.jakubolejarczyk.vet_server.store;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Getter
@Setter
public class StepStore<TData, TMetadata> {
    private Boolean success = true;
    private List<String> messages = new ArrayList<>();
    private Map<String, Object> items = new HashMap<>();
    private TData data;
    private TMetadata metadata;

    public void reset() {
        success = true;
        messages.clear();
        items.clear();
        data = null;
        metadata = null;
    }

    public void addMessage(String message) {
        messages.addFirst(message);
    }

    public void setItem(String key, Object value) {
        items.put(key, value);
    }

    public <T> T getItem(String key, Class<T> type) {
        if (!items.containsKey(key)) {
            throw new NoSuchElementException("The store does not contain key: " + key);
        }
        Object item = items.get(key);
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
        return items.containsKey(key);
    }

    public Boolean hasNotItem(String key) {
        return !hasItem(key);
    }
}
