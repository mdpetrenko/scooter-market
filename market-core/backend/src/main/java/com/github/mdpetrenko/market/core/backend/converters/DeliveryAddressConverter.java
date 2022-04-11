package com.github.mdpetrenko.market.core.backend.converters;

import com.github.mdpetrenko.market.core.api.dto.DeliveryAddressDto;
import com.github.mdpetrenko.market.core.backend.entities.DeliveryAddress;
import org.springframework.stereotype.Component;

@Component
public class DeliveryAddressConverter {

    public DeliveryAddressDto entityToDto(DeliveryAddress deliveryAddress) {
        return new DeliveryAddressDto(deliveryAddress.getId(),
                deliveryAddress.getStreet(), deliveryAddress.getOfficeNumber(), deliveryAddress.getCity(),
                deliveryAddress.getDistrict(), deliveryAddress.getPostalCode(), deliveryAddress.getCountryCode());
    }

    public DeliveryAddress dtoToEntity(DeliveryAddressDto deliveryAddressDto) {
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setId(deliveryAddress.getId());
        deliveryAddress.setCity(deliveryAddressDto.getCity());
        deliveryAddress.setStreet(deliveryAddressDto.getStreet());
        deliveryAddress.setOfficeNumber(deliveryAddressDto.getOfficeNumber());
        deliveryAddress.setCountryCode(deliveryAddressDto.getCountryCode());
        deliveryAddress.setDistrict(deliveryAddressDto.getDistrict());
        deliveryAddress.setPostalCode(deliveryAddressDto.getPostalCode());
        return deliveryAddress;
    }
}
