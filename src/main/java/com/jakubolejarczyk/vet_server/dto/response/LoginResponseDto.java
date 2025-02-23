package com.jakubolejarczyk.vet_server.dto.response;

public class LoginResponseDto {
    private Boolean success;

    private String token;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
