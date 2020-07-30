package edu.test.db_mock.enums;

public enum MessageReadStatus {
    READ(true, "The message has already been read by the admin"),
    NOT_READ(false, "The message has not yet been read by the admin");

    boolean value;
    String description;

    MessageReadStatus(boolean value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
