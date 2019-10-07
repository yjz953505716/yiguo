package com.qfedu.yiguo.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.qfedu.yiguo.dao.UserDao;
import com.qfedu.yiguo.entity.User;
import com.qfedu.yiguo.service.UserService;
import com.qfedu.yiguo.utils.ImgUtils;
import com.qfedu.yiguo.utils.MD5Utils;
import com.qfedu.yiguo.utils.NameUtils;
import com.qfedu.yiguo.utils.PhoneCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserDao userDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //登录
    @Override
    public void login(String phone, String password) {
        User user = userDao.findByPhone(phone);
        if (user == null){
            throw new RuntimeException("手机号错误");
        }
        if (!user.getPassword().equals(password)){
            throw new RuntimeException("密码错误");
        }
    }

    //设置密码
    @Override
    public void perfect(String token, String password,String towPass) {
        String phone = stringRedisTemplate.opsForValue().get(token);
        User user = userDao.findByPhone(phone);
        if (!password.equals(towPass)){
            throw new RuntimeException("两次密码不正确");
        }
        user.setPassword(password);
        userDao.updateUser(user);
    }

    //忘记密码
    @Override
    public void updatePass(String phone, String newPass, String towPass, String code) {
        if (code == null && code == ""){
            throw new RuntimeException("验证码不能为空");
        }
        String md5Code = MD5Utils.md5(code + "haha");
        String code1 = stringRedisTemplate.opsForValue().get(md5Code);
        if (code1 == null){
            throw new RuntimeException("验证码错误");
        }
        if (!newPass.equals(towPass)){
            throw new RuntimeException("两次密码错误");
        }
        User user = userDao.findByPhone(phone);
        user.setPassword(newPass);
        userDao.updateUser(user);
    }

    //短信验证码
    @Override
    public void phoneCode(String phone) {
        PhoneCode.setNewcode();
        String code = Integer.toString(PhoneCode.getNewcode());
        try {
            PhoneCode.sendSms(phone, code);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        String md5Code = MD5Utils.md5(code + "haha");
        stringRedisTemplate.opsForValue().set(md5Code,code);
        stringRedisTemplate.expire(code,5, TimeUnit.MINUTES);
    }

    //手机登录校验验证码
    @Override
    public void codeVerify(String phone, String code) {
        User user1 = userDao.findByPhone(phone);
        if (user1 != null){
            throw new RuntimeException("手机号已注册");
        }
        String md5Code = MD5Utils.md5(code + "haha");
        String code1 = stringRedisTemplate.opsForValue().get(md5Code);
        if (code1 == null){
            throw new RuntimeException("验证码错误");
        }
        if (code == null && code == ""){
            throw new RuntimeException("验证码不能为空");
        }
        /*添加用户*/
        //自动生成昵称
        String name = NameUtils.creatName();
        User user = new User();
        user.setPhone(phone);
        //默认密码
        user.setPassword("123");
        user.setName(name);
        user.setShimingRZ("未认证");
        try {
            //自定生成头像
            String img = ImgUtils.createPhotoImg(name);
            user.setImgurl(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
        userDao.addUser(user);
    }

    //上传头像
    @Override
    public void imgUrl( MultipartFile upload) {

        String path = "/usr/local/tomcat/webapps/yiguoImgs";
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        User user = new User();
        String filename = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid+"_"+filename;
        try {
            upload.transferTo(new File(path,filename));
            user.setImgurl("http://118.31.2.64:8080/yiguoImgs/"+filename);
            userDao.updateUser(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("上传头像失败");
    }

    //修改个人信息
    @Override
    public User updateUser(String token, User user) {
        String phone = stringRedisTemplate.opsForValue().get(token);
        User user1 = userDao.findByPhone(phone);
        if (user1 == null){
            throw new RuntimeException("请先登录");
        }
        user.setId(user1.getId());
        user.setPhone(user1.getPhone());
        userDao.updateUser(user);
        return user;
    }
}
