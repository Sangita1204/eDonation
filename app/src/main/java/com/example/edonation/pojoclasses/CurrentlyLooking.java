package com.example.edonation.pojoclasses;

public class CurrentlyLooking
{
    boolean foods, clothes, stationery, books;

    public CurrentlyLooking(){

    }
    public CurrentlyLooking(boolean foods, boolean clothes, boolean books, boolean stationery) {
        this.foods = foods;
        this.clothes = clothes;
        this.stationery = stationery;
        this.books = books;
    }

    public boolean isFoods() {
        return foods;
    }

    public void setFoods(boolean foods) {
        this.foods = foods;
    }

    public boolean isClothes() {
        return clothes;
    }

    public void setClothes(boolean clothes) {
        this.clothes = clothes;
    }

    public boolean isStationery() {
        return stationery;
    }

    public void setStationery(boolean stationery) {
        this.stationery = stationery;
    }

    public boolean isBooks() {
        return books;
    }

    public void setBooks(boolean books) {
        this.books = books;
    }
}
