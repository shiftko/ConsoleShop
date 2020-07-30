package edu.test.entities.views.abs;

import edu.test.db_mock.Messages;
import edu.test.entities.models.users.User;

abstract public class AbsAdminMenu extends Menu {
    protected User user;
    protected Menu prevMenu;
    protected Messages messages;

    public AbsAdminMenu(User user, Menu prevMenu) {
        this.user = user;
        this.prevMenu = prevMenu;
        this.messages = Messages.getInstance();
    }

    @Override
    protected void handleCallbacks() throws Exception {

    }
}
