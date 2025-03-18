package com.jakubolejarczyk.vet_server.domain;

public interface ClinicDomain {
    Long getId();
    void setId(Long id);

    String getName();
    void setName(String name);

    String getStreet();
    void setStreet(String street);

    String getBuildingNumber();
    void setBuildingNumber(String buildingNumber);

    String getApartmentNumber();
    void setApartmentNumber(String apartmentNumber);

    String getPostalCode();
    void setPostalCode(String postalCode);

    String getCity();
    void setCity(String city);

    String getProvince();
    void setProvince(String province);

    String getCountry();
    void setCountry(String country);

    String getEmail();
    void setEmail(String email);

    String getPhoneNumber();
    void setPhoneNumber(String phoneNumber);

    Long getOpeningHoursId();
    void setOpeningHoursId(Long openingHoursId);
}
