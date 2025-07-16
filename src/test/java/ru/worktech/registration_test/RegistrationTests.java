package ru.worktech.registration_test;

import database.query.DeleteUser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.worktech.steps.UserSteps;

import static java.util.Objects.nonNull;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static testDataGenerator.TestDataGenerator.*;

public class RegistrationTests {

    private final UserSteps userSteps = new UserSteps();
    private final DeleteUser dbManage = new DeleteUser();
    private String userEmail;

    @AfterMethod
    public void afterMethod() {
        if (nonNull(userEmail)) {
            dbManage.deleteUserByEmail(userEmail);
        }
    }

    @Test(testName = "TK-311-1-Успешная регистрации нового пользователя")
    public void testRegistrationSuccessRegistration() {
        userEmail = generateRandomEmail();
        var request = getDefaultRegistration()
                .setEmail(generateRandomEmail());

        userSteps.registerUser(request)
                .assertStatus(SC_OK);
    }

    @Test(testName = "ТК-311-10-Поле middleName не является обязательным")
    public void testRegistrationMiddleNameNotRequired() {
        userEmail = generateRandomEmail();
        var request = getDefaultRegistration()
                .setEmail(userEmail)
                .setMiddleName("");

        userSteps.registerUser(request)
                .assertStatus(SC_OK);
    }

    @Test(testName = "TK-311-2-Проверка регистрации пользователя, который уже существует")
    public void testRegistrationFailExistedUser() {
        userSteps.registerUser(getDefaultRegistration());

        userSteps.registerUser(getDefaultRegistration())
                .assertStatus(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-311-4-Проверка минимальной длины пароля.")
    public void testRegistrationFailShortPassword() {
        var request = getDefaultRegistration()
                .setPassword("Av1234")
                .setConfirmPassword("Av1234");

        userSteps.registerUser(request)
                .assertStatus(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-311-5-Проверка регистрации пользователя с несовпадающими паролями при подтверждении")
    public void testRegistrationFailUserWithMismatchedPasswords() {
        var request = getDefaultRegistration()
                .setPassword("defaultPassword123")
                .setConfirmPassword("defaultPassword12");

        userSteps.registerUser(request)
                .assertStatus(SC_BAD_REQUEST);
    }

    @DataProvider(name = "InvalidFieldValues")
    public Object[][] dataProvider1() {
        return new Object[][]{
                {"email", ""},
                {"email", " default@gmail.com "},
                {"email", "default.gmail.com"},
                {"email", "default@ru"},
                {"email", "testEmail*mail.ru"}
        };
    }

    @Test(testName = "ТК-311- Негативные тесты на регистрацию", dataProvider ="InvalidFieldValues" )
    public void registrationFailTests(String field, String value){
       var request = getDefaultCreateTaskRequestMap();
       request.put(field, value);

       userSteps.registerUserMap(request)
               .assertStatus(SC_BAD_REQUEST);
    }
}