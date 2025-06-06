package ru.worktech.registration_test;

import DataBaseManageServices.query.DeleteUserFromDataBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.worktech.models.request.RegistrationRequest;
import ru.worktech.steps.UserSteps;

import java.util.Objects;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static ru.worktech.models.request.RegistrationRequest.RegistrationRequestBuilder;
import static ru.worktech.models.request.RegistrationRequest.builder;
import static testDataGenerator.TestDataGenerator.generateRandomEmail;

public class RegistrationTests {

    private final UserSteps userSteps = new UserSteps();
    private final DeleteUserFromDataBase dbManage = new DeleteUserFromDataBase();
    private String userEmail;

    @AfterMethod
    public void afterMethod() {
        if (Objects.nonNull(userEmail)) {
            dbManage.deleteUserByEmail(userEmail);
        }
    }

    @Test(testName = "TK-311-1-Успешная регистрации нового пользователя")
    public void testRegistrationSuccessRegistration() {
        userEmail = generateRandomEmail();
        userSteps.registerUser(getDefaultRegistration().email(userEmail).build())
                .assertStatus(SC_OK);
    }

    @Test(testName = "TK-311-2-Проверка регистрации пользователя, который уже существует")
    public void testRegistrationFailExistedUser() {
        userSteps.registerUser(getDefaultRegistration().build());
        userSteps.registerUser(getDefaultRegistration().build())
                .assertStatus(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-311-?-")
    public void testRegistrationFailOnEmptyEmail() {
        userSteps.registerUser(getDefaultRegistration().email("").build())
                .assertStatus(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-311-3-Проверка email с пробелами")
    public void testRegistrationFailUserWithSpaces() {
        userSteps.registerUser(getDefaultRegistration().email(" default@gmail.com ").build())
                .assertStatus(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-311-4-Проверка минимальной длины пароля.")
    public void testRegistrationFailShortPassword() {
        userSteps.registerUser(getDefaultRegistration().password("Av1234").confirmPassword("Av1234").build())
                .assertStatus(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-311-5-Проверка регистрации пользователя с несовпадающими паролями при подтверждении")
    public void testRegistrationFailUserWithMismatchedPasswords() {
        userSteps.registerUser(getDefaultRegistration().password("defaultPassword123")
                .confirmPassword("defaultPassword12").build()).assertStatus(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-311-6-Проверка Email без \"@\"")
    public void testRegistrationFailIncorrectEmail() {
        userSteps.registerUser(getDefaultRegistration().email("default.gmail.com").build())
                .assertStatus(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-311-7-Проверка email без доменной части")
    public void testRegistrationFailWithoutDomain() {
        userSteps.registerUser(getDefaultRegistration().email("default@ru").build())
                .assertStatus(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-311-8-Проверка email с недопустимыми символами")
    public void testRegistrationFailSpecialSymbol() {
        userSteps.registerUser(getDefaultRegistration().email("testdEmail*mail.ru").build())
                .assertStatus(SC_BAD_REQUEST);
    }

    @Test(testName = "ТК-311-10-Поле middleName не является обязательным")
    public void testRegistrationMiddleNameNotRequired() {
        userEmail = generateRandomEmail();
        RegistrationRequest request = getDefaultRegistration()
                .lastName("defaultname")
                .firstName("defaultFirstName")
                .email(userEmail)
                .password("StrongPassword123!")
                .confirmPassword("StrongPassword123!")
                .middleName(null)
                .build();

        userSteps.registerUser(request)
                .assertStatus(SC_OK);
    }

    private RegistrationRequestBuilder getDefaultRegistration() {
        return builder()
                .email("default@.com")
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