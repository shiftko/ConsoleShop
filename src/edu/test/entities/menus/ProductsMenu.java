package edu.test.entities.menus;

import edu.test.db_mock.Products;
import edu.test.entities.menus.abs.AbsUserMenu;
import edu.test.entities.menus.abs.Menu;
import edu.test.entities.users.User;
import edu.test.db_mock.enums.OrderStatus;
import edu.test.db_mock.enums.ProductTypes;

public class ProductsMenu extends AbsUserMenu {
    Products products;

    protected ProductsMenu(User user, Menu prevMenu) {
        super(user, prevMenu);
        products = Products.getInstance();

        setMainMenuItem("1.Product list");
        setMainMenuItem("2.Search product");
        setMainMenuItem("3.Orders checkout");
        setSubMenuItem("-1.back");
    }

    @Override
    protected void navigation() throws Exception {
        switch (getMenuItem()) {
            case 1 -> showProductListMenu();
            case 2 -> showProductMenu();
            case 3 -> showOrderCheckoutMenu();
            case 0 -> System.exit(0);
            case -1 -> prevMenu.run();
            default -> showMenuMessage("Unknown menu item");
        }
    }

    private void showProductListMenu() {
        new ProductListMenu(user, this).run();
    }

    private void showProductMenu() throws Exception {
        String productName = getProductName();
        ProductTypes type = getProductType();
        new ProductMenu(user, products.getProductByName(productName, type), this).run();
    }

    private void showOrderCheckoutMenu() {
        new OrdersListMenu(user, OrderStatus.IN_CART, this).run();
    }
}
