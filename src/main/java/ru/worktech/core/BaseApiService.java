package ru.worktech.core;

import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import ru.worktech.config.ApiConfig;

import static io.restassured.RestAssured.*;

public abstract class BaseApiService {

    protected static final ApiConfig config = ConfigFactory.create(ApiConfig.class);

    static {
        registerParser("text/plain", Parser.JSON);
    }

    protected RequestSpecification getRequestSpec() {
        filters(new ResponseLoggingFilter());
        return given().log().all()
                .baseUri(config.baseUrl())
                .contentType("application/json");
    }
}