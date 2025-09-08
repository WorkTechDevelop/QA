package ru.worktech.common;

import database.dto.UserDTO;
import database.utils.UserFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.sql.SQLException;

public class FreshUserTest extends BaseApiTests {
    protected UserDTO currentUser;

    @BeforeMethod
    public void createFreshUser() throws SQLException {
        currentUser = UserFactory.createUser();
    }

    @AfterMethod
    public void deleteFreshUser() throws SQLException {
        if (currentUser != null) {
            UserFactory.deleteUser(currentUser.getEmail());
            currentUser = null;
        }
    }
}
