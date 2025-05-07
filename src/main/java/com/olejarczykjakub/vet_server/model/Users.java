package com.olejarczykjakub.vet_server.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "Users")
@Table(
	name = "users",
	uniqueConstraints = {
		@UniqueConstraint(
			name = "users_home_account_id_unique",
			columnNames = "homeAccountId"
		)
	}
)
@Data
public class Users {
	@Id
	@SequenceGenerator(
		name = "users_sequence",
		sequenceName = "users_sequence",
		allocationSize = 1
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "users_sequence"
	)
	@Column(
		name = "id",
		updatable = false
	)
	private Long id;

	@Column(
		name = "home_account_id",
		nullable = false,
		unique = true
	)
	private String homeAccountId;

	@Column(
		name = "name",
		nullable = false
	)
	private String name;

	@Column(
		name = "username",
		nullable = false
	)
	private String username;
}
