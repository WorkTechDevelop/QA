package ru.worktech.common;

import database.dto.UserDTO;
import database.utils.UserFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.sql.SQLException;

public class FreshUserTest extends AbstractBaseTests{
    protected UserDTO currentUser;

    @BeforeMethod
    public void createFreshUser() throws SQLException {
        currentUser = UserFactory.create();
    }

    @AfterMethod
    public void deleteFreshUser() throws SQLException {
        if (currentUser != null) {
            UserFactory.delete(currentUser.getEmail());
        }
    }
}
