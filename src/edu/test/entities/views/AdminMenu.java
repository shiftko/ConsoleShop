package edu.test.entities.views;

import edu.test.entities.views.abs.AbsAdminMenu;
import edu.test.entities.views.abs.Menu;
import edu.test.entities.models.users.User;

public class AdminMenu extends AbsAdminMenu {
    protected AdminMenu(User user, Menu prevMenu) {
        super(user, prevMenu);

        setMenuNamePrefix(user.getName());
        setMainMenuItem("1.Users menu");
        setMainMenuItem("2.Order menu");
        setMainMenuItem("3.Products menu");
        setMainMenuItem("4.Messages menu");
        setSubMenuItem("-1.back");
    }

    @Override
    protected void navigation() throws Exception {
        switch (getMenuItem()) {
            case 1 -> showUsersMenu();
            case 2 -> showOrdersMenu();
            case 3 -> showProductsMenu();
            case 4 -> showMessagesMenu();
            case 0 -> System.exit(0);
            case -1 -> prevMenu.run();
            default -> showMenuMessage("Unknown menu item");
        }
    }

    private void showUsersMenu() {
        new UsersMenu(user, this).run();
    }

    private void showOrdersMenu() {
        new OrdersMenu(user, this).run();
    }

    private void showProductsMenu() {
        new ProductsMenu(user, this).run();
    }

    private void showMessagesMenu() {
        new MessagesMenu(user, this).run();
    }
}
