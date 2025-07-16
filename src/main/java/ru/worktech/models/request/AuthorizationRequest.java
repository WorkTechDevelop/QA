package ru.worktech.models.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors (chain = true)
public class AuthorizationRequest {

    public String email;
    public String password;

    public AuthorizationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
