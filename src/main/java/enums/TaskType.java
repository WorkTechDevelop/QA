package enums;

public enum TaskType {
    BUG, FEATURE, TASK;

    public static TaskType valueOfSafe(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        try {
            return TaskType.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}

