package com.sundram.woocommercehomepage.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("category_data")
    @Expose
    private List<Category> categoryData = null;
    @SerializedName("banner_info")
    @Expose
    private List<BannerInfo> bannerInfo = null;
    @SerializedName("category_text_color")
    @Expose
    private String categoryTextColor;
    @SerializedName("category_bg_color")
    @Expose
    private String categoryBgColor;

    public List<Category> getCategoryData() {
        return categoryData;
    }

    public void setCategoryData(List<Category> categoryData) {
        this.categoryData = categoryData;
    }

    public List<BannerInfo> getBannerInfo() {
        return bannerInfo;
    }

    public void setBannerInfo(List<BannerInfo> bannerInfo) {
        this.bannerInfo = bannerInfo;
    }

    public String getCategoryTextColor() {
        return categoryTextColor;
    }

    public void setCategoryTextColor(String categoryTextColor) {
        this.categoryTextColor = categoryTextColor;
    }

    public String getCategoryBgColor() {
        return categoryBgColor;
    }

    public void setCategoryBgColor(String categoryBgColor) {
        this.categoryBgColor = categoryBgColor;
    }

}
