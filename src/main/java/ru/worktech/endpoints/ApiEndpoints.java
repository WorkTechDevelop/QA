package ru.worktech.endpoints;

import lombok.Getter;

@Getter
public enum ApiEndpoints {

    REGISTRATION_ENDPOINT("/work-task/v1/registration/registry"),
    AUTHORIZATION_ENDPOINT("/work-task/v1/auth/login"),
    CREATE_TASK_ENDPOINT("/work-task/v1/task/create-task"),
    EDITE_TASK_ENDPOINT("/work-task/v1/task/update-task"),
    UPDATE_TASK_STATUS("/work-task/v1/task/update-status");

    private final String template;

    ApiEndpoints(String template) {
        this.template = template;
    }

    public String getAddress() {
        return template;
    }
}