package ru.worktech.registration_test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.worktech.steps.UserSteps;

import static org.apache.http.HttpStatus.*;
import static ru.worktech.models.RegistrationRequest.RegistrationRequestBuilder;
import static ru.worktech.models.RegistrationRequest.builder;

public class RegistrationTests {

    private final UserSteps userSteps = new UserSteps();

    @AfterMethod


    @Test
    public void testSuccessfulRegistration() {
        userSteps.registerUser(getDefaultRegistration().build())
                .checkStatusCode(SC_OK);
    }

    @Test
    public void testExistedUserRegistration() {
        userSteps.registerUser(getDefaultRegistration().build())
                .checkStatusCode(SC_BAD_REQUEST);
    }

    @Test
    public void testFailedOnEmptyEmailRegistration() {
        userSteps.registerUser(getDefaultRegistration().email("").build())
                .checkStatusCode(SC_BAD_REQUEST);
    }

    @Test
    public void testRegistrationUserWithSpaces() {
        userSteps.registerUser(getDefaultRegistration().email(" default@gmail.com ").build())
                .checkStatusCode(SC_BAD_REQUEST);
    }

    @Test
    public void testShortPasswordRegistration() {
        userSteps.registerUser(getDefaultRegistration().password("Av1234").confirmPassword("Av1234").build())
                .checkStatusCode(SC_BAD_REQUEST);
    }

    @Test
    public void testCorrectConfirmPassword() {
        userSteps.registerUser(getDefaultRegistration().password("defaultPassword123").confirmPassword("defaultPassword123").build())
                .checkStatusCode(SC_CREATED);
    }

    @Test
    public void testIncorrectEmailRegistration() {
        userSteps.registerUser(getDefaultRegistration().email("default.gmail.com").build())
                .checkStatusCode(SC_BAD_REQUEST);
    }

    @Test
    public void testWithoutDomainRegistration() {
        userSteps.registerUser(getDefaultRegistration().email("default@ru").build())
                .checkStatusCode(SC_BAD_REQUEST);
    }

    @Test
    public void testSpecialSymbolRegistration() {
        userSteps.registerUser(getDefaultRegistration().email("testdEmail*mail.ru").build())
                .checkStatusCode(SC_BAD_REQUEST);
    }

    private RegistrationRequestBuilder getDefaultRegistration(){
        return builder()
                .email("default@gmail.com")
                .password("defaultPassword")
                .confirmPassword("defaultPassword")
                .lastName("defaultLastName")
                .firstName("defaultFirstName")
                .middleName("defaultMiddleName")
                .phone("+79991001010")
                .birthDate("01-01-1971")
                .gender("MALE");
    }
}