package com.github.mdpetrenko.market.core.backend.repositories;

import com.github.mdpetrenko.market.core.backend.entities.DeliveryAddress;
import com.github.mdpetrenko.market.core.backend.entities.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {
}
