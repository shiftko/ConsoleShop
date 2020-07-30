package edu.test.entities.menus;

import edu.test.entities.menus.abs.AbsUserMenu;
import edu.test.entities.menus.abs.Menu;
import edu.test.entities.orders.Order;
import edu.test.entities.users.User;
import edu.test.db_mock.enums.OrderStatus;

import java.util.ArrayList;

public class OrdersListMenu extends AbsUserMenu {
    OrderStatus status;

    protected OrdersListMenu(User user, OrderStatus status, Menu prevMenu) {
        super(user, prevMenu);
        this.status = status;

        setMenuNamePrefix(status.getDescription());
        setSubMenuItem("-1.back");
    }

    @Override
    protected void handleCallbacks() throws Exception {
        super.handleCallbacks();
        compileOrdersListMenu();
    }

    @Override
    protected void navigation() throws Exception {
        int userInput = getMenuItem();
        switch (userInput) {
            case 0 -> System.exit(0);
            case -1 -> prevMenu.run();
            default -> processUserInput(userInput);
        }
    }

    private void compileOrdersListMenu() {
        resetMainMenuItem();
        ArrayList<Order> ordersList = getOrderList();
        int size = ordersList.size();
        if (size > 0) {
            for (int i = 0; size > i; i++) {
                int menuItemIndex = i + 1;
                Order order = ordersList.get(i);
                setMainMenuItem(
                        menuItemIndex + "."
                                + order.getProductName() + "/"
                                + order.getProductType().name().toLowerCase() + "/"
                                + order.getQuantity() + "/"
                                + order.getEditStatus().name()
                );
            }
        } else {
            setMainMenuItem("There are no orders in the cart");
        }
    }

    private ArrayList<Order> getOrderList() {
        ArrayList<Order> ordersList = new ArrayList<>();
        switch (status) {
            case IN_CART -> ordersList = orders.getOrdersInCartByUser(user.getName());
            case CLOSED -> ordersList = orders.getClosedOrdersByUser(user.getName());
        }
        return ordersList;
    }

    private void processUserInput(int userInput) {
        int listIndex = userInput - 1;
        switch (status) {
            case IN_CART -> new InCartOrderMenu(user, getOrderList().get(listIndex), this).run();
            case CLOSED -> new ClosedOrderMenu(user, getOrderList().get(listIndex), this).run();
        }
    }
}
