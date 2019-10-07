package com.qfedu.yiguo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("商品")
public class Goods {
    private Integer id;
    @ApiModelProperty(value = "商品名字")
    private String goodsName;
    @ApiModelProperty(value = "商品图片url")
    private String imgurl;
    @ApiModelProperty(value = "商品类型id")
    private Integer categoryId;
    @ApiModelProperty(value = "价格")
    private Double price;
    @ApiModelProperty(value = "说明")
    private String explain;
    @ApiModelProperty(value = "产地")
    private String field;
    @ApiModelProperty(value = "赞美")
    private String praise;
    @ApiModelProperty(value = "规格")
    private String spec;

    private Integer code;

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getPraise() {
        return praise;
    }

    public void setPraise(String praise) {
        this.praise = praise;
    }
}
