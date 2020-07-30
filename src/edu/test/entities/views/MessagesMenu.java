package edu.test.entities.views;

import edu.test.entities.models.messages.Message;
import edu.test.entities.models.users.User;
import edu.test.entities.views.abs.AbsAdminMenu;
import edu.test.entities.views.abs.Menu;

import java.util.ArrayList;

public class MessagesMenu extends AbsAdminMenu {
    protected MessagesMenu(User user, Menu prevMenu) {
        super(user, prevMenu);


        setSubMenuItem("-1.back");
    }

    @Override
    protected void handleCallbacks() throws Exception {
        super.handleCallbacks();
        resetMainMenuItem();
        compileMessagesMenu();
    }

    @Override
    protected void navigation() throws Exception {
        switch (getMenuItem()) {
            case 0 -> System.exit(0);
            case -1 -> prevMenu.run();
            default -> processUserInput(userInput);
        }
    }

    private void compileMessagesMenu() {
        ArrayList<Message> messageList = messages.getMessages();
        int size = messageList.size();
        for (int i = 0; size > i; i++) {
            int menuItemIndex = i + 1;
            Message message = messageList.get(i);
            String item = menuItemIndex + "." + message.getAuthor() + ": " + message.getMessageBody().substring(0, 20) + "...";
            setMainMenuItem(item);
        }
    }

    private void processUserInput(int userInput) throws Exception {
        int listIndex = userInput - 1;
        new MessageMenu(user, messages.getMessageByIndex(listIndex), this).run();
    }
}
