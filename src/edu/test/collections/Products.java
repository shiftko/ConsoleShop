package edu.test.collections;

import edu.test.entities.enums.ProductTypes;
import edu.test.entities.products.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;

final public class Products {
    private static Products instance;
    private static ArrayList<Product> products = new ArrayList<>();

    {
        products.add(new Product("Milk", ProductTypes.FOOD, 25.25, 34));
        products.add(new Product("Bread", ProductTypes.FOOD, 12.75, 32));
        products.add(new Product("Battery", ProductTypes.ELECTRONICS, 16.15, 100));
    }

    private Products() {
    }

    public static Products getInstance() {
        if (instance == null) {
            instance = new Products();
        }
        return instance;
    }

    public boolean doesNameExist(String name, ProductTypes type) {
        for (Product product : products) {
            if (product.getName().equals(name) && product.getType().name().equals(type.name())) {
                return true;
            }
        }
        return false;
    }

    public Product getProductByName(String name, ProductTypes type) throws Exception {
        for (Product product : products) {
            if (product.getName().equals(name) && product.getType().name().equals(type.name())) {
                return product;
            }
        }

        throw new Exception("Product not found");
    }

    public Product getProductByIndex(int index) throws Exception {
        try {
            return products.get(index);
        } catch (Exception e) {
            throw new Exception("Invalid product index");
        }
    }

    public void addProduct(String name, ProductTypes type, double price, int quantity) throws Exception {
        if (doesNameExist(name, type)) {
            throw new Exception("Such a product already exists");
        }

        products.add(new Product(name, type, price, quantity));
    }

    public ArrayList<String> getProductNamesList() {
        ArrayList<String> productNames = new ArrayList<>();
        for (Product product : products) {
            productNames.add(product.getName());
        }
        return productNames;
    }
}
