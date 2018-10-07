package com.exchange.api.conf.filter;

import net.bull.javamelody.MonitoringFilter;
import net.bull.javamelody.SessionListener;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.contexchanget.annotation.Bean;
import org.springframework.core.annotation.Order;

/**
 * Created by zhangzp on 2018/8/19.
 */
public class JavamelodyConfiguration {

    @Bean
    @Order(Integer.MAX_VALUE-1)
    public FilterRegistrationBean monitoringFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MonitoringFilter());
        registration.addUrlPatterns("/*");
        registration.setName("monitoring");
        return registration;
    }

    @Bean
    public ServletListenerRegistrationBean<SessionListener> servletListenerRegistrationBean() {
        ServletListenerRegistrationBean<SessionListener> slrBean = new ServletListenerRegistrationBean<SessionListener>();
        slrBean.setListener(new SessionListener());
        return slrBean;
    }
}
