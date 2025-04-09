package ru.worktech.core;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import ru.worktech.config.ApiConfig;

public abstract class BaseApiService {

    protected static final ApiConfig config = ConfigFactory.create(ApiConfig.class);

    static {
        RestAssured.registerParser("text/plain", Parser.JSON);
    }

    protected RequestSpecification getRequestSpec() {
        return RestAssured.given()
                .baseUri(config.baseUrl())
                .contentType("application/json");
    }
}