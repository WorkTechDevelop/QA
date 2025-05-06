package ru.worktech.core;

import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import ru.worktech.config.ApiConfig;
import ru.worktech.models.AuthorizationRequest;

import static io.restassured.RestAssured.*;
import static io.restassured.parsing.Parser.JSON;
import static org.aeonbits.owner.ConfigFactory.create;
import static ru.worktech.endpoints.Endpoints.AUTHORIZATION_ENDPOINT;

public abstract class BaseApiService {

    protected static final ApiConfig config = create(ApiConfig.class);
    private static String authToken;

    static {
        registerParser("text/plain", JSON);
    }

    protected static String getAuthToken() {
        if (authToken == null) {
            authToken = fetchNewToken();
        }
        return authToken;
    }

    protected RequestSpecification getRequestSpec() {
        filters(new ResponseLoggingFilter());
        return given()
                .baseUri(config.baseUrl())
                .contentType("application/json")
                .header("Authorization", "Bearer " + getAuthToken());
    }

    private static String fetchNewToken() {
        return given()
                .baseUri(config.baseUrl())
                .contentType("application/json")
                .body(new AuthorizationRequest(config.username(), config.password()))
                .when()
                .post(AUTHORIZATION_ENDPOINT)
                .then()
                .extract()
                .path("token");
    }
}