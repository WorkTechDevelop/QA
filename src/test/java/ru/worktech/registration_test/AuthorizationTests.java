package ru.worktech.registration_test;

import database.dto.UserDTO;
import database.query.UserQuery;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.worktech.models.request.AuthorizationRequest;
import ru.worktech.steps.UserSteps;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static testDataGenerator.TestDataGenerator.generateRandomEmail;
import static testDataGenerator.TestDataGenerator.generateRandomPassword;

public class AuthorizationTests extends UserSteps {

    private String userEmail;
    private String password = "password12345";
    UserDTO userDTO;
    UserQuery userQuery = new UserQuery();

    @BeforeTest
    private void testUserCreation() {
        userEmail = generateRandomEmail();
        System.out.printf("Test user email = [%s]\n", userEmail);
        String passwordHash = "$2a$10$KaVHluqzpnf5SZt5AQMwHu012fwB2DE803njWq9y19cddH3Qj8baW";
        Timestamp timestamp = new Timestamp(Instant.now().toEpochMilli());
        long oneDay = 24 * 60 * 60 * 1000;
        timestamp.setTime(timestamp.getTime() - oneDay);
        userDTO = UserDTO.builder()
                .id(UUID.randomUUID().toString())
                .email(userEmail)
                .is_active(true)
                .first_name("Random")
                .last_name("Test")
                .gender("MALE")
                .password(passwordHash)
                .confirmed_at(timestamp)
                .build();
        userQuery.create(userDTO);
        userQuery.createRole(userDTO.getId(), "ADMIN");
    }

    @AfterTest
    private void testUserDeletion() throws SQLException {
        userQuery.deleteByEmail(userEmail);
        userQuery.closeConnection();
    }

    @Test(testName = "Черновик теста авторизации после создания пользователя в БД")
    public void authorizationTest() {
        loginUser(new AuthorizationRequest(userEmail, password))
                .assertStatus(SC_OK);
    }

    @Test(testName = "TK-311-1-Успешная авторизация")
    public void testAuthorizationSuccess() {
        loginUser(new AuthorizationRequest("test3@mail.ru", "password12345"))
                .assertStatus(SC_OK);
    }

    @Test(testName = "ТК-311-Тесты на авторизацию с невалидными данными", dataProvider = "dataProvider")
    public void testAuthorizationFail(String email, String password) {
        loginUser(new AuthorizationRequest(email, password))
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
