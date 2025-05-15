package ru.worktech.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.worktech.core.AssertableResponse;
import ru.worktech.models.request.AuthorizationRequest;
import ru.worktech.models.request.RegistrationRequest;
import ru.worktech.services.UserService;

public class UserSteps {

    private final UserService userService = new UserService();

    @Step("зарегистрировать пользователя")
    public AssertableResponse registerUser(RegistrationRequest user) {
        Response response = userService.registerUser(user);
        return new AssertableResponse(response);
    }

    @Step("Авторизовать пользователя")
    public AssertableResponse loginUser(AuthorizationRequest user) {
        Response response = userService.loginUser(user);
        return new AssertableResponse(response);
    }
}
