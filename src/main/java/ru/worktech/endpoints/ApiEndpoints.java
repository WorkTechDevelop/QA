package ru.worktech.endpoints;

import lombok.Getter;

import static java.lang.String.format;

@Getter
public enum ApiEndpoints {

    REGISTRATION_ENDPOINT("/work-task/v1/registry"),
    AUTHORIZATION_ENDPOINT("/work-task/v1/login"),
    CREATE_TASK_ENDPOINT("/work-task/v1/task/create-task"),
    EDITE_TASK_ENDPOINT("/work-task/v1/task/update-task"),
    GET_TASK_BY_ID_ENDPOINT("/work-task/v1/task/{{taskId}}"),
    GET_TASK_IN_PROJECT_ENDPOINT("/work-task/v1/task/tasks-in-project?project-id-929"),
    GET_TASK_BY_ID_PROJECT_ENDPOINT("/work-task/v1/task/project-tasks/project-id-929");

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

    public String resolveProjectEndpoint(String... args) {
        String url = template.replace("{{taskId}}", "%s");
        return args.length == 0
                ? url
                : format(url, (Object[]) args);
    }

    public String getAddress() {
        return template;
    }
}