package com.github.mdpetrenko.market.auth.backend.converters;

import com.github.mdpetrenko.market.auth.api.dto.BillingAddressDto;
import com.github.mdpetrenko.market.auth.backend.entities.BillingAddress;
import org.springframework.stereotype.Component;

@Component
public class BillingAddressConverter {

    public BillingAddressDto entityToDto(BillingAddress billingAddress) {
        BillingAddressDto billingAddressDto = new BillingAddressDto();
        billingAddressDto.setId(billingAddress.getId());
        billingAddressDto.setCity(billingAddress.getCity());
        billingAddressDto.setCountryCode(billingAddress.getCountryCode().name());
        billingAddressDto.setDistrict(billingAddress.getDistrict());
        billingAddressDto.setStreet(billingAddress.getStreet());
        billingAddressDto.setOfficeNumber(billingAddress.getOfficeNumber());
        billingAddressDto.setPostalCode(billingAddress.getPostalCode());
        billingAddressDto.setOwnerName(billingAddress.getUser().getName());
        return billingAddressDto;
    }

}
