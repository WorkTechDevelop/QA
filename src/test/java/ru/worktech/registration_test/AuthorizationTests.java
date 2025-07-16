package ru.worktech.registration_test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.worktech.models.request.AuthorizationRequest;
import ru.worktech.steps.UserSteps;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static testDataGenerator.TestDataGenerator.generateRandomEmail;
import static testDataGenerator.TestDataGenerator.generateRandomPassword;

public class AuthorizationTests {

    private final UserSteps userSteps = new UserSteps();

    @Test(testName = "TK-311-1-Успешная авторизация")
    public void testAuthorizationSuccess() {
     userSteps.loginUser(new AuthorizationRequest("test3@mail.ru","password12345" ))
             .assertStatus(SC_OK);
    }

    @DataProvider(name = "dataProvider")
    public Object[][] dataProvider() {
        return new Object[][]{
                { null, "defaultPassword" },
                { "default@gmail.com", null },
                { null, null },
                { "default.gmail.com", "defaultPassword" },
                { " default.gmail.com ", "defaultPassword" },
                { generateRandomEmail(), generateRandomPassword() },
        };
    }

    @Test(testName = "ТК-311-Тесты на авторизацию с невалидными данными", dataProvider = "dataProvider")
    public void testAuthorizationFail(String email, String password) {
        userSteps.loginUser(new AuthorizationRequest(email, password))
                .assertStatus(SC_UNAUTHORIZED);
    }
}
