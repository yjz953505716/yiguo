package com.qfedu.yiguo.service.impl;

import com.qfedu.yiguo.dao.OrderCountDao;
import com.qfedu.yiguo.dao.OrderCountSpecDao;
import com.qfedu.yiguo.dao.ShoppingCartDao;
import com.qfedu.yiguo.dao.UserDao;
import com.qfedu.yiguo.entity.OrderCount;
import com.qfedu.yiguo.entity.OrderCountSpec;
import com.qfedu.yiguo.entity.ShoppingCart;
import com.qfedu.yiguo.entity.User;
import com.qfedu.yiguo.service.OrderCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderCountServiceImpl implements OrderCountService {

    @Autowired(required = false)
    private OrderCountDao orderCountDao;

    @Autowired(required = false)
    private UserDao userDao;

    @Autowired(required = false)
    private OrderCountSpecDao orderCountSpecDao;

    @Autowired(required = false)
    private ShoppingCartDao shoppingCartDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public OrderCount findAll(String token) {
        String phone = stringRedisTemplate.opsForValue().get(token);
        User user = userDao.findByPhone(phone);
        if (user == null){
            throw new RuntimeException("请登录");
        }
        return orderCountDao.findAll(user.getId());
    }

    @Override
    public void addOrder(String token,Integer[] gids, Double money) {
        String phone = stringRedisTemplate.opsForValue().get(token);
        User user = userDao.findByPhone(phone);
        if (user == null){
            throw new RuntimeException("请登录");
        }
        OrderCount orderCount = new OrderCount();
        orderCount.setUid(user.getId());
        orderCount.setMoney(money);
        orderCountDao.addOrder(orderCount);

        for (Integer gid : gids) {
            OrderCountSpec orderCountSpec = new OrderCountSpec();
            orderCountSpec.setGid(gid);
            orderCountSpec.setOid(orderCountDao.findAll(user.getId()).getId());
            orderCountSpec.setgNum(shoppingCartDao.findByGoodsId(gid).getGoodsNum());
            orderCountSpecDao.addOCS(orderCountSpec);
        }

    }
}
