package ru.worktech.registration_test;

import org.testng.annotations.Test;
import ru.worktech.steps.UserSteps;

import static java.util.UUID.randomUUID;
import static org.apache.http.HttpStatus.*;
import static ru.worktech.models.AuthorizationRequest.AuthorizationRequestBuilder;
import static ru.worktech.models.AuthorizationRequest.builder;

public class AuthorizationTests {

    private final UserSteps userSteps = new UserSteps();

    @Test
    public void testSuccessfulAuthorization() {
        userSteps.loginUser(getDefaultAuthorization().build())
                .checkStatusCode(SC_OK);
    }

    @Test
    public void testAuthorizationWithoutEmail() {
        userSteps.loginUser(
                        builder()
                                .username(null)
                                .password("defaultPassword")
                                .build())
                .checkStatusCode(SC_UNAUTHORIZED);
    }

    @Test
    public void testAuthorizationWithoutPassword() {
        userSteps.loginUser(
                        builder()
                                .username("default@gmail.com")
                                .password(null)
                                .build())
                .checkStatusCode(SC_BAD_REQUEST);
    }

    @Test
    public void testAuthorizationWithEmptyData() {
        userSteps.loginUser(
                        builder()
                                .username(null)
                                .password(null)
                                .build())
                .checkStatusCode(SC_UNAUTHORIZED);
    }

    @Test
    public void testAuthorizationWithIncorrectEmail() {
        userSteps.loginUser(
                        builder()
                                .username("default.gmail.com")
                                .password("defaultPassword")
                                .build())
                .checkStatusCode(SC_UNAUTHORIZED);
    }

    @Test
    public void testAuthorizationWithSpacesBeforeAndAfterEmail() {
        userSteps.loginUser(
                        builder()
                                .username(" default@gmail.com ")
                                .password("defaultPassword")
                                .build())
                .checkStatusCode(SC_UNAUTHORIZED);
    }

    @Test
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

    private AuthorizationRequestBuilder getDefaultAuthorization() {
        return builder()
                .username("default@gmail.com")
                .password("defaultPassword");
    }
}
