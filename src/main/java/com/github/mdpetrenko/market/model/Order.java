package com.github.mdpetrenko.market.model;

import com.github.mdpetrenko.market.dtos.OrderDetailsDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "owner_phone")
    private String ownerPhone;

    @Column(name = "owner_email")
    private String ownerEmail;

    @OneToMany(mappedBy = "order")
    private Collection<OrderItem> orderItems;

    public Order(OrderDetailsDto orderDetailsDto) {
        this.ownerName = orderDetailsDto.getOwnerName();
        this.deliveryAddress = orderDetailsDto.getDeliveryAddress();
        this.ownerPhone = orderDetailsDto.getOwnerPhone();
        this.ownerEmail = orderDetailsDto.getOwnerEmail();
        this.orderItems = orderDetailsDto.getCartItems().stream().map(OrderItem::new).collect(Collectors.toList());
        this.orderItems.forEach(i -> i.setOrder(this));
    }

}
