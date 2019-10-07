package com.qfedu.yiguo.controller;

import com.qfedu.yiguo.common.JsonResult;
import com.qfedu.yiguo.entity.OrderCount;
import com.qfedu.yiguo.service.OrderCountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(description ="订单结算")
@Controller
@ResponseBody
public class OrderCountController {

    @Autowired
    private OrderCountService orderCountService;

    @PostMapping("/addOrderCount")
    @ApiOperation(value="去结算按钮生成结算订单", notes="需要传来 token , 商品id数组 gids 和 money合计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gids", value = "商品id数组"),
            @ApiImplicitParam(name = "money", value = "合计")
    })
    public JsonResult addOrderCount(String token,Integer[] gids,Double money){
        orderCountService.addOrder(token,gids,money);
        return new JsonResult(0,null);
    }

    @PostMapping("/findOrderCount")
    @ApiOperation(value="订单结算展示", notes="需要传来 token")
    @ApiImplicitParam(name = "gids", value = "商品id数组")
    public JsonResult<OrderCount> findOrderCount(String token){
        OrderCount orderCount = orderCountService.findAll(token);
        return new JsonResult(0, orderCount);
    }
}
