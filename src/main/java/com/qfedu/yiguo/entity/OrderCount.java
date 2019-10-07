package com.qfedu.yiguo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("订单结算")
public class OrderCount {
    private Integer id;
    @ApiModelProperty(value = "用户id")
    private Integer uid;
    @ApiModelProperty(value = "合计")
    private Double money;
    @ApiModelProperty(value = "地址id")
    private Integer ressId;
    @ApiModelProperty(value = "商品详情集合")
    private List<OrderCountSpec> ocsList;

    public List<OrderCountSpec> getOcsList() {
        return ocsList;
    }

    public void setOcsList(List<OrderCountSpec> ocsList) {
        this.ocsList = ocsList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getRessId() {
        return ressId;
    }

    public void setRessId(Integer ressId) {
        this.ressId = ressId;
    }
}
