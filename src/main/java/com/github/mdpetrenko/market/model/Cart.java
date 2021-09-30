package com.github.mdpetrenko.market.model;

import com.github.mdpetrenko.market.dtos.OrderItemDto;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@Data
@RedisHash("ScooterMarket_cart")
public class Cart implements Serializable {
    private Collection<OrderItemDto> items;
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
        for (OrderItemDto item : items) {
            if (item.getProductId().equals(productId)) {
                item.changeQuantity(1);
                recalculate();
                return true;
            }
        }
        return false;
    }

    public Cart add(Product product) {
        items.add(new OrderItemDto(product));
        recalculate();
        return this;
    }

    public Cart decrement(Long productId) {
        Iterator<OrderItemDto> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItemDto item = iter.next();
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
        totalPrice = items.stream().mapToInt(OrderItemDto::getPrice).sum();
    }

    public Cart merge(Cart another) {
        for (OrderItemDto item : another.items) {
            boolean merged = false;
            for (OrderItemDto currentItem : this.items) {
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
