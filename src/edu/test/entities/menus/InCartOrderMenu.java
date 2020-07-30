package edu.test.entities.menus;

import edu.test.entities.menus.abs.AbsUserMenu;
import edu.test.entities.menus.abs.Menu;
import edu.test.entities.orders.Order;
import edu.test.entities.users.User;
import edu.test.db_mock.enums.OrderEditStatus;
import edu.test.db_mock.enums.OrderStatus;

class InCartOrderMenu extends AbsUserMenu {
    final OrderStatus status = OrderStatus.IN_CART;
    Order order;

    protected InCartOrderMenu(User user, Order order, Menu prevMenu) {
        super(user, prevMenu);
        this.order = order;

        setMenuNameSuffix(order.getProductName() + "/" + order.getProductType().name().toLowerCase() + "/" + order.getQuantity());

        setMainMenuItem("1.Change order quantity");
        setMainMenuItem("2.Confirm order");
        setMainMenuItem("3.Delete order");
        setSubMenuItem("-1.back");
    }

    @Override
    protected void handleCallbacks() throws Exception {
        super.handleCallbacks();
        updateOrderInfo();
    }

    private void updateOrderInfo() throws Exception {
        if (orders.checkIfActiveOrderExists(order.getLogin(), order.getProductName(), order.getProductType())) {
            Order updatedOrder = orders.getOrder(order.getLogin(), order.getProductName(), order.getProductType());
            setMenuNameSuffix(
                    updatedOrder.getProductName() + "/"
                            + updatedOrder.getProductType().name().toLowerCase() + "/"
                            + updatedOrder.getQuantity() + "/"
                            + order.getEditStatus().name()
            );
        } else {
            prevMenu.run();
        }
    }

    @Override
    protected void navigation() throws Exception {
        switch (getMenuItem()) {
            case 1 -> changeProductQuantity();
            case 2 -> confirmOrder();
            case 3 -> deleteOrder();
            case 0 -> System.exit(0);
            case -1 -> prevMenu.run();
            default -> showMenuMessage("Unknown menu item");
        }
    }

    private void changeProductQuantity() throws Exception {
        int quantity = getProductQuantity();
        order.setQuantity(quantity);
        orders.updateOrder(order);
    }

    private void confirmOrder() {
        order.setEditStatus(OrderEditStatus.COMPLETED);
    }

    private void deleteOrder() throws Exception {
        orders.deleteOrder(order);
    }
}
