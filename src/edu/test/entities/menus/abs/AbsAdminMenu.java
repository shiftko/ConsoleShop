package edu.test.entities.menus.abs;

import edu.test.entities.users.User;

abstract public class AbsAdminMenu extends Menu {
    protected User user;
    protected Menu prevMenu;

    public AbsAdminMenu(User user, Menu prevMenu) {
        this.user = user;
        this.prevMenu = prevMenu;
    }

    @Override
    protected void handleCallbacks() throws Exception {

    }
}
