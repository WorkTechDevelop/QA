package ru.worktech.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.worktech.core.AssertableResponse;
import ru.worktech.models.AutorizationRequest;
import ru.worktech.models.RegistrationRequest;
import ru.worktech.services.UserService;

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
