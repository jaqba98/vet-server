package com.olejarczykjakub.vet_server.model;

public class UserModel {
	private String homeAccountId;
	private String name;
	private String username;

	public String getHomeAccountId() {
		return homeAccountId;
	}

	public void setHomeAccountId(String homeAccountId) {
		this.homeAccountId = homeAccountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
