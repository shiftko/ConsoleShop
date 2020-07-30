package edu.test.entities.models.users;

import edu.test.db_mock.enums.Roles;

public class User {
    private String name;
    private String password;
    private Roles role;
    private int age;

    public User(String name, String password, Roles role, int age) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Roles getRole() {
        return role;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
