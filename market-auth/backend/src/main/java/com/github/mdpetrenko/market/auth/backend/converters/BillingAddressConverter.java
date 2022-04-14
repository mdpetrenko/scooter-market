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
        billingAddressDto.setCountryCode(billingAddress.getCountryCode());
        billingAddressDto.setDistrict(billingAddress.getDistrict());
        billingAddressDto.setAddressLine1(billingAddress.getStreet());
        billingAddressDto.setAddressLine2(billingAddress.getOfficeNumber());
        billingAddressDto.setZipCode(billingAddress.getPostalCode());
        return billingAddressDto;
    }

    public BillingAddress dtoToEntity(BillingAddressDto billingAddressDto) {
        BillingAddress billingAddress = new BillingAddress();
        billingAddress.setId(billingAddressDto.getId());
        billingAddress.setCity(billingAddressDto.getCity());
        billingAddress.setCountryCode(billingAddressDto.getCountryCode());
        billingAddress.setDistrict(billingAddressDto.getDistrict());
        billingAddress.setStreet(billingAddressDto.getAddressLine1());
        billingAddress.setOfficeNumber(billingAddressDto.getAddressLine2());
        billingAddress.setPostalCode(billingAddressDto.getZipCode());
        return billingAddress;
    }

}
