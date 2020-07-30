package edu.test.entities.views;

import edu.test.entities.views.abs.AbsUserMenu;
import edu.test.entities.views.abs.Menu;
import edu.test.entities.models.products.Product;
import edu.test.entities.models.users.User;

public class ProductMenu extends AbsUserMenu {
    protected Product product;

    protected ProductMenu(User user, Product product, Menu prevMenu) {
        super(user, prevMenu);
        this.product = product;

        setMenuNamePrefix(product.getName());
        setMainMenuItem("1.Add to cart");
        setSubMenuItem("-1.back");
    }

    @Override
    protected void navigation() throws Exception {
        switch (getMenuItem()) {
            case 1 -> performAddToCart();
            case 0 -> System.exit(0);
            case -1 -> prevMenu.run();
            default -> showMenuMessage("Unknown menu item");
        }
    }

    private void performAddToCart() throws Exception {
        orders.addOrder(user.getName(), product.getName(), product.getType(), 1);
    }
}
