package com.team.cache;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 下午10:55 2018/4/2
 */
//@Configuration
public class DruidConfiguration {

    @Bean
    public ServletRegistrationBean statViewService(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet());

        servletRegistrationBean.addInitParameter("allow","127.0.0.1");
        servletRegistrationBean.addInitParameter("deny","192.168.1.100");

        servletRegistrationBean.addInitParameter("loginUsername","admin");
        servletRegistrationBean.addInitParameter("loginPassword","888888");

        servletRegistrationBean.addInitParameter("resetEnable","false");
        return  servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean statFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusion","*.js,*.css,*.png,*.jpg,*.gif");
        return filterRegistrationBean;
    }

}
