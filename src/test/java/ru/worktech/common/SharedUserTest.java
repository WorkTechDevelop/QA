package ru.worktech.common;

import database.dto.UserDTO;
import database.utils.UserFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.sql.SQLException;

public class SharedUserTest extends BaseApiTests {
    protected UserDTO currentUser;

    @BeforeClass
    public void createSharedUser() throws SQLException {
        currentUser = UserFactory.createUser();
    }

    @AfterClass
    public void deleteSharedUser() throws SQLException {
        if (currentUser != null) {
            UserFactory.deleteUser(currentUser.getEmail());
            currentUser = null;
        }
    }
}
