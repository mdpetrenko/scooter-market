package com.github.mdpetrenko.market.auth.api.dto;

import java.util.List;

public class UserDto {
    private String name;
    private String phone;
    private String email;
    private List<BillingAddressDto> addresses;

    public UserDto() {
    }

    public UserDto(String name, String phone, String email, List<BillingAddressDto> billingAddresses) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.addresses = billingAddresses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<BillingAddressDto> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<BillingAddressDto> addresses) {
        this.addresses = addresses;
    }
}
