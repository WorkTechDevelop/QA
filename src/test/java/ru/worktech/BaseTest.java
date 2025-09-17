package ru.worktech;

import database.connection.DbQueryPreparer;
import database.query.UserQuery;
import org.testng.annotations.AfterClass;

public abstract class BaseTest {

    protected final UserQuery userQuery = new UserQuery();

    @AfterClass
    public void tearDown() {
        DbQueryPreparer.ConnectionFactory.closeConnection();
    }
}