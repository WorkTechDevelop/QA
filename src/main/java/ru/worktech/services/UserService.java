package ru.worktech.services;

import io.restassured.response.Response;
import ru.worktech.core.BaseApiService;
import ru.worktech.models.AuthorizationRequest;
import ru.worktech.models.RegistrationRequest;

import static ru.worktech.endpoints.Endpoints.AUTHORIZATION_ENDPOINT;
import static ru.worktech.endpoints.Endpoints.REGISTRATION_ENDPOINT;

public class UserService extends BaseApiService {

    public Response registerUser(RegistrationRequest user) {
        return getRequestSpec()
                .body(user)
                .when()
                .post(REGISTRATION_ENDPOINT);
    }

    public Response loginUser(AuthorizationRequest user) {
        return getRequestSpec()
                .body(user)
                .when()
                .post(AUTHORIZATION_ENDPOINT);
    }
}