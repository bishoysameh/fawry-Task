package com.fawry.ecommerce.service;

import com.fawry.ecommerce.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ECommerceService {
    private static final double SHIPPING_FEE_PER_KG = 30.0;
    private ShippingService shippingService = new ShippingService();

    public void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cannot checkout with empty cart");
        }

        Map<Product, Integer> items = cart.getItems();
        double subtotal = 0;
        double shippingFees = 0;
        List<Shippable> shippableItems = new ArrayList<>();

        // Calculate subtotal and collect shippable items
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            
            if (product.getQuantity() < quantity) {
                throw new IllegalStateException("Not enough stock for " + product.getName());
            }
            
            if (product.isExpired()) {
                throw new IllegalStateException(product.getName() + " is expired");
            }
            
            subtotal += product.getPrice() * quantity;
            
            if (product.requiresShipping() && product instanceof Shippable) {
                shippableItems.add((Shippable) product);
                shippingFees += ((Shippable) product).getWeight() * SHIPPING_FEE_PER_KG;
            }
        }

        double totalAmount = subtotal + shippingFees;
        
        if (customer.getBalance() < totalAmount) {
            throw new IllegalStateException("Insufficient balance");
        }

        if (!shippableItems.isEmpty()) {
            shippingService.shipItems(shippableItems);
        }

        printReceipt(items, subtotal, shippingFees, totalAmount, customer);

        updateInventory(items);
        customer.deductBalance(totalAmount);
    }

    private void printReceipt(Map<Product, Integer> items, double subtotal, double shippingFees, 
                            double totalAmount, Customer customer) {
        System.out.println("** Checkout receipt **");
        
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.printf("%dx %s    %.0f%n", quantity, product.getName(), product.getPrice() * quantity);
        }
        
        System.out.println("---");
        System.out.printf("Subtotal    %.0f%n", subtotal);
        System.out.printf("Shipping    %.0f%n", shippingFees);
        System.out.printf("Amount    %.0f%n", totalAmount);
        System.out.printf("Remaining balance: %.0f%n%n", customer.getBalance() - totalAmount);
        System.out.println("END.");
    }

    private void updateInventory(Map<Product, Integer> items) {
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            product.setQuantity(product.getQuantity() - quantity);
        }
    }
}