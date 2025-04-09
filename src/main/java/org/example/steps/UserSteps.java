package org.example.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.core.AssertableResponse;
import org.example.models.AutorizationRequest;
import org.example.models.RegistrationRequest;
import org.example.services.UserService;

public class UserSteps {

    private final UserService userService = new UserService();

    @Step("зарегистрировать пользователя")
    public AssertableResponse registerUser(RegistrationRequest user) {
        Response response = userService.registerUser(user);
        return new AssertableResponse(response);
    }

    @Step("Авторизовать пользователя")
    public AssertableResponse loginUser(AutorizationRequest user) {
        Response response = userService.loginUser(user);
        return new AssertableResponse(response);
    }
}
