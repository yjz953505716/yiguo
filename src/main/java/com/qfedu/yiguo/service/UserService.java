package com.qfedu.yiguo.service;

import com.qfedu.yiguo.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    /*登录*/
    public void login(String phone,String password);

    /*设置密码*/
    public void perfect(String token,String password,String towPass);

    /*忘记密码*/
    public void updatePass(String phone,String newPass,String towPass,String code);

    /*短信验证码*/
    public void phoneCode(String phone);

    /*手机号验证码登录*/
    public void codeVerify(String phone,String code);

    /*上传头像*/
    public void imgUrl(MultipartFile upload);

    /*修改个人信息*/
    public User updateUser(String token,User user);
}
