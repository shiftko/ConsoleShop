package edu.test.entities.views;

import edu.test.entities.views.abs.AbsUserMenu;
import edu.test.entities.views.abs.Menu;
import edu.test.entities.models.orders.Order;
import edu.test.entities.models.users.User;
import edu.test.db_mock.enums.OrderStatus;

import java.util.ArrayList;

public class OrdersMenu extends AbsUserMenu {

    protected OrdersMenu(User user, Menu prevMenu) {
        super(user, prevMenu);

        setSubMenuItem("-1.back");
    }

    @Override
    protected void handleCallbacks() throws Exception {
        super.handleCallbacks();
        resetMainMenuItem();
        showClosedOrdersInfo();
        showOrdersInCartInfo();
    }

    @Override
    protected void navigation() throws Exception {
        switch (getMenuItem()) {
            case 1 -> showClosedOrders();
            case 2 -> showOrdersInCart();
            case 0 -> System.exit(0);
            case -1 -> prevMenu.run();
            default -> showMenuMessage("Unknown menu item");
        }
    }

    private void showClosedOrdersInfo() {
        ArrayList<Order> closedOrders = orders.getClosedOrdersByUser(user.getName());
        if (closedOrders.size() > 0) {
            setMainMenuItem("1.View closed order(s) / amount:" + closedOrders.size());
        } else {
            setMainMenuItem("x.You have no closed order(s)");
        }
    }

    private void showOrdersInCartInfo() {
        ArrayList<Order> ordersInCart = orders.getOrdersInCartByUser(user.getName());
        if (ordersInCart.size() > 0) {
            setMainMenuItem("2.View order(s) in the cart / amount:" + ordersInCart.size());
        } else {
            setMainMenuItem("x.You have no order(s) in the cart");
        }
    }

    private void showClosedOrders() {
        new OrdersListMenu(user, OrderStatus.CLOSED, this).run();
    }

    private void showOrdersInCart() {
        new OrdersListMenu(user, OrderStatus.IN_CART, this).run();
    }
}
