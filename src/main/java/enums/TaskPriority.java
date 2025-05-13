package enums;

public enum TaskPriority {
    LOW, MEDIUM, HIGH;

    public static TaskPriority valueOfSafe(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        try {
            return TaskPriority.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}