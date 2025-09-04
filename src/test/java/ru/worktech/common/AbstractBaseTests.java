package ru.worktech.common;

import database.query.DeleteTask;
import ru.worktech.steps.TaskSteps;
import ru.worktech.steps.UserSteps;

public class AbstractBaseTests {
    protected UserSteps userSteps = new UserSteps();
    protected TaskSteps taskSteps = new TaskSteps();
    protected DeleteTask deleteTask = new DeleteTask();
}
