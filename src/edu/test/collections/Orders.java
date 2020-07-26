package edu.test.collections;

import edu.test.entities.enums.ProductTypes;
import edu.test.entities.orders.Order;

import java.util.ArrayList;

final public class Orders {
    private static Orders instance;
    private static ArrayList<Order> orders = new ArrayList<>();

    {
        orders.add(new Order("ivan", "Milk", ProductTypes.FOOD, 2, true));
        orders.add(new Order("ivan", "Battery", ProductTypes.ELECTRONICS, 4, true));
        orders.add(new Order("ivan", "Battery", ProductTypes.ELECTRONICS, 4, false));
        orders.add(new Order("Roman", "Milk", ProductTypes.FOOD, 2, true));
        orders.add(new Order("Roman", "Battery", ProductTypes.ELECTRONICS, 4, true));
        orders.add(new Order("Roman", "Battery", ProductTypes.ELECTRONICS, 4, false));
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
            if (order.getLogin().equals(userName) && order.isInCart()) {
                ordersInCart.add(order);
            }
        }
        return ordersInCart;
    }

    public static ArrayList<Order> getClosedOrdersByUser(String userName) {
        ArrayList<Order> ordersInCart = new ArrayList<>();
        for (Order order : orders) {
            if (order.getLogin().equals(userName) && !order.isInCart()) {
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
            orders.add(new Order(login, productName, productType, 1, true));
        }
    }

    private boolean checkIfActiveOrderExists(String login, String productName, ProductTypes productType) {
        for (Order order : orders) {
            if (order.getLogin().equals(login)
                    && order.getProductName().equals(productName)
                    && order.getProductType().equals(productType)
                    && order.isInCart()
            ) {
                return true;
            }
        }
        return false;
    }

    private Order getOrder(String login, String productName, ProductTypes productType) throws Exception {
        for (Order order : orders) {
            if (order.getLogin().equals(login)
                    && order.getProductName().equals(productName)
                    && order.getProductType().equals(productType)
                    && order.isInCart()
            ) {
                return order;
            }
        }

        throw new Exception("Order not found");
    }

    private void updateOrder(Order order) throws Exception {
        int size = orders.size();
        for (int i = 0; size > 1; i++) {
            Order previousOrder = orders.get(i);
            if (previousOrder.equals(order)) {
                orders.set(i, order);
                return;
            }
        }
        throw new Exception("Order was not updated");
    }
}
