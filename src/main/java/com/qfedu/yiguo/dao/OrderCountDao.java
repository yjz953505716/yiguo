package com.qfedu.yiguo.dao;

import com.qfedu.yiguo.entity.OrderCount;

import java.util.List;

public interface OrderCountDao {

    public OrderCount findAll(Integer uid);

    public void addOrder(OrderCount orderCount);
}
