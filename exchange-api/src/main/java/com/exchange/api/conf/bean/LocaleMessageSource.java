package com.exchange.api.conf.bean;

import org.springframework.contexchanget.MessageSource;
import org.springframework.contexchanget.i18n.LocaleContexchangetHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * Created by zhangzp on 2018/8/19.
 */
@Component
public class LocaleMessageSource {

    @Resource
    private MessageSource messageSource;

    /**
     * @param code ：对应messages配置的key.
     * @return
     */
    public String getMessage(String code){
        return getMessage(code,null);
    }

    /**
     * @param code ：对应messages配置的key.
     * @param args : 数组参数.
     * @return
     */
     public String getMessage(String code, Object[] args){
        return getMessage(code, args,"");
    }

    /**
     *
     * @param code ：对应messages配置的key.
     * @param args : 数组参数.
     * @param defaultMessage : 没有设置key的时候的默认值.
     * @return
     */
    public String getMessage(String code, Object[] args, String defaultMessage){
        //这里使用比较方便的方法，不依赖request.
        Locale locale = LocaleContexchangetHolder.getLocale();
        return messageSource.getMessage(code, args, defaultMessage, locale);
    }
}