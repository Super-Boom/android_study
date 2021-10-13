package com.xzg.androidstudy.data;

import com.alibaba.fastjson.annotation.JSONField;

public class TrailProductDetail {

    @JSONField(name = "code")
    private Integer code;
    @JSONField(name = "data")
    private Data data;
    @JSONField(name = "msg")
    private String msg;
    @JSONField(name = "span_id")
    private String spanId;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSpanId() {
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

    public static class Data {
        @JSONField(name = "trial_product_id")
        private Integer trialProductId;
        @JSONField(name = "trial_product_code")
        private String trialProductCode;
        @JSONField(name = "product_name")
        private String productName;
        @JSONField(name = "shipping_fee")
        private String shippingFee;
        @JSONField(name = "stock")
        private Integer stock;
        @JSONField(name = "image")
        private String image;
        @JSONField(name = "detail_images")
        private String detailImages;
        @JSONField(name = "cross_line_price")
        private String crossLinePrice;
        @JSONField(name = "trial_price")
        private String trialPrice;
        @JSONField(name = "status")
        private Integer status;
        @JSONField(name = "trial_people")
        private String trialPeople;
        @JSONField(name = "trial_amount")
        private Integer trialAmount;
        @JSONField(name = "rectangle_image")
        private String rectangleImage;
        @JSONField(name = "description")
        private String description;
        @JSONField(name = "sort")
        private Integer sort;
        @JSONField(name = "point")
        private Integer point;
        @JSONField(name = "service")
        private String service;
        @JSONField(name = "create_time")
        private String createTime;
        @JSONField(name = "update_time")
        private String updateTime;

        public Integer getTrialProductId() {
            return trialProductId;
        }

        public void setTrialProductId(Integer trialProductId) {
            this.trialProductId = trialProductId;
        }

        public String getTrialProductCode() {
            return trialProductCode;
        }

        public void setTrialProductCode(String trialProductCode) {
            this.trialProductCode = trialProductCode;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getShippingFee() {
            return shippingFee;
        }

        public void setShippingFee(String shippingFee) {
            this.shippingFee = shippingFee;
        }

        public Integer getStock() {
            return stock;
        }

        public void setStock(Integer stock) {
            this.stock = stock;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDetailImages() {
            return detailImages;
        }

        public void setDetailImages(String detailImages) {
            this.detailImages = detailImages;
        }

        public String getCrossLinePrice() {
            return crossLinePrice;
        }

        public void setCrossLinePrice(String crossLinePrice) {
            this.crossLinePrice = crossLinePrice;
        }

        public String getTrialPrice() {
            return trialPrice;
        }

        public void setTrialPrice(String trialPrice) {
            this.trialPrice = trialPrice;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getTrialPeople() {
            return trialPeople;
        }

        public void setTrialPeople(String trialPeople) {
            this.trialPeople = trialPeople;
        }

        public Integer getTrialAmount() {
            return trialAmount;
        }

        public void setTrialAmount(Integer trialAmount) {
            this.trialAmount = trialAmount;
        }

        public String getRectangleImage() {
            return rectangleImage;
        }

        public void setRectangleImage(String rectangleImage) {
            this.rectangleImage = rectangleImage;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getSort() {
            return sort;
        }

        public void setSort(Integer sort) {
            this.sort = sort;
        }

        public Integer getPoint() {
            return point;
        }

        public void setPoint(Integer point) {
            this.point = point;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
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
    }
}

