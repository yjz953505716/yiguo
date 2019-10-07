package com.qfedu.yiguo.controller;

import com.qfedu.yiguo.common.JsonResult;
import com.qfedu.yiguo.entity.Goods;
import com.qfedu.yiguo.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(description ="商品")
@Controller
@ResponseBody
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/goodsId")
    @ApiOperation(value="商品详情", notes="需要传来 gid")
    @ApiImplicitParam(name = "gid", value = "商品id")
    public JsonResult<Goods> goodsId(Integer gid){
        Goods goods = goodsService.findById(gid);
        if (goods == null){
            return new JsonResult(1,"加载失败");
        }
        return new JsonResult(0,goods);
    }

    @GetMapping("/HOTgoods")
    @ApiOperation(value="热门推荐商品",notes="返回的是 商品集合goodsList")
    public JsonResult<List<Goods>> HOTgoods(){
        List<Goods> goodsList = goodsService.findAllR();
        return new JsonResult(0,goodsList);
    }

    @GetMapping("/goodsName")
    @ApiOperation(value="商品查询", notes="需要传来 商品名称goodsName 返回一个商品对象goods")
    @ApiImplicitParam(name = "goodsName", value = "商品名称")
    public JsonResult<Goods> goodsName(String goodsName){
        Goods goods = goodsService.findByName(goodsName);
        return new JsonResult(0,goods);
    }
}
