package edu.test.entities.views;

import edu.test.entities.views.abs.AbsAdminMenu;
import edu.test.entities.views.abs.Menu;
import edu.test.entities.models.users.User;

public class UsersMenu extends AbsAdminMenu {
    protected UsersMenu(User user, Menu prevMenu) {
        super(user, prevMenu);

        setSubMenuItem("-1.back");
    }

    @Override
    protected void navigation() throws Exception {
        switch (getMenuItem()) {
            case 0 -> System.exit(0);
            case -1 -> prevMenu.run();
            default -> showMenuMessage("Unknown menu item");
        }
    }
}
