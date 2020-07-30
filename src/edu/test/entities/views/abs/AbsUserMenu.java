package edu.test.entities.views.abs;

import edu.test.db_mock.Messages;
import edu.test.db_mock.Orders;
import edu.test.db_mock.Products;
import edu.test.entities.models.orders.Order;
import edu.test.entities.models.users.User;

import java.util.ArrayList;

public abstract class AbsUserMenu extends Menu {
    protected User user;
    protected Menu prevMenu;
    protected Products products;
    protected Orders orders;
    protected Messages messages;

    protected AbsUserMenu(User user, Menu prevMenu) {
        this.user = user;
        this.prevMenu = prevMenu;
        this.products = Products.getInstance();
        this.orders = Orders.getInstance();
        this.messages = Messages.getInstance();
    }

    @Override
    protected void handleCallbacks() throws Exception {
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
