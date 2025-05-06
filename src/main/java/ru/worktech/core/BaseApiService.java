package ru.worktech.core;

import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import ru.worktech.config.ApiConfig;
import ru.worktech.models.AuthorizationRequest;

import static io.restassured.RestAssured.*;
import static ru.worktech.endpoints.Endpoints.AUTHORIZATION_ENDPOINT;

public abstract class BaseApiService {

    protected static final ApiConfig config = ConfigFactory.create(ApiConfig.class);
    private static String authToken;

    static {
        registerParser("text/plain", Parser.JSON);
    }

    protected static String getAuthToken() {
        if (authToken == null) {
            authToken = fetchNewToken();
        }
        return authToken;
    }

    protected RequestSpecification getRequestSpec() {
        filters(new ResponseLoggingFilter());
        return given().log().all()
                .baseUri(config.baseUrl())
                .contentType("application/json")
                .header("Authorization", "Bearer " + getAuthToken());      //в тестах на авторизацию хидер нужно будет нулить
    }

    private static String fetchNewToken() {
        Response response = given()
                .baseUri(config.baseUrl())
                .contentType("application/json")
                .body(new AuthorizationRequest(config.login(), config.password()))
                .when()
                .post(AUTHORIZATION_ENDPOINT);

        System.out.println("Auth status code: " + response.getStatusCode());
        System.out.println("Auth response body: " + response.asString());

        if (response.getStatusCode() == 200) {
            return response
                    .jsonPath()
                    .getString("jwtToken");
        } else {
            return null;
        }
    }
}