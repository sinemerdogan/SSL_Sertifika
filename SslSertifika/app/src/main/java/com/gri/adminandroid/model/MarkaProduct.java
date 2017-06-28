package com.gri.adminandroid.model;

/**
 * Created by mustafa on 15.12.2016.
 */

public class MarkaProduct {
    private String markaSslName;
    private String price;
    private int starCount;
    private String buyUrl;

    public String getMarkaSslName() {
        return markaSslName;
    }

    public void setMarkaSslName(String markaSslName) {
        this.markaSslName = markaSslName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    public String getBuyUrl() {
        return buyUrl;
    }

    public void setBuyUrl(String buyUrl) {
        this.buyUrl = buyUrl;
    }
}
