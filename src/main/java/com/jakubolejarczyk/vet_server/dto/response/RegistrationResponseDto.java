package com.jakubolejarczyk.vet_server.dto.response;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RegistrationResponseDto {
    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("errors")
    private Map<String, String> errors;

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
