package com.jakubolejarczyk.vet_server.model.dependent;

import com.jakubolejarczyk.vet_server.domain.dependent.VetServiceDomain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Table(name = "vetservice")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class VetService implements VetServiceDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_archived", nullable = false)
    private Boolean isArchived;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String category;

    @Column(name = "duration_minutes", nullable = false)
    private Integer durationMinutes;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;

    @Column(name = "clinic_id", nullable = false)
    private Long clinicId;
}
