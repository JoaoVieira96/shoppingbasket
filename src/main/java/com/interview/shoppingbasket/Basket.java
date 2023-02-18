package com.interview.shoppingbasket;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Basket {
    private List<BasketItem> items = new ArrayList<>();

    public void add(String productCode, String productName, int quantity) {
        BasketItem basketItem = new BasketItem();
        basketItem.setProductCode(productCode);
        basketItem.setProductName(productName);
        basketItem.setQuantity(quantity);

        items.add(basketItem);
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public void consolidateItems() {
        // Exercise - implement this function
        List<BasketItem> finalItems = new ArrayList<>();

        items.stream().forEach( item -> {
            List<BasketItem> filteredItems = items.stream().filter( itemFilter -> itemFilter.getProductCode().equals(item.getProductCode()) &&
                    !finalItems.stream().anyMatch( match -> match.getProductCode().equals(itemFilter.getProductCode()))).collect(Collectors.toList());
            filteredItems.stream().forEach( filteredItem -> {
                if (!finalItems.stream().anyMatch( match -> match.getProductCode().equals(filteredItem.getProductCode()))) {
                    finalItems.add(filteredItem);
                } else {
                    Optional<BasketItem> basketItemOptional = finalItems.stream().filter(filter -> filter.getProductCode().equals(filteredItem.getProductCode())).findFirst();
                    if (basketItemOptional.isPresent()) {
                        BasketItem basketItem = basketItemOptional.get();
                        basketItem.setQuantity(basketItem.getQuantity() + filteredItem.getQuantity());
                    }
                }
            });
        });

        items = finalItems;
    }
}
