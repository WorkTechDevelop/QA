package ru.worktech.services;

import io.restassured.response.Response;
import ru.worktech.core.BaseApiService;
import ru.worktech.models.request.AuthorizationRequest;
import ru.worktech.models.request.RegistrationRequest;

import java.util.Map;

import static ru.worktech.endpoints.ApiEndpoints.AUTHORIZATION_ENDPOINT;
import static ru.worktech.endpoints.ApiEndpoints.REGISTRATION_ENDPOINT;

public class UserService extends BaseApiService {

    public Response registerUser(RegistrationRequest user) {
        return getRequestSpec()
                .body(user)
                .when()
                .post(REGISTRATION_ENDPOINT.getAddress());
    }

    public Response registerUser(Map<String, Object> registrationMap) {
        return getRequestSpec()
                .body(registrationMap)
                .when()
                .post(REGISTRATION_ENDPOINT.getAddress());
    }


    public Response loginUser(AuthorizationRequest user) {
        return getRequestSpecWithOutAuth()
                .body(user)
                .when()
                .post(AUTHORIZATION_ENDPOINT.getAddress());
    }
}