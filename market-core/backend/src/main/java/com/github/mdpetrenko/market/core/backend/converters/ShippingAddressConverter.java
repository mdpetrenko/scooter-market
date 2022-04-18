package com.github.mdpetrenko.market.core.backend.converters;

import com.github.mdpetrenko.market.core.api.dto.ShippingAddressDto;
import com.github.mdpetrenko.market.core.backend.entities.ShippingAddress;
import org.springframework.stereotype.Component;

@Component
public class ShippingAddressConverter {

    public ShippingAddressDto entityToDto(ShippingAddress shippingAddress) {
        return new ShippingAddressDto(shippingAddress.getId(),
                shippingAddress.getAddressLine1(), shippingAddress.getAddressLine2(), shippingAddress.getAdminArea2(),
                shippingAddress.getAdminArea1(), shippingAddress.getPostalCode(), shippingAddress.getCountryCode());
    }

    public ShippingAddress dtoToEntity(ShippingAddressDto shippingAddressDto) {
        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setId(shippingAddress.getId());
        shippingAddress.setAdminArea2(shippingAddressDto.getAdminArea2());
        shippingAddress.setAddressLine1(shippingAddressDto.getAddressLine1());
        shippingAddress.setAddressLine2(shippingAddressDto.getAddressLine2());
        shippingAddress.setCountryCode(shippingAddressDto.getCountryCode());
        shippingAddress.setAdminArea1(shippingAddressDto.getAdminArea1());
        shippingAddress.setPostalCode(shippingAddressDto.getPostalCode());
        return shippingAddress;
    }
}
