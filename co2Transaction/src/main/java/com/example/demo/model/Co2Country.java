package com.example.demo.model;

public class Co2Country {
    private Integer id;

    private String countryName;

    private Float countryForestArea;

    private Float countryCredits;

    private Float countryCoints;

    private String countryWalletAddress;

    private String countryNumber;

    private String countryLongitude;

    private String countryLatitude;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName == null ? null : countryName.trim();
    }

    public Float getCountryForestArea() {
        return countryForestArea;
    }

    public void setCountryForestArea(Float countryForestArea) {
        this.countryForestArea = countryForestArea;
    }

    public Float getCountryCredits() {
        return countryCredits;
    }

    public void setCountryCredits(Float countryCredits) {
        this.countryCredits = countryCredits;
    }

    public Float getCountryCoints() {
        return countryCoints;
    }

    public void setCountryCoints(Float countryCoints) {
        this.countryCoints = countryCoints;
    }

    public String getCountryWalletAddress() {
        return countryWalletAddress;
    }

    public void setCountryWalletAddress(String countryWalletAddress) {
        this.countryWalletAddress = countryWalletAddress == null ? null : countryWalletAddress.trim();
    }

    public String getCountryNumber() {
        return countryNumber;
    }

    public void setCountryNumber(String countryNumber) {
        this.countryNumber = countryNumber == null ? null : countryNumber.trim();
    }

    public String getCountryLongitude() {
        return countryLongitude;
    }

    public void setCountryLongitude(String countryLongitude) {
        this.countryLongitude = countryLongitude == null ? null : countryLongitude.trim();
    }

    public String getCountryLatitude() {
        return countryLatitude;
    }

    public void setCountryLatitude(String countryLatitude) {
        this.countryLatitude = countryLatitude == null ? null : countryLatitude.trim();
    }
}