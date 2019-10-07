package com.qfedu.yiguo.service.impl;

import com.qfedu.yiguo.dao.GoodsDao;
import com.qfedu.yiguo.entity.Goods;
import com.qfedu.yiguo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired(required = false)
    private GoodsDao goodsDao;

    @Override
    public Goods findById(Integer id) {
        return goodsDao.findById(id);
    }

    @Override
    public List<Goods> findAllR() {
        return goodsDao.findAllR();
    }

    @Override
    public Goods findByName(String goodsName) {
        return goodsDao.findByName(goodsName);
    }
}
