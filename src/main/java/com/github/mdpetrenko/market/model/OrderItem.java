package com.github.mdpetrenko.market.model;

import com.github.mdpetrenko.market.dtos.CartItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_title")
    private String productTitle;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price_per_item")
    private int pricePerItem;

    @Column(name = "total_price")
    private int totalPrice;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public OrderItem(CartItem cartItem) {
        this.productTitle = cartItem.getProductTitle();
        this.pricePerItem = cartItem.getPricePerItem();
        this.totalPrice = cartItem.getTotalPrice();
        this.quantity = cartItem.getQuantity();
    }
}
