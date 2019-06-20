package com.yu.huobi.config;

import com.yu.huobi.config.intercepors.LoginInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Yu
 * @create 2019/6/15
 * @since 1.0.0
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    LoginInterceptor mLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(mLoginInterceptor).addPathPatterns("/person/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }


    //    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/toLogin").setViewName("login");
//    }
}