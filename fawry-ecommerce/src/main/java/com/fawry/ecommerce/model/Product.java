package com.fawry.ecommerce.model;

public abstract class Product {
    private String name;
    private double price;
    private int quantity;
    private boolean requiresShipping;

    public Product(String name, double price, int quantity, boolean requiresShipping) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.requiresShipping = requiresShipping;
    }

    public abstract boolean isExpired();
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public boolean requiresShipping() { return requiresShipping; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}