package com.example.edonation.pojoclasses;

public class Donor {
    String fullNameDonor, emailDonor, locationDonor, organisationName, donorId;
    Long phoneNoDonor;
    public  CurrentlyLooking currentlyLooking;
    String descriptionDonor;

    public Donor(String donorId, String organisationName, String fullNameDonor, String emailDonor, String locationDonor, Long phoneNoDonor, CurrentlyLooking currentlyLooking, String descriptionDonor) {
      this.donorId=donorId;
       this.organisationName=organisationName;
        this.fullNameDonor = fullNameDonor;
        this.emailDonor = emailDonor;
        this.locationDonor = locationDonor;
        this.phoneNoDonor = phoneNoDonor;
        this.currentlyLooking = currentlyLooking;
        this.descriptionDonor = descriptionDonor;
    }
    public Donor(){

    }

    public String getDonorId() {
        return donorId;
    }

    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public String getFullNameDonor() {
        return fullNameDonor;
    }

    public void setFullNameDonor(String fullNameDonor) {
        this.fullNameDonor = fullNameDonor;
    }

    public String getEmailDonor() {
        return emailDonor;
    }

    public void setEmailDonor(String emailDonor) {
        this.emailDonor = emailDonor;
    }

    public String getLocationDonor() {
        return locationDonor;
    }

    public void setLocationDonor(String locationDonor) {
        this.locationDonor = locationDonor;
    }

    public Long getPhoneNoDonor() {
        return phoneNoDonor;
    }

    public void setPhoneNoDonor(Long phoneNoDonor) {
        this.phoneNoDonor = phoneNoDonor;
    }

    public CurrentlyLooking getCurrentlyLooking() {
        return currentlyLooking;
    }

    public void setCurrentlyLooking(CurrentlyLooking currentlyLooking) {
        this.currentlyLooking = currentlyLooking;
    }

    public String getDescriptionDonor() {
        return descriptionDonor;
    }

    public void setDescriptionDonor(String descriptionDonor) {
        this.descriptionDonor = descriptionDonor;
    }
}
