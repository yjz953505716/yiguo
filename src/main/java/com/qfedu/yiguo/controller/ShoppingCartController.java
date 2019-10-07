package com.qfedu.yiguo.controller;

import com.qfedu.yiguo.common.JsonResult;
import com.qfedu.yiguo.entity.ShoppingCart;
import com.qfedu.yiguo.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(description ="购物车")
@Controller
@ResponseBody
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/SClist")
    @ApiOperation(value="购物车展示(传入前台是购物车集合cartList里面包含商品集合goodsList)", notes="需要传来 token")
    public JsonResult<List<ShoppingCart>> SClist(String token){
        List<ShoppingCart> cartList = shoppingCartService.findAll(token);
        return new JsonResult(0,cartList);
    }

    @PostMapping("/addSC")
    @ApiOperation(value="加入购物车", notes="需要传来 token 和 商品id  gid")
    @ApiImplicitParam(name = "gid", value = "商品id")
    public JsonResult addSC(String token,Integer gid){
        shoppingCartService.addSC(token,gid);
        return new JsonResult(0,null);
    }

    @GetMapping("/deleteSC")
    @ApiOperation(value="批量删除", notes="需要传来 所选中的商品id数组 gids")
    @ApiImplicitParam(name = "gids", value = "商品id数组")
    public JsonResult deleteSC(Integer[] gids){
        shoppingCartService.deleteSC(gids);
        return new JsonResult(0,null);
    }

    @GetMapping("/addSCnum")
    @ApiOperation(value="购物车里加商品数量，并返回数量", notes="需要传来 商品id  gid")
    @ApiImplicitParam(name = "gid", value = "商品id")
    public JsonResult<Integer> addSCnum (Integer gid){
        Integer num = shoppingCartService.updateSCaddNum(gid);
        return new JsonResult(0,num);
    }
    @GetMapping("subSCnum")
    @ApiOperation(value="购物车里减商品数量，并返回数量", notes="需要传来 商品id  gid")
    @ApiImplicitParam(name = "gid", value = "商品id")
    public JsonResult<Integer> subSCnum (Integer gid){
        Integer num = shoppingCartService.updateSCsubNum(gid);
        return new JsonResult(0,num);
    }
}
