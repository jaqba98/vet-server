package com.jakubolejarczyk.vet_server.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "role", nullable = false, length = 6)
    private String role;

    @Column(name ="picture_url", nullable = false)
    private String pictureUrl;

    @Column(name = "is_verified", nullable = false)
    private Boolean isVerified;
}
