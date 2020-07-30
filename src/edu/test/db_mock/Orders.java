package edu.test.db_mock;

import edu.test.entities.models.orders.Order;
import edu.test.db_mock.enums.OrderEditStatus;
import edu.test.db_mock.enums.OrderStatus;
import edu.test.db_mock.enums.ProductTypes;

import java.util.ArrayList;

final public class Orders {
    private static Orders instance;
    private static ArrayList<Order> orders = new ArrayList<>();

    static {
        orders.add(new Order("ivan", "Milk", ProductTypes.FOOD, 2, OrderStatus.IN_CART, OrderEditStatus.EDITABLE));
        orders.add(new Order("ivan", "Battery", ProductTypes.ELECTRONICS, 4, OrderStatus.IN_CART, OrderEditStatus.EDITABLE));
        orders.add(new Order("ivan", "Battery", ProductTypes.ELECTRONICS, 4, OrderStatus.CLOSED, OrderEditStatus.EDITABLE));
        orders.add(new Order("Roman", "Milk", ProductTypes.FOOD, 2, OrderStatus.IN_CART, OrderEditStatus.EDITABLE));
        orders.add(new Order("Roman", "Battery", ProductTypes.ELECTRONICS, 4, OrderStatus.IN_CART, OrderEditStatus.EDITABLE));
        orders.add(new Order("Roman", "Battery", ProductTypes.ELECTRONICS, 4, OrderStatus.CLOSED, OrderEditStatus.EDITABLE));
    }

    private Orders() {
    }

    public static Orders getInstance() {
        if (instance == null) {
            instance = new Orders();
        }
        return instance;
    }

    public static ArrayList<Order> getOrdersInCartByUser(String userName) {
        ArrayList<Order> ordersInCart = new ArrayList<>();
        for (Order order : orders) {
            if (order.getLogin().equals(userName) && order.getOrderStatus().equals(OrderStatus.IN_CART)) {
                ordersInCart.add(order);
            }
        }
        return ordersInCart;
    }

    public static ArrayList<Order> getClosedOrdersByUser(String userName) {
        ArrayList<Order> ordersInCart = new ArrayList<>();
        for (Order order : orders) {
            if (order.getLogin().equals(userName) && order.getOrderStatus().equals(OrderStatus.CLOSED)) {
                ordersInCart.add(order);
            }
        }
        return ordersInCart;
    }

    public void addOrder(String login, String productName, ProductTypes productType, int quantity) throws Exception {
        if (checkIfActiveOrderExists(login, productName, productType)) {
            Order order = getOrder(login, productName, productType);
            order.setQuantity(order.getQuantity() + quantity);
            updateOrder(order);
        } else {
            orders.add(new Order(login, productName, productType, quantity, OrderStatus.IN_CART, OrderEditStatus.EDITABLE));
        }
    }

    public boolean checkIfActiveOrderExists(String login, String productName, ProductTypes productType) {
        for (Order order : orders) {
            if (
                    order.getLogin().equals(login)
                            && order.getProductName().equals(productName)
                            && order.getProductType().equals(productType)
                            && order.getOrderStatus().equals(OrderStatus.IN_CART)
            ) {
                return true;
            }
        }
        return false;
    }

    public Order getOrder(String login, String productName, ProductTypes productType) throws Exception {
        for (Order order : orders) {
            if (
                    order.getLogin().equals(login)
                            && order.getProductName().equals(productName)
                            && order.getProductType().equals(productType)
                            && order.getOrderStatus().equals(OrderStatus.IN_CART)
            ) {
                return order;
            }
        }

        throw new Exception("Order not found");
    }

    public void updateOrder(Order order) throws Exception {
        int size = orders.size();
        for (int i = 0; size > i; i++) {
            Order previousOrder = orders.get(i);
            if (previousOrder.equals(order)) {
                orders.set(i, order);
                return;
            }
        }
        throw new Exception("The order was not updated");
    }

    public void deleteOrder(Order order) throws Exception {
        int size = orders.size();
        for (int i = 0; size > i; i++) {
            Order previousOrder = orders.get(i);
            if (previousOrder.equals(order)) {
                orders.remove(i);
                return;
            }
        }
        throw new Exception("The order was not deleted");
    }
}
