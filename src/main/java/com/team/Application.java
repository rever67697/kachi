package com.team;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;

import com.team.service.InterfaceService;
import com.team.service.impl.InterfaceServiceImpl;
import com.team.util.SpringUtil;
import org.apache.catalina.Context;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.context.request.RequestContextListener;

import com.github.pagehelper.PageHelper;

@SpringBootApplication			//@SpringBootApplication = (默认属性)@Configuration + @EnableAutoConfiguration + @ComponentScan
@MapperScan("com.team.dao")		//查找报指定包及其子包下面的dao接口
public class Application{
	private static Logger logger = Logger.getLogger(Application.class);

    /**
     * 数据源，druid连接池
     */
    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name="dataSource", destroyMethod = "close", initMethod="init")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
    	logger.info("-------------------- writeDataSource init ---------------------");
	    return DataSourceBuilder.create().type(dataSourceType).build();
    }
    
    /**
     * 配置SqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties props = new Properties();
        props.setProperty("reasonable", "true");
        props.setProperty("supportMethodsArguments", "true");
        props.setProperty("returnPageInfo", "check");
        props.setProperty("params", "count=countSql");
        pageHelper.setProperties(props);
        //添加插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactoryBean.getObject();
    }
    
    /**  
     * 配置事务管理器  
     */  
    @Bean(name="transactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 配置session有效时间，这里默认是30min
     *@return
     *return
     */
    //@Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
     return new EmbeddedServletContainerCustomizer() {
      @Override
      public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setSessionTimeout(3600);//单位为S
      }
     };
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
     * 关闭其他不安全的请求方法，只允许GET和POST
     * @return
     */
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                collection.addMethod("HEAD");
                collection.addMethod("PUT");
                collection.addMethod("DELETE");
                collection.addMethod("OPTIONS");
                collection.addMethod("TRACE");
                collection.addMethod("COPY");
                collection.addMethod("SEARCH");
                collection.addMethod("PROPFIND");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        return tomcat;
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
