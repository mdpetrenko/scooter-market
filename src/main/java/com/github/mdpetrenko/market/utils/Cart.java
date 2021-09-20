package com.github.mdpetrenko.market.utils;

import com.github.mdpetrenko.market.dtos.CartItem;
import com.github.mdpetrenko.market.model.Product;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@Getter
public class Cart {
    private final Collection<CartItem> items;
    private int totalPrice;

    public Cart() {
        items = new ArrayList<>();
    }

    public boolean add(Long productId) {
        for (CartItem item : items) {
            if (item.getProductId().equals(productId)) {
                item.changeQuantity(1);
                recalculate();
                return true;
            }
        }
        return false;
    }

    public void add(Product product) {
        items.add(new CartItem(product));
        recalculate();
    }

    public void decrement(Long productId) {
        Iterator<CartItem> iter = items.iterator();
        while (iter.hasNext()) {
            CartItem item = iter.next();
            if (item.getProductId().equals(productId)) {
                item.changeQuantity(-1);
                if (item.getQuantity() <= 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public void remove(Long productId) {
        items.removeIf(i -> i.getProductId().equals(productId));
        recalculate();
    }

    public void clear() {
        items.clear();
        totalPrice = 0;
    }

    private void recalculate() {
        totalPrice = 0;
        totalPrice = items.stream().mapToInt(CartItem::getTotalPrice).sum();
    }

}
