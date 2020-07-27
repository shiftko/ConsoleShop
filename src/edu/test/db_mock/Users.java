package edu.test.db_mock;

import edu.test.entities.users.User;
import edu.test.db_mock.enums.Roles;

import java.util.ArrayList;

final public class Users {
    private static Users instance;
    private static ArrayList<User> users = new ArrayList<>();

    static {
        users.add(new User("Ivan", "test", Roles.ADMIN, 34));
        users.add(new User("Olga", "test", Roles.USER, 32));
        users.add(new User("Roman", "test", Roles.USER, 7));
    }

    private Users() {
    }

    public static Users getInstance() {
        if (instance == null) {
            instance = new Users();
        }
        return instance;
    }

    public static boolean doesExist(String login, String password) {
        for (User user : users) {
            if (user.getName().equals(login) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public User getUser(String login, String password) throws Exception {
        for (User user : users) {
            if (user.getName().equals(login) && user.getPassword().equals(password)) {
                return user;
            }
        }

        throw new Exception("User not found");
    }

    public void addUser(String login, String password, int age) {
        if (!doesExist(login, password)) {
            users.add(new User(login, password, Roles.USER, age));
        }
    }
}
