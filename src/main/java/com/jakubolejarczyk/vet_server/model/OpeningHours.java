package com.jakubolejarczyk.vet_server.model;

import com.jakubolejarczyk.vet_server.domain.OpeningHoursDomain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Time;

@Table(name = "openinghours")
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class OpeningHours implements OpeningHoursDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "monday_from")
    private Time mondayFrom;

    @Column(name = "monday_to")
    private Time mondayTo;

    @Column(name = "tuesday_from")
    private Time tuesdayFrom;

    @Column(name = "tuesday_to")
    private Time tuesdayTo;

    @Column(name = "wednesday_from")
    private Time wednesdayFrom;

    @Column(name = "wednesday_to")
    private Time wednesdayTo;

    @Column(name = "thursday_from")
    private Time thursdayFrom;

    @Column(name = "thursday_to")
    private Time thursdayTo;

    @Column(name = "friday_from")
    private Time fridayFrom;

    @Column(name = "friday_to")
    private Time fridayTo;

    @Column(name = "saturday_from")
    private Time saturdayFrom;

    @Column(name = "saturday_to")
    private Time saturdayTo;

    @Column(name = "sunday_from")
    private Time sundayFrom;

    @Column(name = "sunday_to")
    private Time sundayTo;
}
