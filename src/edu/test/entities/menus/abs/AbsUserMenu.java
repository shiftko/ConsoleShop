package edu.test.entities.menus.abs;

import edu.test.db_mock.Orders;
import edu.test.entities.orders.Order;
import edu.test.entities.users.User;

import java.util.ArrayList;

public abstract class AbsUserMenu extends Menu {
    protected User user;
    protected Menu prevMenu;
    protected Orders orders;

    protected AbsUserMenu(User user, Menu prevMenu) {
        this.user = user;
        this.prevMenu = prevMenu;
        this.orders = Orders.getInstance();
    }

    @Override
    protected void handleCallbacks() {
        showOrdersInCartDetailedInfo();
    }

    private void showOrdersInCartDetailedInfo() {
        resetInformationField();
        ArrayList<Order> ordersInCart = orders.getOrdersInCartByUser(user.getName());
        if (ordersInCart.size() > 0) {
            int productsQuantity = 0;
            for (Order order : ordersInCart) {
                productsQuantity += order.getQuantity();
            }
            setInformationFieldItem("You have: " + ordersInCart.size() + " orders (" + productsQuantity + " products) in the cart");
        }
    }
}
