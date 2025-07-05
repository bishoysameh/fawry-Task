package com.fawry.ecommerce.model;

import java.time.LocalDate;

public class ExpirableProduct extends ShippableProduct {
    private LocalDate expiryDate;

    public ExpirableProduct(String name, double price, int quantity, 
                          boolean requiresShipping, double weight, LocalDate expiryDate) {
        super(name, price, quantity, requiresShipping, weight);
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }
}