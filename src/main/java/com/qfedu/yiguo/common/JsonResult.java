package com.qfedu.yiguo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * controller层方法返回值统一json类型
 */
@ApiModel("json类型")
public class JsonResult<T> {

    @ApiModelProperty("返回数据状态码，0 成功 1 失败")
    private Integer code;
    @ApiModelProperty("返回具体的对象，也可以是null")
    private T info;

    public JsonResult() {
    }

    public JsonResult(Integer code, T info) {
        this.code = code;
        this.info = info;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }
}
