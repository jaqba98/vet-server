package com.jakubolejarczyk.vet_server.model.independent;

import com.jakubolejarczyk.vet_server.domain.independent.OpeningHoursDomain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;

@Entity
@Table(name = "openinghours")
@Getter
@Setter
@SuperBuilder
public class OpeningHours implements OpeningHoursDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_archived", nullable = false)
    private Boolean isArchived;

    @Column(name = "monday_from")
    private LocalTime mondayFrom;

    @Column(name = "monday_to")
    private LocalTime mondayTo;

    @Column(name = "tuesday_from")
    private LocalTime tuesdayFrom;

    @Column(name = "tuesday_to")
    private LocalTime tuesdayTo;

    @Column(name = "wednesday_from")
    private LocalTime wednesdayFrom;

    @Column(name = "wednesday_to")
    private LocalTime wednesdayTo;

    @Column(name = "thursday_from")
    private LocalTime thursdayFrom;

    @Column(name = "thursday_to")
    private LocalTime thursdayTo;

    @Column(name = "friday_from")
    private LocalTime fridayFrom;

    @Column(name = "friday_to")
    private LocalTime fridayTo;

    @Column(name = "saturday_from")
    private LocalTime saturdayFrom;

    @Column(name = "saturday_to")
    private LocalTime saturdayTo;

    @Column(name = "sunday_from")
    private LocalTime sundayFrom;

    @Column(name = "sunday_to")
    private LocalTime sundayTo;
}
