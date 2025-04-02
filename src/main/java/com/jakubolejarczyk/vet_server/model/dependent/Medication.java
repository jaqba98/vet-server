package com.jakubolejarczyk.vet_server.model.dependent;

import com.jakubolejarczyk.vet_server.domain.dependent.MedicationDomain;
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
public class Medication implements MedicationDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_archived", nullable = false)
    private Boolean isArchived;

    @Column(name = "entity_name", nullable = false)
    private String entityName;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private String dose;

    @Column(name = "quantity_in_stock", nullable = false)
    private Long quantityInStock;

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;

    @Column(name = "clinic_id", nullable = false)
    private Long clinicId;
}
