package ru.worktech.registration_test;

import org.testng.annotations.Test;
import ru.worktech.steps.UserSteps;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static ru.worktech.models.RegistrationRequest.RegistrationRequestBuilder;
import static ru.worktech.models.RegistrationRequest.builder;

public class RegistrationTests {

    private final UserSteps userSteps = new UserSteps();

    @Test
    public void testSuccessfulRegistration() {
        userSteps.registerUser(getDefaultRegistration().build())
                .checkStatusCode(SC_OK);
    }

    @Test
    public void testFailedOnEmptyEmailRegistration() {
        userSteps.registerUser(getDefaultRegistration().email("").build())
                .checkStatusCode(SC_BAD_REQUEST);
    }

    private RegistrationRequestBuilder getDefaultRegistration(){
        return builder()
                .email("defaulеt@gmail.com")
                .password("defaultPassword")
                .confirmPassword("defaultPassword")
                .lastName("defaultLastName")
                .firstName("defaultFirstName")
                .middleName("defaultMiddleName")
                .phone("+79991001010")
                .birthDate("01-01-1971")
                .gender(" ");
    }
}
