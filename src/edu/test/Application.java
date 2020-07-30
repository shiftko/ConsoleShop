package edu.test;

import edu.test.entities.views.InitialMenu;

public class Application {
    InitialMenu initialMenu;

    public Application() {
        initialMenu = new InitialMenu();
    }

    public void run() {
        initialMenu.run();
    }
}