package com.sundram.woocommercehomepage.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BannerInfo {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("banner_url")
    @Expose
    private String bannerUrl;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("layout_type")
    @Expose
    private String layoutType;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("link_display")
    @Expose
    private String linkDisplay;
    @SerializedName("link_to")
    @Expose
    private String linkTo;
    @SerializedName("link_idd")
    @Expose
    private String linkIdd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(String layoutType) {
        this.layoutType = layoutType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLinkDisplay() {
        return linkDisplay;
    }

    public void setLinkDisplay(String linkDisplay) {
        this.linkDisplay = linkDisplay;
    }

    public String getLinkTo() {
        return linkTo;
    }

    public void setLinkTo(String linkTo) {
        this.linkTo = linkTo;
    }

    public String getLinkIdd() {
        return linkIdd;
    }

    public void setLinkIdd(String linkIdd) {
        this.linkIdd = linkIdd;
    }
}
