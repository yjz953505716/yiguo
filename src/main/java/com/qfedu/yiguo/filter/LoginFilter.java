package com.qfedu.yiguo.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.qfedu.yiguo.common.JsonResult;
import com.qfedu.yiguo.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter("/*")
public class LoginFilter implements Filter {

    @Autowired
    StringRedisTemplate stringRedisTemplate;



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        String method = request.getMethod();
        //向请求头中写入数据后，会自动先发一个OPTIONS的请求
        //我们不用处理该请求,直接返回
        if (method.equals("OPTIONS")){
            return;
        }
        String uri = request.getRequestURI();
        if (uri.contains("login")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
//            String token = request.getParameter("token");
            //从请求头中获取token
            String token = request.getHeader("token");
            String ajax = request.getHeader("x-requested-with");
            if (token == null || token.equals("")){
                ajaxRtu(request, response, ajax);
            }else {
                String name = stringRedisTemplate.opsForValue().get(token);
                if (name == null){
                    ajaxRtu(request, response, ajax);
                }
                String token2 = MD5Utils.md5(name + "haha");
                if (token.equals(token2)){
                    filterChain.doFilter(servletRequest,servletResponse);
                }else {
                    ajaxRtu(request, response, ajax);
                }
            }
        }
    }

    //判断是否ajax请求
    private void ajaxRtu(HttpServletRequest request, HttpServletResponse response, String ajax) throws IOException {
        if (ajax != null && ajax.equals("XMLHttpRequest")){
            JsonResult result = new JsonResult(1,"权限不够");
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonStr = objectMapper.writeValueAsString(result);
            response.getWriter().write(jsonStr);
        }else {
            //重定向
            response.sendRedirect(request.getContextPath()+"/login.html");
        }
    }

    @Override
    public void destroy() {

    }
}
