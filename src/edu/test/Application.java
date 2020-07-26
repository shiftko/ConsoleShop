package edu.test;

import edu.test.entities.menus.InitialMenu;

public class Application {
    InitialMenu initialMenu;

    public Application() {
        initialMenu = new InitialMenu();
    }

    public void run() {
        initialMenu.run();
    }
}