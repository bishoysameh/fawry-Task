package com.fawry.ecommerce.model;

public abstract class ShippableProduct extends Product implements Shippable {
    private double weight;

    public ShippableProduct(String name, double price, int quantity, 
                          boolean requiresShipping, double weight) {
        super(name, price, quantity, requiresShipping);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}