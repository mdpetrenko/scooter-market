package com.github.mdpetrenko.market.core.backend.repositories;

import com.github.mdpetrenko.market.core.backend.entities.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {
}
