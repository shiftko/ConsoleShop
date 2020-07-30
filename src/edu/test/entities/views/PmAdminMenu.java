package edu.test.entities.views;

import edu.test.entities.models.messages.Message;
import edu.test.entities.models.users.User;
import edu.test.entities.views.abs.AbsUserMenu;
import edu.test.entities.views.abs.Menu;

public class PmAdminMenu extends AbsUserMenu {

    protected PmAdminMenu(User user, Menu prevMenu) {
        super(user, prevMenu);

        setMainMenuItem("1.Write to admin");
//        setMainMenuItem("2.Check messages");
        setSubMenuItem("-1.back");
    }

    @Override
    protected void navigation() throws Exception {
        switch (getMenuItem()) {
            case 1 -> writeToAdmin();
            case 0 -> System.exit(0);
            case -1 -> prevMenu.run();
            default -> showMenuMessage("Unknown menu item");
        }
    }

    private void writeToAdmin() {
        String message = getMessageBody();
        messages.addMessage(new Message(user.getName(), message));
    }
}
