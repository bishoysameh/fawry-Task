package com.fawry.ecommerce;

import java.time.LocalDate;

import com.fawry.ecommerce.model.Customer;
import com.fawry.ecommerce.model.ExpirableProduct;
import com.fawry.ecommerce.model.NonExpirableProduct;
import com.fawry.ecommerce.model.Product;
import com.fawry.ecommerce.model.ShippableProduct;
import com.fawry.ecommerce.service.Cart;
import com.fawry.ecommerce.service.ECommerceService;

public class FawryECommerceSystemApplication {
    public static void main(String[] args) {
        Product cheese = new ExpirableProduct("Cheese", 100, 10, 
                                           true, 0.4, LocalDate.now().plusDays(7));
        
        Product tv = new ShippableProduct("TV", 1000, 5, true, 5.0) {
            @Override public boolean isExpired() { return false; }
        };
        
        Product scratchCard = new NonExpirableProduct("Scratch Card", 50, 100, false);
        
        Customer customer = new Customer("John", 1500);
        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(tv, 1);
        cart.add(scratchCard, 3);
        
        new ECommerceService().checkout(customer, cart);
    }
}