package com.qfedu.yiguo.service;

import com.qfedu.yiguo.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    public List<ShoppingCart> findAll(String token);

    public void addSC(String token,Integer gid);

    public void deleteSC(Integer[] gids);

    public Integer updateSCaddNum(Integer gid);

    public Integer updateSCsubNum(Integer gid);
}
