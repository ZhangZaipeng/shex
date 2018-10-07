package com.exchange.api.interceptor;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContexchangetUtils;

import javax.servlet.Servletexchangeception;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhangzp on 2018/8/19 下午4:30.
 */
public class LanguageInterceptor exchangetends HandlerInterceptorAdapter {
    public static final String DEFAULT_PARAM_NAME = "locale";
    private String paramName = "locale";

    public LanguageInterceptor() {
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamName() {
        return this.paramName;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Servletexchangeception {
        String newLocale = request.getParameter(this.paramName);
        // String newLocale = WebUtil.getCookie(request, "lang");
        if (newLocale != null) {
            LocaleResolver localeResolver = RequestContexchangetUtils.getLocaleResolver(request);
            if (localeResolver == null) {
                throw new IllegalStateexchangeception("No LocaleResolver found: not in a DispatcherServlet request?");
            }

            localeResolver.setLocale(request, response, StringUtils.parseLocaleString(newLocale));
        }

        return true;
    }

}
