package edu.test.entities.menus;

import edu.test.entities.menus.abs.AbsUserMenu;
import edu.test.entities.menus.abs.Menu;
import edu.test.entities.users.User;

public class PmAdminMenu extends AbsUserMenu {
    protected PmAdminMenu(User user, Menu prevMenu) {
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
