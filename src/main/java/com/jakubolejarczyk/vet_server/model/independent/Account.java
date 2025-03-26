package com.jakubolejarczyk.vet_server.model.independent;

import com.jakubolejarczyk.vet_server.domain.independent.AccountDomain;
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
public class Account implements AccountDomain {
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

    @Column(name = "is_archived", nullable = false)
    private Boolean isArchived;
}
