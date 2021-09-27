package com.sundram.woocommercehomepage.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_image")
    @Expose
    private String productImage;
    @SerializedName("currency_symbol")
    @Expose
    private String currencySymbol;
    @SerializedName("is_in_stock")
    @Expose
    private Boolean isInStock;
    @SerializedName("product_type")
    @Expose
    private String productType;
    @SerializedName("sale_price")
    @Expose
    private String salePrice;
    @SerializedName("regular_price")
    @Expose
    private String regularPrice;
    @SerializedName("product_price")
    @Expose
    private String productPrice;
    @SerializedName("average_rating")
    @Expose
    private String averageRating;
    @SerializedName("wishlist")
    @Expose
    private String wishlist;
    @SerializedName("product_price_min")
    @Expose
    private String productPriceMin;
    @SerializedName("product_price_max")
    @Expose
    private String productPriceMax;
    @SerializedName("product_price_min_reg")
    @Expose
    private String productPriceMinReg;
    @SerializedName("product_price_max_reg")
    @Expose
    private String productPriceMaxReg;
    @SerializedName("sale")
    @Expose
    private Boolean sale;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public Boolean getIsInStock() {
        return isInStock;
    }

    public void setIsInStock(Boolean isInStock) {
        this.isInStock = isInStock;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public String getWishlist() {
        return wishlist;
    }

    public void setWishlist(String wishlist) {
        this.wishlist = wishlist;
    }

    public String getProductPriceMin() {
        return productPriceMin;
    }

    public void setProductPriceMin(String productPriceMin) {
        this.productPriceMin = productPriceMin;
    }

    public String getProductPriceMax() {
        return productPriceMax;
    }

    public void setProductPriceMax(String productPriceMax) {
        this.productPriceMax = productPriceMax;
    }

    public String getProductPriceMinReg() {
        return productPriceMinReg;
    }

    public void setProductPriceMinReg(String productPriceMinReg) {
        this.productPriceMinReg = productPriceMinReg;
    }

    public String getProductPriceMaxReg() {
        return productPriceMaxReg;
    }

    public void setProductPriceMaxReg(String productPriceMaxReg) {
        this.productPriceMaxReg = productPriceMaxReg;
    }

    public Boolean getSale() {
        return sale;
    }

    public void setSale(Boolean sale) {
        this.sale = sale;
    }
}
