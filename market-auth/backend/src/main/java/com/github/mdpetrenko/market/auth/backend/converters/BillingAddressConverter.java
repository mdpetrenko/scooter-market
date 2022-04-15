package com.github.mdpetrenko.market.auth.backend.converters;

import com.github.mdpetrenko.market.auth.api.dto.BillingAddressDto;
import com.github.mdpetrenko.market.auth.backend.entities.BillingAddress;
import org.springframework.stereotype.Component;

@Component
public class BillingAddressConverter {

    public BillingAddressDto entityToDto(BillingAddress billingAddress) {
        BillingAddressDto billingAddressDto = new BillingAddressDto();
        billingAddressDto.setId(billingAddress.getId());
        billingAddressDto.setAdminArea2(billingAddress.getAdminArea2());
        billingAddressDto.setCountryCode(billingAddress.getCountryCode());
        billingAddressDto.setAdminArea1(billingAddress.getAdminArea1());
        billingAddressDto.setAddressLine1(billingAddress.getAddressLine1());
        billingAddressDto.setAddressLine2(billingAddress.getAddressLine2());
        billingAddressDto.setPostalCode(billingAddress.getPostalCode());
        return billingAddressDto;
    }

    public BillingAddress dtoToEntity(BillingAddressDto billingAddressDto) {
        BillingAddress billingAddress = new BillingAddress();
        billingAddress.setId(billingAddressDto.getId());
        billingAddress.setAdminArea2(billingAddressDto.getAdminArea2());
        billingAddress.setCountryCode(billingAddressDto.getCountryCode());
        billingAddress.setAdminArea1(billingAddressDto.getAdminArea1());
        billingAddress.setAddressLine1(billingAddressDto.getAddressLine1());
        billingAddress.setAddressLine2(billingAddressDto.getAddressLine2());
        billingAddress.setPostalCode(billingAddressDto.getPostalCode());
        return billingAddress;
    }

}
