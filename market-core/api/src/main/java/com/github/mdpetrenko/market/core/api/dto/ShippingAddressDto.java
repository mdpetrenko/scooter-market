package com.github.mdpetrenko.market.core.api.dto;

public class ShippingAddressDto {

    private Long id;

    private String addressLine1;

    private String addressLine2;

    private String adminArea2;

    private String adminArea1;

    private String postalCode;

    private String countryCode;

    public ShippingAddressDto() {
    }

    public ShippingAddressDto(Long id, String addressLine1, String addressLine2, String adminArea1, String adminArea2, String postalCode, String countryCode) {
        this.id = id;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.adminArea1 = adminArea1;
        this.adminArea2 = adminArea2;
        this.postalCode = postalCode;
        this.countryCode = countryCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAdminArea1() {
        return adminArea1;
    }

    public void setAdminArea1(String adminArea1) {
        this.adminArea1 = adminArea1;
    }

    public String getAdminArea2() {
        return adminArea2;
    }

    public void setAdminArea2(String adminArea2) {
        this.adminArea2 = adminArea2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
