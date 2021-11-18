package com.xzg.androidstudy.pages.recyclerview_example;

public class Item {
    public String title;
    public String imgUrl;
    public float imgWidth;
    public float imgHeight;

    public Item(String title, String imgUrl, float imgWidth, float imgHeight) {
        this.title = title;
        this.imgUrl = imgUrl;
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public float getImgWidth() {
        return imgWidth;
    }

    public void setImgWidth(float imgWidth) {
        this.imgWidth = imgWidth;
    }

    public float getImgHeight() {
        return imgHeight;
    }

    public void setImgHeight(float imgHeight) {
        this.imgHeight = imgHeight;
    }
}
