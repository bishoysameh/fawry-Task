package com.fawry.ecommerce.model;

public class NonExpirableProduct extends Product {
    public NonExpirableProduct(String name, double price, int quantity, 
                             boolean requiresShipping) {
        super(name, price, quantity, requiresShipping);
    }

    @Override
    public boolean isExpired() {
        return false;
    }
}