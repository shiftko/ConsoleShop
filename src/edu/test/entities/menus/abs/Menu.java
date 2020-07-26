package edu.test.entities.menus.abs;

import edu.test.entities.enums.ProductTypes;

import java.util.ArrayList;
import java.util.Scanner;

abstract public class Menu {
    private ArrayList<String> mainItems = new ArrayList<>();
    private ArrayList<String> subItems = new ArrayList<>();
    private ArrayList<String> informationFieldItems = new ArrayList<>();

    {
        subItems.add("0.exit");
    }

    private String menuName = "";
    private String menuNamePrefix = "";
    private String menuDelimiter = "";
    private String menuEnding = "";
    private String menu = "";

    protected Scanner scanner;

    protected Menu() {
        scanner = new Scanner(System.in);
    }

    protected void setMainMenuItem(String item) {
        mainItems.add(item);
    }

    protected void setSubMenuItem(String item) {
        subItems.add(item);
    }

    protected void resetInformationField() {
        informationFieldItems.clear();
    }

    protected void setInformationFieldItem(String item) {
        informationFieldItems.add(item);
    }

    private void setMenuPartials() {
        if (menuNamePrefix.trim().length() > 0) {
            menuName = "--> " + menuNamePrefix + ": " + this.getClass().getSimpleName() + " <--";
        } else {
            menuName = "--> " + this.getClass().getSimpleName() + " <--";
        }

        menuEnding = "";
        menuDelimiter = "";
        int length = menuName.length();
        for (int i = 0; length > i; i++) {
            menuDelimiter += "-";
            menuEnding += "*";
        }
    }

    protected void setMenuNamePrefix(String prefix) {
        menuNamePrefix = prefix;
    }

    private void compileMenu() {
        setMenuPartials();
        menu = "";
        menu += menuEnding + "\n";
        menu += menuName + "\n";
        for (String item : mainItems) {
            menu += item + "\n";
        }
        if (informationFieldItems.size() > 0) {
            menu += menuDelimiter + "\n";
            for (String item : informationFieldItems) {
                menu += item + "\n";
            }
        }
        menu += menuDelimiter + "\n";
        for (String item : subItems) {
            menu += item + "\n";
        }
    }

    protected void showMenu() {
        System.out.println(menu);
    }

    protected void showMenuMessage(String message) {
        System.out.println("--> " + message);
    }

    abstract protected void handleCallbacks() throws Exception;

    abstract protected void navigation() throws Exception;

    public void run() {
        while (true) {
            try {
                handleCallbacks();
                compileMenu();
                showMenu();
                navigation();
            } catch (Exception e) {
                showMenuMessage(e.getMessage());
            }
        }
    }

    protected String getLogin() {
        System.out.print("Login: ");
        return scanner.nextLine().trim();
    }

    protected String getPassword() {
        System.out.print("Password: ");
        return scanner.nextLine().trim();
    }

    protected String getProductName() {
        System.out.print("Product name: ");
        return scanner.nextLine().trim();
    }

    protected ProductTypes getProductType() throws Exception {
        String availableTypes = "Available types are: ";
        for (ProductTypes type : ProductTypes.values()) {
            availableTypes += type.name().toLowerCase() + ", ";
        }
        while (true) {
            System.out.println(availableTypes.substring(0, availableTypes.length() - 2));
            System.out.print("Type: ");
            try {
                return ProductTypes.valueOf(scanner.nextLine().trim().toUpperCase());
            } catch (Exception e) {
                showMenuMessage("Error getting product type, the type must be one of the available types");
            }
        }
    }

    protected int getAge() throws Exception {
        System.out.print("Age: ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            throw new Exception("Error getting user age, the age must be a number");
        }
    }

    protected int getMenuItem() throws Exception {
        System.out.print("Your input: ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            throw new Exception("Error getting menu item, the input must be a number");
        }
    }
}
