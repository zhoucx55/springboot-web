package com.mashibing.springbootweb;

import com.mashibing.springbootweb.listen.MyHttpSessionListener;
import com.mashibing.springbootweb.servlet.MyServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

/**
 * 在SpringBootApplication上使用@ServletComponentScan注解后，
 *  Servlet、Filter、Listener可以直接通过@WebServlet、@WebFilter、@WebListener注解自动注册，无需其他代码
 */
@SpringBootApplication
@ServletComponentScan
public class SpringbootWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebApplication.class, args);
    }

    // 第一种@WebServlet(name = "myServlet",urlPatterns = "/srv")
    //第二种@Bean
    @Bean
    public ServletRegistrationBean<MyServlet> getServletRegistrationBean(){
        ServletRegistrationBean<MyServlet> bean = new ServletRegistrationBean<>(new MyServlet());
        ArrayList<String> url = new ArrayList<>();
        url.add("/srv");
        bean.setUrlMappings(url);
        bean.setLoadOnStartup(1);
        return bean;
    }

    @Bean
    public ServletListenerRegistrationBean listenerRegist(){
        ServletListenerRegistrationBean srb = new ServletListenerRegistrationBean();
        srb.setListener(new MyHttpSessionListener());
        return srb;
    }


}
