package com.jakubolejarczyk.vet_server.controller;

import jakarta.persistence.*;

@Entity(name = "Contact")
@Table(name = "Contact")
public class Contact {

    @Id
    @SequenceGenerator(
            name = "contact_sequence",
            sequenceName = "contact_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "contact_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Integer id;

    @Column(
            name = "type",
            nullable = false
    )
    private String type;

    @Column(
            name = "value",
            nullable = false
    )
    private String value;

    @Column(
            name = "is_primary",
            nullable = false
    )
    private Boolean isPrimary;

    public Contact(String type, String value, Boolean isPrimary) {
        this.type = type;
        this.value = value;
        this.isPrimary = isPrimary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }
}
