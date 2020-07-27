package edu.test.entities.orders;

import edu.test.enums.ProductTypes;

public class Order {
    String login;
    String productName;
    ProductTypes productType;
    int quantity;
    boolean inCart;

    public Order(String login, String productName, ProductTypes productType, int quantity, boolean inCart) {
        this.login = login;
        this.productName = productName;
        this.productType = productType;
        this.quantity = quantity;
        this.inCart = inCart;
    }

    public String getLogin() {
        return login;
    }

    public String getProductName() {
        return productName;
    }

    public ProductTypes getProductType() {
        return productType;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isInCart() {
        return inCart;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setInCart(boolean inCart) {
        this.inCart = inCart;
    }
}
