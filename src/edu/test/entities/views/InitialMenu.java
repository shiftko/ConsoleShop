package edu.test.entities.views;

import edu.test.db_mock.Users;
import edu.test.entities.views.abs.Menu;
import edu.test.entities.models.users.User;

public class InitialMenu extends Menu {
    Users users;

    public InitialMenu() {
        users = Users.getInstance();

        setMainMenuItem("1.Log in");
        setMainMenuItem("2.Sign in");
    }

    @Override
    protected void handleCallbacks() {

    }

    @Override
    protected void navigation() throws Exception {
        switch (getMenuItem()) {
            case 1 -> logIn();
            case 2 -> signIn();
            case 0 -> System.exit(0);
            default -> showMenuMessage("Unknown menu item");
        }
    }

    private void logIn() throws Exception {
        String login = getLogin();
        String password = getPassword();
        if (users.doesExist(login, password)) {
            defineNextMenu(users.getUser(login, password)).run();
        } else {
            showMenuMessage("User not found");
        }
    }

    private void signIn() throws Exception {
        String login = getLogin();
        String password = getPassword();
        int age = getAge();
        if (users.doesExist(login, password)) {
            showMenuMessage("Such user already exists");
        } else {
            users.addUser(login, password, age);
            showMenuMessage("New user: " + login + " has been created");
        }
        showMenuMessage("Use his credentials to log in");
    }

    private Menu defineNextMenu(User user) throws Exception {
        return switch (user.getRole()) {
            case ADMIN -> new AdminMenu(user, this);
            case USER -> new UserMenu(user, this);
        };
    }
}