package ru.worktech.common;

import database.query.TaskQueries;
import database.query.UserQueries;
import ru.worktech.steps.TaskSteps;
import ru.worktech.steps.UserSteps;

public abstract class BaseApiTests {

    private UserSteps userSteps;
    private TaskSteps taskSteps;
    private TaskQueries taskQueries;
    private UserQueries userQueries;

    protected UserSteps getUserSteps() {
        if (userSteps == null) {
            userSteps = new UserSteps();
        }
        return userSteps;
    }

    protected TaskSteps getTaskSteps() {
        if (taskSteps == null) {
            taskSteps = new TaskSteps();
        }
        return taskSteps;
    }

    protected TaskQueries getTaskQueries() {
        if (taskQueries == null) {
            taskQueries = new TaskQueries();
        }
        return taskQueries;
    }

    protected UserQueries getUserQueries() {
        if (userQueries == null) {
            userQueries = new UserQueries();
        }
        return userQueries;
    }
}
