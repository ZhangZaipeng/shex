package com.shex.api.conf.interceptor;

import com.shex.api.interceptor.AuthLevelInterceptor;
import com.shex.api.interceptor.LanguageInterceptor;
import com.shex.api.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by zhangzp on 2018/8/20 0008.
 */
@Configuration
public class SpringInterceptorRegister extends WebMvcConfigurerAdapter {

    // 添加spring中的拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 语言国际化
        registry.addInterceptor(new LanguageInterceptor()).
                addPathPatterns("/**");

        // 添加 登录 拦截器
        registry.addInterceptor(new LoginInterceptor()).
                    addPathPatterns("/**").excludePathPatterns("/user/*");

        // 添加 认证等级
        registry.addInterceptor(new AuthLevelInterceptor()).
                addPathPatterns("/api/**").excludePathPatterns("");

        super.addInterceptors(registry);
    }

}
