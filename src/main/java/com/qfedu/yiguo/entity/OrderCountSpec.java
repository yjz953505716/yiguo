package com.qfedu.yiguo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("订单结算详情类")
public class OrderCountSpec {

    private Integer id;
    @ApiModelProperty(value = "订单id")
    private Integer oid;
    @ApiModelProperty(value = "商品")
    private Integer gid;
    @ApiModelProperty(value = "商品数量")
    private Integer gNum;
    @ApiModelProperty(value = "商品集合")
    private List<Goods> goodsList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getgNum() {
        return gNum;
    }

    public void setgNum(Integer gNum) {
        this.gNum = gNum;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
}
