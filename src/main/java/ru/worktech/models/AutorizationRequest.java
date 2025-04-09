package ru.worktech.models;

import lombok.Data;

@Data
public class AutorizationRequest {

    public String username;
    public String password;

    public AutorizationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
