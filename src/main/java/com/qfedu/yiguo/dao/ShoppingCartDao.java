package com.qfedu.yiguo.dao;

import com.qfedu.yiguo.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartDao {

    public List<ShoppingCart> findAll(Integer uid);

    public void addSC(ShoppingCart shoppingCart);

    public ShoppingCart findByGoodsId(Integer gid);

    public void deleteSC(Integer[] gids);

    public void updateSCaddNum(Integer gid);

    public void updateSCsubNum(Integer gid);
}
