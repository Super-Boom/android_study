package com.xzg.androidstudy.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class Products {

    @JSONField(name = "product_id")
    private Integer productId;
    @JSONField(name = "product_code")
    private String productCode;
    @JSONField(name = "product_title")
    private String productTitle;
    @JSONField(name = "sku_sn")
    private String skuSn;
    @JSONField(name = "description")
    private String description;
    @JSONField(name = "stock")
    private Integer stock;
    @JSONField(name = "preset_stock")
    private Integer presetStock;
    @JSONField(name = "shelf_status")
    private String shelfStatus;
    @JSONField(name = "actual_price")
    private Integer actualPrice;
    @JSONField(name = "cross_line_price")
    private Integer crossLinePrice;
    @JSONField(name = "rebate")
    private Integer rebate;
    @JSONField(name = "sort")
    private Integer sort;
    @JSONField(name = "route_id")
    private String routeId;
    @JSONField(name = "product_tag")
    private String productTag;
    @JSONField(name = "tag")
    private String tag;
    @JSONField(name = "product_rectangle_img")
    private String productRectangleImg;
    @JSONField(name = "product_carousel_img")
    private String productCarouselImg;
    @JSONField(name = "product_detail_img")
    private String productDetailImg;
    @JSONField(name = "type")
    private String type;
    @JSONField(name = "release_type")
    private String releaseType;
    @JSONField(name = "top")
    private Integer top;
    @JSONField(name = "point")
    private Integer point;
    @JSONField(name = "item_id")
    private String itemId;
    @JSONField(name = "item_type")
    private String itemType;
    @JSONField(name = "child_item_id")
    private String childItemId;
    @JSONField(name = "buy_limit")
    private Integer buyLimit;
    @JSONField(name = "freeze_buy_interval")
    private Integer freezeBuyInterval;
    @JSONField(name = "longitude")
    private String longitude;
    @JSONField(name = "latitude")
    private String latitude;
    @JSONField(name = "distance")
    private Integer distance;
    @JSONField(name = "product_status")
    private Integer productStatus;
    @JSONField(name = "wechat_pub_page_url")
    private String wechatPubPageUrl;
    @JSONField(name = "wechat_enterprise_qrcode")
    private String wechatEnterpriseQrcode;
    @JSONField(name = "h5_page_url")
    private String h5PageUrl;
    @JSONField(name = "create_time")
    private String createTime;
    @JSONField(name = "update_time")
    private String updateTime;
    @JSONField(name = "process")
    private String process;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getSkuSn() {
        return skuSn;
    }

    public void setSkuSn(String skuSn) {
        this.skuSn = skuSn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getPresetStock() {
        return presetStock;
    }

    public void setPresetStock(Integer presetStock) {
        this.presetStock = presetStock;
    }

    public String getShelfStatus() {
        return shelfStatus;
    }

    public void setShelfStatus(String shelfStatus) {
        this.shelfStatus = shelfStatus;
    }

    public Integer getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Integer actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Integer getCrossLinePrice() {
        return crossLinePrice;
    }

    public void setCrossLinePrice(Integer crossLinePrice) {
        this.crossLinePrice = crossLinePrice;
    }

    public Integer getRebate() {
        return rebate;
    }

    public void setRebate(Integer rebate) {
        this.rebate = rebate;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getProductTag() {
        return productTag;
    }

    public void setProductTag(String productTag) {
        this.productTag = productTag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getProductRectangleImg() {
        return productRectangleImg;
    }

    public void setProductRectangleImg(String productRectangleImg) {
        this.productRectangleImg = productRectangleImg;
    }

    public String getProductCarouselImg() {
        return productCarouselImg;
    }

    public void setProductCarouselImg(String productCarouselImg) {
        this.productCarouselImg = productCarouselImg;
    }

    public String getProductDetailImg() {
        return productDetailImg;
    }

    public void setProductDetailImg(String productDetailImg) {
        this.productDetailImg = productDetailImg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReleaseType() {
        return releaseType;
    }

    public void setReleaseType(String releaseType) {
        this.releaseType = releaseType;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getChildItemId() {
        return childItemId;
    }

    public void setChildItemId(String childItemId) {
        this.childItemId = childItemId;
    }

    public Integer getBuyLimit() {
        return buyLimit;
    }

    public void setBuyLimit(Integer buyLimit) {
        this.buyLimit = buyLimit;
    }

    public Integer getFreezeBuyInterval() {
        return freezeBuyInterval;
    }

    public void setFreezeBuyInterval(Integer freezeBuyInterval) {
        this.freezeBuyInterval = freezeBuyInterval;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public String getWechatPubPageUrl() {
        return wechatPubPageUrl;
    }

    public void setWechatPubPageUrl(String wechatPubPageUrl) {
        this.wechatPubPageUrl = wechatPubPageUrl;
    }

    public String getWechatEnterpriseQrcode() {
        return wechatEnterpriseQrcode;
    }

    public void setWechatEnterpriseQrcode(String wechatEnterpriseQrcode) {
        this.wechatEnterpriseQrcode = wechatEnterpriseQrcode;
    }

    public String getH5PageUrl() {
        return h5PageUrl;
    }

    public void setH5PageUrl(String h5PageUrl) {
        this.h5PageUrl = h5PageUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }
}
