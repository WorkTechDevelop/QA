package ru.worktech.registration_test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.worktech.services.DataBaseManageService;
import ru.worktech.steps.UserSteps;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static ru.worktech.models.RegistrationRequest.RegistrationRequestBuilder;
import static ru.worktech.models.RegistrationRequest.builder;
import static ru.worktech.services.TestDataGenerator.generateRandomEmail;

public class RegistrationTests {

    private final UserSteps userSteps = new UserSteps();
    private final DataBaseManageService dbManage = new DataBaseManageService();
    private String userEmail;

    @AfterMethod
    public void deleteUserFromDataBase() {
        if (userEmail != null) {
            dbManage.deleteUser(userEmail);
        }
    }

    @Test(testName = "TK-311-1- Успешная регистрации нового пользователя ")
    public void testRegistrationSuccessRegistration() {
        userEmail = generateRandomEmail();
        userSteps.registerUser(getDefaultRegistration().email(userEmail).build())
                .checkStatusCode(SC_OK);
    }

    @Test(testName = "TK-311-2-Проверка регистрации пользователя, который уже существует")
    public void testRegistrationFailExistedUser() {
        userSteps.registerUser(getDefaultRegistration().build());
        userSteps.registerUser(getDefaultRegistration().build())
                .checkStatusCode(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-311-?-")
    public void testRegistrationFailOnEmptyEmail() {
        userSteps.registerUser(getDefaultRegistration().email("").build())
                .checkStatusCode(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-311-3-Проверка email с пробелами")
    public void testRegistrationFailUserWithSpaces() {
        userSteps.registerUser(getDefaultRegistration().email(" default@gmail.com ").build())
                .checkStatusCode(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-311-4-Проверка минимальной длины пароля.")
    public void testRegistrationFailShortPassword() {
        userSteps.registerUser(getDefaultRegistration().password("Av1234").confirmPassword("Av1234").build())
                .checkStatusCode(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-311-5-Проверка регистрации пользователя с несовпадающими паролями при подтверждении")
    public void testRegistrationFailUserWithMismatchedPasswords() {
        userSteps.registerUser(getDefaultRegistration().password("defaultPassword123")
                        .confirmPassword("defaultPassword12").build()).checkStatusCode(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-311-6-Проверка Email без \"@\"")
    public void testRegistrationFailIncorrectEmail() {
        userSteps.registerUser(getDefaultRegistration().email("default.gmail.com").build())
                .checkStatusCode(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-311-7-Проверка email без доменной части")
    public void testRegistrationFailWithoutDomain() {
        userSteps.registerUser(getDefaultRegistration().email("default@ru").build())
                .checkStatusCode(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-311-8-Проверка email с недопустимыми символами")
    public void testRegistrationFailSpecialSymbol() {
        userSteps.registerUser(getDefaultRegistration().email("testdEmail*mail.ru").build())
                .checkStatusCode(SC_BAD_REQUEST);
    }

    private RegistrationRequestBuilder getDefaultRegistration() {
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