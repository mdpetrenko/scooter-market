package com.github.mdpetrenko.market.core.backend.entities;

import com.github.mdpetrenko.market.core.api.dto.OrderDetailsDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
@NamedEntityGraph(
        name = "orders.front",
        attributeNodes = {
                @NamedAttributeNode(value = "items", subgraph = "items-products")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "items-products",
                        attributeNodes = {
                                @NamedAttributeNode("product")
                        }
                )
        }
)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "owner_phone")
    private String ownerPhone;

    @Column(name = "owner_email")
    private String ownerEmail;

    @Column(name = "price")
    private BigDecimal price;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Collection<OrderItem> items;

    @Column(name = "username")
    private String username;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    public Order(OrderDetailsDto orderDetailsDto) {
        this.ownerName = orderDetailsDto.getOwnerName();
        this.deliveryAddress = orderDetailsDto.getDeliveryAddress();
        this.ownerPhone = orderDetailsDto.getOwnerPhone();
        this.ownerEmail = orderDetailsDto.getOwnerEmail();
    }

}
