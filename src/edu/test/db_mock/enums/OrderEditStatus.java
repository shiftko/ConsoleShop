package edu.test.db_mock.enums;

public enum OrderEditStatus {
    EDITABLE(true, "User can edit order"),
    COMPLETED(false, "Admin can confirm purchase");

    boolean value;
    String description;

    OrderEditStatus(boolean value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
