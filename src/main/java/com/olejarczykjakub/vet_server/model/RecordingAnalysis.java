package com.olejarczykjakub.vet_server.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class RecordingAnalysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<KeyValuePair> data;

    public RecordingAnalysis() {}

    public RecordingAnalysis(List<KeyValuePair> data) {
        this.data = data;
    }

    public Long getId() { return id; }

    public List<KeyValuePair> getData() { return data; }
    public void setData(List<KeyValuePair> data) { this.data = data; }
}