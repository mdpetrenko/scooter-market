package com.github.mdpetrenko.market.core.api.dto;

public class DeliveryAddressDto {

    private Long id;

    private String street;

    private String officeNumber;

    private String city;

    private String district;

    private String postalCode;

    private String countryCode;

    public DeliveryAddressDto() {
    }

    public DeliveryAddressDto(Long id, String street, String officeNumber, String city, String district, String postalCode, String countryCode) {
        this.id = id;
        this.street = street;
        this.officeNumber = officeNumber;
        this.city = city;
        this.district = district;
        this.postalCode = postalCode;
        this.countryCode = countryCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
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
