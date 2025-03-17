package com.jakubolejarczyk.vet_server.model;

import com.jakubolejarczyk.vet_server.domain.AccountDomain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Account extends AccountDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(length = 6)
    private String role;

    @Column(name ="picture_url")
    private String pictureUrl;

    @Column(name = "is_verified", nullable = false)
    private Boolean isVerified;
}
