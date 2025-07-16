package ru.worktech.core;

import io.restassured.response.Response;

public class AssertableResponse {

    private final Response response;

    public AssertableResponse(Response response) {
        this.response = response;
    }

    public AssertableResponse assertStatus(int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
        return this;
    }

    public String getStringByJsonPath(String jsonPath) {
        return response.jsonPath().get(jsonPath);
    }

    public Response getRawResponse() {
        return response;
    }
}