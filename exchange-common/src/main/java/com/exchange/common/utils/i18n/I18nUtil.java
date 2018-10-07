package com.exchange.common.utils.i18n;

import java.io.UnsupportedEncodingexchangeception;
import java.util.Locale;
import java.util.ResourceBundle;

public class I18nUtil {

  private static ResourceBundle getBundle(String code){
    String []str = code.split("_");
    String properties = "Bcexchange_" + code;
    Locale locale = new Locale(str[0],str[1]);
    return ResourceBundle.getBundle(properties,locale);
  }

  public static String getResource(String key, String code){
    ResourceBundle resourceBundle = getBundle(code);
    String str;
    try {
      str = new String(resourceBundle.getString(key).getBytes("ISO-8859-1"),"UTF-8");
    } catch (UnsupportedEncodingexchangeception e) {
      str = "There is a problem with the language system";
      e.printStackTrace();
    }
    return str;
  }

  public static void main(String[] args) {
    System.out.println(getResource(I18nKeys.USER_LOGIN_ENTERTYPE,"zh_CN"));
  }
}
