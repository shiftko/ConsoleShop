package edu.test.entities.views;

import edu.test.entities.views.abs.AbsUserMenu;
import edu.test.entities.views.abs.Menu;
import edu.test.entities.models.orders.Order;
import edu.test.entities.models.users.User;
import edu.test.db_mock.enums.OrderStatus;

class ClosedOrderMenu extends AbsUserMenu {
    final OrderStatus status = OrderStatus.CLOSED;
    Order order;

    protected ClosedOrderMenu(User user, Order order, Menu prevMenu) {
        super(user, prevMenu);
        this.order = order;

        setMenuNameSuffix(order.getProductName() + "/" + order.getProductType().name().toLowerCase() + "/" + order.getQuantity());

        setMainMenuItem("You cannot interact with closed orders");
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
}
