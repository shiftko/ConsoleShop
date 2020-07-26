package edu.test.entities.menus;

import edu.test.entities.enums.OrderStatus;
import edu.test.entities.menus.abs.AbsUserMenu;
import edu.test.entities.menus.abs.Menu;
import edu.test.entities.orders.Order;
import edu.test.entities.users.User;

import java.util.ArrayList;

public class OrdersMenu extends AbsUserMenu {

    protected OrdersMenu(User user, Menu prevMenu) {
        super(user, prevMenu);

        showClosedOrdersInfo();
        showOrdersInCartInfo();
        setSubMenuItem("-1.back");
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
        ArrayList<Order> ordersInCart = orders.getClosedOrdersByUser(user.getName());
        if (ordersInCart.size() > 0) {
            setMainMenuItem("1.View closed order(s) / " + ordersInCart.size());
        }
    }

    private void showOrdersInCartInfo() {
        ArrayList<Order> ordersInCart = orders.getOrdersInCartByUser(user.getName());
        if (ordersInCart.size() > 0) {
            setMainMenuItem("2.View order(s) in the cart / " + ordersInCart.size());
        }
    }

    private void showClosedOrders() {
        new OrdersListMenu(user, OrderStatus.CLOSED, this).run();
    }

    private void showOrdersInCart() {
        new OrdersListMenu(user, OrderStatus.IN_CART, this).run();
    }
}
