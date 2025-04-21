package com.asacxyz.peekr.model;

public enum TaskStatus {
    NOT_INITIALIZED, COMPLETED;

    public static final TaskStatus getElement(String element) {
        for (TaskStatus t : TaskStatus.values()) {
            if (t.name().equalsIgnoreCase(element)) {
                return t;
            }
        }

        throw new RuntimeException("Element not found.");
    }
}
