package ru.worktech.models.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorizationRequest {

    public String username;
    public String password;

    public AuthorizationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
