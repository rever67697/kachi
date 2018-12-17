package com.team;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.MultipartConfigElement;

import com.team.service.InterfaceService;
import com.team.service.impl.InterfaceServiceImpl;
import com.team.util.SpringUtil;
import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.context.request.RequestContextListener;

import com.github.pagehelper.PageHelper;

@SpringBootApplication			//@SpringBootApplication = (默认属性)@Configuration + @EnableAutoConfiguration + @ComponentScan
@MapperScan("com.team.dao")		//查找报指定包及其子包下面的dao接口
public class Application{
	private static Logger logger = Logger.getLogger(Application.class);

    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("reasonable", "true");
        p.setProperty("supportMethodsArguments", "true");
        p.setProperty("returnPageInfo", "check");
        p.setProperty("params", "count=countSql");
        //通过设置pageSize=0或者RowBounds.limit = 0就会查询出全部的结果。
        p.setProperty("pageSizeZero", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }


    /**
     * RequestContextListener注册
     */
    @Bean
    public ServletListenerRegistrationBean<RequestContextListener> requestContextListenerRegistration() {
        return new ServletListenerRegistrationBean<RequestContextListener>(new RequestContextListener());
    }

    /**
     * 注册日期绑定
     * @return
     */
    @Bean
    public Converter<String, Date> addNewConvert() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = null;
                try {
                    date = sdf.parse((String) source);
                } catch (ParseException e) {
                     sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        date = sdf.parse((String) source);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }

                }
                return date;
            }
        };
    }

    /**
     * 文件上传配置
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize("50MB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("50MB");
        return factory.createMultipartConfig();
    }


        /**
         * 启动入口main方法
         */
    public static void main(String[] args) throws Exception{
        SpringApplication.run(Application.class, args);
        logger.info("SpringBoot Start Success!");
        InterfaceService interfaceService = SpringUtil.getBean(InterfaceServiceImpl.class);

        interfaceService.aliMessage();
//        System.out.println("\n"+
//                "                  oo8oo_                           \n"+
//                "                o8888888o                          \n"+
//                "                88\" . \"88                          \n"+
//                "                (| -_- |)                          \n"+
//                "                0\\  =  /0                          \n"+
//                "              ___/'==='\\___                        \n"+
//                "            .' \\|     |// '.                      \n"+
//                "           / \\|||  :  |||// \\                     \n"+
//                "          / _||||| -:- |||||_ \\                    \n"+
//                "         |   | \\\\\\  -  /// |   |                   \n"+
//                "         | \\_|  ''\\---/''  |_/ |                   \n"+
//                "         \\  .-\\__  '-'  __/-.  /                   \n"+
//                "       ___'. .'  /--.--\\  '. .'___                 \n"+
//                "    .\"\" '<  '.___\\_<|>_/___.'  >' \"\".              \n"+
//                "   | | :  `- \\`.:`\\ _ /`:.`/ -`  : | |             \n"+
//                "   \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /             \n"+
//                " =====`-.____`.___ \\_____/ ___.`____.-`=====     \n"+
//                "                 `=---=`                           \n"+
//                "                                                   \n"+
//                "                                                   \n"+
//                " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~        \n"+
//                "                                                           \n"+
//                "      佛祖保佑         永不宕机/永无bug ");
    }

    
}
