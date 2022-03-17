package com.github.mdpetrenko.market.cart.entities;

import com.github.mdpetrenko.market.api.dto.ProductDto;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@Data
@RedisHash("Scooter-Market_cart")
public class Cart implements Serializable {
    private Collection<CartItem> items;
    private int totalPrice;
    private String id;

    public Cart() {
        items = new ArrayList<>();
    }

    public Cart(String id) {
        this();
        this.id = id;
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

    public Cart add(ProductDto product) {
        items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        recalculate();
        return this;
    }

    public Cart decrement(Long productId) {
        Iterator<CartItem> iter = items.iterator();
        while (iter.hasNext()) {
            CartItem item = iter.next();
            if (item.getProductId().equals(productId)) {
                item.changeQuantity(-1);
                if (item.getQuantity() <= 0) {
                    iter.remove();
                }
                recalculate();
                break;
            }
        }
        return this;
    }

    public Cart remove(Long productId) {
        items.removeIf(i -> i.getProductId().equals(productId));
        recalculate();
        return this;
    }

    public Cart clear() {
        items.clear();
        totalPrice = 0;
        return this;
    }

    private void recalculate() {
        totalPrice = 0;
        totalPrice = items.stream().mapToInt(CartItem::getPrice).sum();
    }

    public Cart merge(Cart another) {
        for (CartItem item : another.items) {
            boolean merged = false;
            for (CartItem currentItem : this.items) {
                if (item.getProductId().equals(currentItem.getProductId())) {
                    currentItem.changeQuantity(item.getQuantity());
                    merged = true;
                    break;
                }
            }
            if (!merged) {
                items.add(item);
            }
        }
        another.clear();
        return this;
    }
}
