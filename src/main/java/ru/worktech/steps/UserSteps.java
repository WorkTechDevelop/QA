package ru.worktech.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.worktech.core.AssertableResponse;
import ru.worktech.models.request.AuthorizationRequest;
import ru.worktech.models.request.RegistrationRequest;
import ru.worktech.services.UserService;

import java.util.Map;

public class UserSteps {

    protected String userEmail;
    protected final String password = "password12345";
    protected final String passwordHash = "$2a$10$KaVHluqzpnf5SZt5AQMwHu012fwB2DE803njWq9y19cddH3Qj8baW";

    private final UserService userService = new UserService();

    @Step("зарегистрировать пользователя")
    public AssertableResponse registerUser(RegistrationRequest user) {
        Response response = userService.registerUser(user);
        return new AssertableResponse(response);
    }

    @Step("зарегистрировать пользователя")
    public AssertableResponse registerUserMap(Map<String, Object> registrationMap) {
        Response response = userService.registerUser(registrationMap);
        return new AssertableResponse(response);
    }

    @Step("Авторизовать пользователя")
    public AssertableResponse loginUser(AuthorizationRequest user) {
        Response response = userService.loginUser(user);
        return new AssertableResponse(response);
    }
}