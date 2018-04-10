package com.team.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 下午10:08 2018/4/6
 */
//@Configuration
public class WebMvcAdapter extends WebMvcConfigurerAdapter{
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/Webmvc").setViewName("index");
    }
}
