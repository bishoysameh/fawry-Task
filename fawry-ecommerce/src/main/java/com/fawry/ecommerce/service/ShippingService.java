package com.fawry.ecommerce.service;

import com.fawry.ecommerce.model.Shippable;
import java.util.List;

public class ShippingService {
    public void shipItems(List<Shippable> items) {
        System.out.println("** Shipment Notice **");
        double totalWeight = 0;
        for (Shippable item : items) {
            System.out.printf("%s (Weight: %.1fkg)%n", item.getName(), item.getWeight());
            totalWeight += item.getWeight();
        }
        System.out.printf("Total Weight: %.1fkg%n", totalWeight);
    }
}