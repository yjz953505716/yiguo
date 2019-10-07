package com.qfedu.yiguo.service.impl;

import com.qfedu.yiguo.dao.ShoppingCartDao;
import com.qfedu.yiguo.dao.UserDao;
import com.qfedu.yiguo.entity.ShoppingCart;
import com.qfedu.yiguo.entity.User;
import com.qfedu.yiguo.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional //开启事务
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired(required = false)
    private ShoppingCartDao shoppingCartDao;

    @Autowired(required = false)
    private UserDao userDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //购物车展示
    @Override
    public List<ShoppingCart> findAll(String token) {
        String phone = stringRedisTemplate.opsForValue().get(token);
        User user = userDao.findByPhone(phone);
        if (user == null){
            throw new RuntimeException("请先登录");
        }
        List<ShoppingCart> cartList = shoppingCartDao.findAll(user.getId());
        if (cartList == null){
            throw new RuntimeException("亲~! 还没有加入任何购物车呦~~");
        }
        return cartList;
    }

    //加入购物车
    @Override
    public void addSC(String token, Integer gid) {
        String phone = stringRedisTemplate.opsForValue().get(token);
        User user = userDao.findByPhone(phone);
        if (user == null){
            throw new RuntimeException("请先登录");
        }
        //加入购物车时进行判断，存在就数量+1,不存在就存入购物车
        ShoppingCart shoppingCart = shoppingCartDao.findByGoodsId(gid);
        if (shoppingCart != null){
            shoppingCartDao.updateSCaddNum(gid);
        } else {
            ShoppingCart shoppingCart1 = new ShoppingCart();
            shoppingCart1.setGid(gid);
            shoppingCart1.setUid(user.getId());
            shoppingCart1.setGoodsNum(1);
            shoppingCartDao.addSC(shoppingCart1);
        }
    }

    //批量删除
    @Override
    public void deleteSC(Integer[] gid) {
        shoppingCartDao.deleteSC(gid);
    }

    //+
    @Override
    public Integer updateSCaddNum(Integer gid) {
        shoppingCartDao.updateSCaddNum(gid);
        return shoppingCartDao.findByGoodsId(gid).getGoodsNum();
    }

    //-
    @Override
    public Integer updateSCsubNum(Integer gid) {
        shoppingCartDao.updateSCsubNum(gid);
        ShoppingCart shoppingCart = shoppingCartDao.findByGoodsId(gid);
        Integer goodsNum = shoppingCart.getGoodsNum();
        if (goodsNum < 1){
            throw new RuntimeException("商品数量最少为1");
        }
        return shoppingCartDao.findByGoodsId(gid).getGoodsNum();
    }
}
