package edu.test.entities.menus;

import edu.test.collections.Products;
import edu.test.entities.menus.abs.AbsUserMenu;
import edu.test.entities.menus.abs.Menu;
import edu.test.entities.users.User;

import java.util.ArrayList;

public class ProductListMenu extends AbsUserMenu {
    Products products;

    protected ProductListMenu(User user, Menu prevMenu) {
        super(user, prevMenu);
        this.products = products.getInstance();

        compileProductsListMenu();
        setSubMenuItem("-1.back");
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

    private void compileProductsListMenu() {
        ArrayList<String> productNamesList = products.getProductNamesList();
        int size = products.getProductNamesList().size();
        for (int i = 0; size > i; i++) {
            int menuItemIndex = i + 1;
            setMainMenuItem(menuItemIndex + "." + productNamesList.get(i));
        }
    }

    private void processUserInput(int userInput) throws Exception {
        int listIndex = userInput - 1;
        new ProductMenu(user, products.getProductByIndex(listIndex), this).run();
    }
}
