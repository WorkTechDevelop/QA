package ru.worktech.registration_test;

import database.dto.UserDTO;
import database.query.UserQueries;
import database.utils.UserFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.worktech.common.BaseApiTests;
import ru.worktech.common.FreshUserTest;
import ru.worktech.common.SharedUserTest;
import ru.worktech.models.request.AuthorizationRequest;

import java.sql.SQLException;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static testDataGenerator.TestDataGenerator.generateRandomEmail;
import static testDataGenerator.TestDataGenerator.generateRandomPassword;

public class AuthorizationTests extends BaseApiTests {

    private UserDTO currentUser;

    @Test(testName = "TK-311-1-Успешная авторизация")
    public void testAuthorizationSuccess() throws SQLException {
        currentUser = UserFactory.createUser();
        getUserSteps().loginUser(new AuthorizationRequest(currentUser.getEmail(), currentUser.getPassword()))
                .assertStatus(SC_OK);
    }


    @Test(testName = "ТК-311-Тесты на авторизацию с невалидными данными", dataProvider = "dataProvider")
    public void testAuthorizationFail(String email, String password) {
        getUserSteps().loginUser(new AuthorizationRequest(email, password))
                .assertStatus(SC_UNAUTHORIZED);
    }

    @DataProvider(name = "dataProvider")
    private Object[][] dataProvider() {
        return new Object[][]{
                {null, "defaultPassword"},
                {"default@gmail.com", null},
                {null, null},
                {"", ""},
                {"default.gmail.com", "defaultPassword"},
                {" default.gmail.com ", "defaultPassword"},
                {generateRandomEmail(), generateRandomPassword()},
        };
    }
}
