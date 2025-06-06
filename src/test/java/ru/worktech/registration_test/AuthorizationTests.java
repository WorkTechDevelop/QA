package ru.worktech.registration_test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.worktech.steps.UserSteps;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static ru.worktech.models.request.AuthorizationRequest.AuthorizationRequestBuilder;
import static ru.worktech.models.request.AuthorizationRequest.builder;
import static testDataGenerator.testDataGenerator.generateRandomEmail;
import static testDataGenerator.testDataGenerator.generateRandomPassword;

public class AuthorizationTests {

    private final UserSteps userSteps = new UserSteps();

    @Test(testName = "TK-311-1-Успешная авторизация")
    public void testAuthorizationSuccess() {
        userSteps.loginUser(getDefaultAuthorization().build())
                .assertStatus(SC_OK);
    }

    @DataProvider(name = "dataProvider")
    public Object[][] dataProvider() {
        return new Object[][]{
                {null, "defaultPassword"},
                {"default@gmail.com", null},
                {null, null},
                {"default.gmail.com", "defaultPassword"},
                {" default.gmail.com ", "defaultPassword"},
                {generateRandomEmail(), generateRandomPassword()},
        };
    }

    @Test(testName = "ТК-311-Тесты на авторизацию с невалидными данными", dataProvider = "dataProvider")
    public void testAuthorizationFail(String email, String password) {
        userSteps.loginUser(builder()
                        .username(email)
                        .password(password)
                        .build())
                .assertStatus(SC_UNAUTHORIZED);
    }

    private AuthorizationRequestBuilder getDefaultAuthorization() {
        return builder()
                .username("test@mail.ru")
                .password("wasdwasd");
    }
}
