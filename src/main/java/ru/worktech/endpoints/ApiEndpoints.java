package ru.worktech.endpoints;

import lombok.Getter;
import ru.worktech.models.UpdateTaskStatusRequest;

import static java.lang.String.format;

@Getter
public enum ApiEndpoints {

    REGISTRATION_ENDPOINT("/work-task/v1/registry"),
    AUTHORIZATION_ENDPOINT("/work-task/v1/login"),
    CREATE_TASK_ENDPOINT("/work-task/v1/task/create-task"),
    EDITE_TASK_ENDPOINT("/work-task/v1/task/update-task"),
    GET_TASK_BY_ID_ENDPOINT("/work-task/v1/task/{{taskId}}"),
    UPDATE_TASK_STATUS("/work-task/v1/task/update-status");

    private final String template;

    ApiEndpoints(String template) {
        this.template = template;
    }

    public String resolve(String... args) {
        String url = template.replace("{{taskId}}", "%s");
        return args.length == 0
                ? url
                : format(url, (Object[]) args);
    }

    public String getAddress() {
        return template;
    }
}