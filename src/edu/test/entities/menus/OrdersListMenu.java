package edu.test.entities.menus;

import edu.test.entities.enums.OrderStatus;
import edu.test.entities.menus.abs.AbsUserMenu;
import edu.test.entities.menus.abs.Menu;
import edu.test.entities.orders.Order;
import edu.test.entities.users.User;

import java.util.ArrayList;

public class OrdersListMenu extends AbsUserMenu {
    OrderStatus status;

    protected OrdersListMenu(User user, OrderStatus status, Menu prevMenu) {
        super(user, prevMenu);
        this.status = status;

        setMenuNamePrefix(status.getDescription());
        compileOrdersListMenu();
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

    private void compileOrdersListMenu() {
        ArrayList<Order> ordersList = new ArrayList<>();
        switch (status) {
            case IN_CART -> ordersList = orders.getOrdersInCartByUser(user.getName());
            case CLOSED -> ordersList = orders.getClosedOrdersByUser(user.getName());
        }
        int size = ordersList.size();
        for (int i = 0; size > i; i++) {
            int menuItemIndex = i + 1;
            Order order = ordersList.get(i);
            setMainMenuItem(menuItemIndex + "." + order.getProductName() + "/" + order.getProductType().name().toLowerCase() + "/" + order.getQuantity());
        }
    }
}
