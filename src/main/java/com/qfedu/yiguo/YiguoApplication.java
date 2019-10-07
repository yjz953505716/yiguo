package com.qfedu.yiguo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.servlet.annotation.WebFilter;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.qfedu.yiguo.dao")
public class YiguoApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(YiguoApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(YiguoApplication.class, args);
    }

}
