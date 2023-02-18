package com.interview.shoppingbasket;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class BasketTest {
    @Test
    void emptyBasket() {
        Basket basket = new Basket();
        List<BasketItem> basketSize = basket.getItems();

        assertEquals(basketSize.size(), 0);
    }

    @Test
    void createBasketFullConstructor() {
        Basket basket = new Basket();
        basket.add("productCode", "myProduct", 10);
        List<BasketItem> basketSize = basket.getItems();

        assertEquals(basketSize.size(), 1);
        assertEquals(basketSize.get(0).getProductCode(), "productCode");
        assertEquals(basketSize.get(0).getProductName(), "myProduct");
        assertEquals(basketSize.get(0).getQuantity(), 10);
    }

    @Test
    void createBasketWithMultipleProducts() {
        Basket basket = new Basket();
        basket.add("productCode", "myProduct", 10);
        basket.add("productCode2", "myProduct2", 10);
        basket.add("productCode3", "myProduct3", 10);

        List<BasketItem> basketSize = basket.getItems();

        assertEquals(basketSize.size(),3);
        assertEquals(basketSize.get(0).getProductCode(), "productCode");
        assertEquals(basketSize.get(0).getProductName(), "myProduct");
        assertEquals(basketSize.get(0).getQuantity(), 10);
        assertEquals(basketSize.get(1).getProductCode(), "productCode2");
        assertEquals(basketSize.get(1).getProductName(), "myProduct2");
        assertEquals(basketSize.get(1).getQuantity(), 10);
        assertEquals(basketSize.get(2).getProductCode(), "productCode3");
        assertEquals(basketSize.get(2).getProductName(), "myProduct3");
        assertEquals(basketSize.get(2).getQuantity(), 10);
    }

    @Test
    void consolidateBasketTest() {
        // Exercise - implement the unit test for consolidate items
        Basket basket = new Basket();
        basket.add("productCode", "1", 10);
        basket.add("productCode", "2", 5);
        basket.add("productCode2", "myProduct2", 10);
        basket.add("productCode2", "myProduct2", 10);
        basket.add("productCode2", "myProduct2", 5);
        basket.add("productCode3", "myProduct3", 10);

        basket.consolidateItems();

        // Verify if product codes are being merged
        assertEquals("productCode", basket.getItems().get(0).getProductCode());
        assertEquals("productCode2", basket.getItems().get(1).getProductCode());
        assertEquals("productCode3", basket.getItems().get(2).getProductCode());
        assertEquals(3, basket.getItems().size());

        // Verify if quantities are being added
        assertEquals(15, basket.getItems().get(0).getQuantity());
        assertEquals(25, basket.getItems().get(1).getQuantity());
        assertEquals(10, basket.getItems().get(2).getQuantity());
    }
}
