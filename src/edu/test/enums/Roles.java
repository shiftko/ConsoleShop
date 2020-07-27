package edu.test.enums;

public enum Roles {
    ADMIN(1),
    USER(0);

    int role;

    Roles(int role) {
        this.role = role;
    }

    public int getRole() {
        return role;
    }
}
