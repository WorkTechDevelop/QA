package enums;

public enum TaskStatus {
    TODO, IN_PROGRESS, REVIEW, DONE;

    public static TaskStatus valueOfSafe(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        try {
            return TaskStatus.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}