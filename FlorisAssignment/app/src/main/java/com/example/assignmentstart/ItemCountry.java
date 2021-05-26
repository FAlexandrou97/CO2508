package com.example.assignmentstart;


public class ItemCountry {

    private int heart;
    private boolean favorite;
    String countryName;

    public ItemCountry(int heart, String countryName, boolean favorite) {
        this.heart = heart;
        this.countryName = countryName;
        this.favorite = favorite;
    }

    public int getHeart() {
        return heart;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public boolean isFavorite() {
        return favorite;
    }

}
