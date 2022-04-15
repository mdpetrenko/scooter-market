package com.github.mdpetrenko.market.auth.backend.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "billing_addresses")
@Data
public class BillingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "address_line1")
    private String addressLine1;

    @Column(name = "address_line2")
    private String addressLine2;

    @Column(name = "admin_area1")
    private String adminArea1;

    @Column(name = "admin_area2")
    private String adminArea2;


    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "country_code")
    private String countryCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
