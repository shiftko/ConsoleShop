package edu.test.entities.models.orders;

import edu.test.db_mock.enums.OrderEditStatus;
import edu.test.db_mock.enums.OrderStatus;
import edu.test.db_mock.enums.ProductTypes;

public class Order {
    String login;
    String productName;
    ProductTypes productType;
    int quantity;
    OrderStatus orderStatus;
    OrderEditStatus editStatus;

    public Order(
            String login
            , String productName
            , ProductTypes productType
            , int quantity
            , OrderStatus orderStatus
            , OrderEditStatus editStatus
    ) {
        this.login = login;
        this.productName = productName;
        this.productType = productType;
        this.quantity = quantity;
        this.orderStatus = orderStatus;
        this.editStatus = editStatus;
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

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public OrderEditStatus getEditStatus() {
        return editStatus;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setEditStatus(OrderEditStatus editStatus) {
        this.editStatus = editStatus;
    }
}
