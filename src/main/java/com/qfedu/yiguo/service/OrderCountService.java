package com.qfedu.yiguo.service;

import com.qfedu.yiguo.entity.OrderCount;

import java.util.List;

public interface OrderCountService {

    public OrderCount findAll(String token);

    public void addOrder(String token,Integer[] gids,Double money);
}
