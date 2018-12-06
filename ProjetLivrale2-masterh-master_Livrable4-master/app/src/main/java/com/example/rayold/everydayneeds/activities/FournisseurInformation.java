package com.example.rayold.everydayneeds.activities;

public class FournisseurInformation {
    private String email, companyName, phoneNumber, address, generalDescription, licensed;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGeneralDescription(String generalDescription) {
        this.generalDescription = generalDescription;
    }

    public void setLicensed(String licensed) {
        this.licensed = licensed;
    }

    public String getEmail() {
        return email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getGeneralDescription() {
        return generalDescription;
    }


    public String getLicensed() {
        return licensed;
    }
}
