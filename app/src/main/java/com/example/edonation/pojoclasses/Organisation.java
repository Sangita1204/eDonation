package com.example.edonation.pojoclasses;

public class Organisation {
    String registerFullName, registerEmail, registerPassword, registerLocation, registerWebsite,registerABN;
    Long registerPhoneNo;

    public Organisation(){

    }
    public Organisation(String registerFullName, String registerEmail, String registerPassword, String registerLocation, String registerWebsite, long registerPhoneNo, String registerABN) {
        this.registerFullName = registerFullName;
        this.registerEmail = registerEmail;
        this.registerPassword = registerPassword;
        this.registerLocation = registerLocation;
        this.registerWebsite = registerWebsite;
        this.registerPhoneNo = registerPhoneNo;
        this.registerABN = registerABN;
    }

    public String getRegisterFullName() {
        return registerFullName;
    }

    public void setRegisterFullName(String registerFullName) {
        this.registerFullName = registerFullName;
    }

    public String getRegisterEmail() {
        return registerEmail;
    }

    public void setRegisterEmail(String registerEmail) {
        this.registerEmail = registerEmail;
    }

    public String getRegisterPassword() {
        return registerPassword;
    }

    public void setRegisterPassword(String registerPassword) {
        this.registerPassword = registerPassword;
    }

    public String getRegisterLocation() {
        return registerLocation;
    }

    public void setRegisterLocation(String registerLocation) {
        this.registerLocation = registerLocation;
    }

    public String getRegisterWebsite() {
        return registerWebsite;
    }

    public void setRegisterWebsite(String registerWebsite) {
        this.registerWebsite = registerWebsite;
    }

    public String getRegisterABN() {
        return registerABN;
    }

    public void setRegisterABN(String registerABN) {
        this.registerABN = registerABN;
    }

    public Long getRegisterPhoneNo() {
        return registerPhoneNo;
    }

    public void setRegisterPhoneNo(Long registerPhoneNo) {
        this.registerPhoneNo = registerPhoneNo;
    }
}
