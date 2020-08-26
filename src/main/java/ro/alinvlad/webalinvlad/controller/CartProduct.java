package ro.alinvlad.webalinvlad.controller;

import ro.alinvlad.webalinvlad.database.Product;

public class CartProduct extends Product {

    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
