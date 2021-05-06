package com.example.edonation.pojoclasses;

public class Organisation {
    String fullName, email, password, location, website, ABN, description;
    Long phoneNo;
    String Clothes, Books, Food, Stationery;

    public Organisation(){

    }
    public Organisation(String fullName, String email, String password, String location, String website, String ABN, Long phoneNo, String description) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.location = location;
        this.website = website;
        this.ABN = ABN;
        this.phoneNo = phoneNo;
        this.description=description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClothes() {
        return Clothes;
    }

    public void setClothes(String clothes) {
        Clothes = clothes;
    }

    public String getBooks() {
        return Books;
    }

    public void setBooks(String books) {
        Books = books;
    }

    public String getFood() {
        return Food;
    }

    public void setFood(String food) {
        Food = food;
    }

    public String getStationery() {
        return Stationery;
    }

    public void setStationery(String stationery) {
        Stationery = stationery;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getABN() {
        return ABN;
    }

    public void setABN(String ABN) {
        this.ABN = ABN;
    }

    public Long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }
}
