package com.qfedu.yiguo.controller;

import com.qfedu.yiguo.common.JsonResult;
import com.qfedu.yiguo.entity.User;
import com.qfedu.yiguo.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.qfedu.yiguo.utils.MD5Utils;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.TimeUnit;

@Api(description ="登录")
@Controller
@ResponseBody
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @ApiOperation(value="手机号密码登录", notes="需要传来 phone手机号 和 password密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号"),
            @ApiImplicitParam(name = "password", value = "密码")
    })
    @PostMapping("/login")
    public JsonResult<String> login(String phone, String password){
        userService.login(phone, password);
        //生成token并加盐haha
        String token = MD5Utils.md5(phone + "haha");
        //存到redis中
        stringRedisTemplate.opsForValue().set(token,phone);
        //过期时间30分钟
        stringRedisTemplate.expire(token,30, TimeUnit.MINUTES);
        return new JsonResult(0,token);
    }

    @PostMapping("/phoneCode")
    @ApiOperation(value="短信验证码", notes="需要传来 phone")
    @ApiImplicitParam(name = "phone", value = "手机号")
    public JsonResult phoneCode(String phone){
        userService.phoneCode(phone);
        return new JsonResult(0,null);
    }

    @PostMapping("/codeVerify")
    @ApiOperation(value="手机登录并校验验证码(登陆后会默认生成一些用户信息包括密码默认123)", notes="需要传来 phone 和验证码code")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号"),
            @ApiImplicitParam(name = "code", value = "验证码")
    })
    public JsonResult<String> codeVerify(String phone,String code){
        userService.codeVerify(phone,code);
        String token = MD5Utils.md5(phone + "hehe");
        stringRedisTemplate.opsForValue().set(token,phone);
        stringRedisTemplate.expire(token,30, TimeUnit.MINUTES);
        return new JsonResult(0,token);
    }

    @PostMapping("/perfect")
    @ApiOperation(value="设置密码", notes="需要传来 token 和 两个密码对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newPass", value = "密码"),
            @ApiImplicitParam(name = "towPass", value = "再次密码"),
    })
    public JsonResult perfect(String token,String password,String towPass){
        userService.perfect(token,password,towPass);
        return new JsonResult(0,null);
    }

    @GetMapping("/imgUrl")
    @ApiOperation(value="上传头像", notes="需要传来 上传文件标签的name='upload'属性")
    @ApiImplicitParam(name = "upload", value = "上传文件标签的name='upload'属性")
    public JsonResult imgUrl (MultipartFile upload){
       userService.imgUrl(upload);
       return new JsonResult(0,null);
    }

    @PostMapping("/updatePass")
    @ApiOperation(value="忘记密码,需要短信验证进行", notes="需要传来 phone ，newPass新密码，towPass再次密码, code短信验证码 ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号"),
            @ApiImplicitParam(name = "newPass", value = "新密码"),
            @ApiImplicitParam(name = "towPass", value = "再次密码"),
            @ApiImplicitParam(name = "code", value = "验证码")
    })
    public JsonResult updatePass(String phone,String newPass,String towPass,String code){
        userService.updatePass(phone,newPass,towPass,code);
        return new JsonResult(0,null);
    }

    @PostMapping("/updateUser")
    @ApiOperation(value="修改个人信息", notes="需要传来 token 和 User对象")
    @ApiImplicitParam(name = "user", value = "用户对象user")
    public JsonResult<User> updateUser(String token,User user){
        User user1 = userService.updateUser(token, user);
        return new JsonResult(0,user1);
    }

    @PostMapping("/exit")
    @ApiOperation(value="注销退出", notes="需要传来 token")
    public JsonResult exit(String token){
        stringRedisTemplate.delete(token);
        return new JsonResult(0,null);
    }


}
