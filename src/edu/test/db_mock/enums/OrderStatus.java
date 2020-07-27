package edu.test.db_mock.enums;

public enum OrderStatus {
    IN_CART(true, "In cart"),
    CLOSED(false, "Closed");

    boolean value;
    String description;

    OrderStatus(boolean value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
