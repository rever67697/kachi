package com.team.config;

import com.team.filter.LoginFilter;
import com.team.util.IConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 上午11:12 2018/4/20
 */
@Configuration
public class LoginConfig {

    @Value("${filter.noFilterPath}")
    private String noFilterPath;


    @Bean
    public FilterRegistrationBean loginFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new LoginFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("noFilterPath", noFilterPath);
        filterRegistrationBean.setInitParameters(initParameters);
        return filterRegistrationBean;
    }

//    @Bean
//    public FilterRegistrationBean logFilter(){
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new LogFilter());
//        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.setOrder(10);
//        Map<String, String> initParameters = new HashMap<>();
////        initParameters.put("excludes", "/verify");
//        initParameters.put("session_user", IConstant.SESSION_USER_NAME+"1");
//        initParameters.put("log_url", "http://localhost:8999/log-customer/log/save");
//        filterRegistrationBean.setInitParameters(initParameters);
//        return filterRegistrationBean;
//    }
}
