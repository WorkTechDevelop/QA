package org.example.core;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class AssertableResponse {

    private final Response response;

    public AssertableResponse(Response response) {
        this.response = response;
    }

    public AssertableResponse checkStatusCode(int statusCode) {
        response.then().statusCode(statusCode);
        return this;
    }

    public AssertableResponse checkBodyFieldEquals(String field, Object value) {
        response.then().body(field, equalTo(value));
        return this;
    }
}