package com.jakubolejarczyk.vet_server.model.dependent;

import com.jakubolejarczyk.vet_server.domain.dependent.PetDomain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Pet implements PetDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_archived", nullable = false)
    private Boolean isArchived;

    @Column(name = "entity_name", nullable = false)
    private String entityName;

    @Column
    private String species;

    @Column
    private String breed;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "weight_kg")
    private BigDecimal weightKg;

    @Column
    private String color;

    @Column
    private Boolean sterilized;

    @Column
    private String pictureUrl;

    @Column(name = "microchip_number")
    private String microchipNumber;

    @Column(name = "medical_notes", columnDefinition = "TEXT")
    private String medicalNotes;

    @Column(name = "client_id", nullable = false)
    private Long clientId;
}
