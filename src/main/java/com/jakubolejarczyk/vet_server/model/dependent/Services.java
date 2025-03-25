package com.jakubolejarczyk.vet_server.model.dependent;

import com.jakubolejarczyk.vet_server.domain.dependent.ServicesDomain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Services implements ServicesDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String category;

    @Column(name = "duration_minutes", nullable = false)
    private String durationMinutes;

    @Column(nullable = false)
    private String price;

    @Column(name = "is_available", nullable = false)
    private String isAvailable;

    @Column(name = "clinic_id", nullable = false)
    private Long clinicId;
}
