package edu.test.db_mock.enums;

public enum ProductTypes {
    FOOD(1),
    ELECTRONICS(2);

    int type;

    ProductTypes(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
