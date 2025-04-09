package org.example.services;

import io.restassured.response.Response;
import org.example.core.BaseApiService;
import org.example.models.AutorizationRequest;
import org.example.models.RegistrationRequest;

import static org.example.endpoints.Endpoints.AUTHORIZATION_ENDPOINT;
import static org.example.endpoints.Endpoints.REGISTRATION_ENDPOINT;

public class UserService extends BaseApiService {

    public Response registerUser(RegistrationRequest user) {
        return getRequestSpec()
                .body(user)
                .when()
                .post(REGISTRATION_ENDPOINT);
    }

    public Response loginUser(AutorizationRequest user) {
        return getRequestSpec()
                .body(user)
                .when()
                .post(AUTHORIZATION_ENDPOINT);
    }
}