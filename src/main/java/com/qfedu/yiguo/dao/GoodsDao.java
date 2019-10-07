package com.qfedu.yiguo.dao;

import com.qfedu.yiguo.entity.Goods;

import java.util.List;

public interface GoodsDao {
    public Goods findById(Integer id);

    public List<Goods> findAllR();

    public Goods findByName(String goodsName);
}
