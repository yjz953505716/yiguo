package com.qfedu.yiguo.service;

import com.qfedu.yiguo.entity.Goods;

import java.util.List;

public interface GoodsService {

    public Goods findById(Integer id);

    /*热门商品*/
    public List<Goods> findAllR();

    public Goods findByName(String goodsName);
}
