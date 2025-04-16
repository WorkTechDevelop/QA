package ru.worktech.core;

import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import ru.worktech.config.ApiConfig;

import static io.restassured.RestAssured.*;
import static io.restassured.parsing.Parser.JSON;
import static org.aeonbits.owner.ConfigFactory.create;

public abstract class BaseApiService {

    protected static final ApiConfig config = create(ApiConfig.class);

    static {
        registerParser("text/plain", JSON);
    }

    protected RequestSpecification getRequestSpec() {
        filters(new ResponseLoggingFilter());
        return given().log().all()
                .baseUri(config.baseUrl())
                .contentType("application/json");
    }
}