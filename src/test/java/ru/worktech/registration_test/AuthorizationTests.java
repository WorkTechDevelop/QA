package ru.worktech.registration_test;

import org.testng.annotations.Test;
import ru.worktech.config.ApiConfig;
import ru.worktech.steps.UserSteps;

import static java.util.UUID.randomUUID;
import static org.aeonbits.owner.ConfigFactory.create;
import static org.apache.http.HttpStatus.*;
import static ru.worktech.models.request.AuthorizationRequest.builder;

public class AuthorizationTests {

    private final UserSteps userSteps = new UserSteps();
    private static final ApiConfig config = create(ApiConfig.class);

    @Test(testName = "TK-311-1-Успешная авторизация")
    public void testSuccessfulAuthorization() {
        userSteps.loginUser(
                builder()
                        .username(config.username())
                        .password(config.password())
                        .build())
                .checkStatusCode(SC_OK);
    }

    @Test(testName = "TK-311-2-Авторизация без Email")
    public void testAuthorizationWithoutEmail() {
        userSteps.loginUser(
                        builder()
                                .username(null)
                                .password("defaultPassword")
                                .build())
                .checkStatusCode(SC_UNAUTHORIZED);
    }

    @Test(testName = "TK-311-3-Авторизация без пароля")
    public void testAuthorizationWithoutPassword() {
        userSteps.loginUser(
                        builder()
                                .username("default@gmail.com")
                                .password(null)
                                .build())
                .checkStatusCode(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-311-4-Авторизация без Email и пароля")
    public void testAuthorizationWithEmptyData() {
        userSteps.loginUser(
                        builder()
                                .username(null)
                                .password(null)
                                .build())
                .checkStatusCode(SC_UNAUTHORIZED);
    }

    @Test(testName = "TK-311-6-Авторизация с некоректным Email (без @)")
    public void testAuthorizationWithIncorrectEmail() {
        userSteps.loginUser(
                        builder()
                                .username("default.gmail.com")
                                .password("defaultPassword")
                                .build())
                .checkStatusCode(SC_UNAUTHORIZED);
    }

    @Test(testName = "TK-311-7-Авторизация c пробелами перед и после Email")
    public void testAuthorizationWithSpacesBeforeAndAfterEmail() {
        userSteps.loginUser(
                        builder()
                                .username(" default@gmail.com ")
                                .password("defaultPassword")
                                .build())
                .checkStatusCode(SC_UNAUTHORIZED);
    }

    @Test(testName = "TK-311-8-Авторизация незаригестрированного пользователя")
    public void testAuthorizationNotExistUser() {
        userSteps.loginUser(
                        builder()
                                .username(generateRandomEmail())
                                .password(generateRandomPassword())
                                .build())
                .checkStatusCode(SC_UNAUTHORIZED);
    }

    private String generateRandomEmail() {
        return "user" + randomUUID() + "@mail.com";
    }

    private String generateRandomPassword() {
        return randomUUID().toString().substring(0, 10);
    }
}
