package com.exchange.common.utils;

import com.exchange.common.exception.ResultErrexchangeception;
import com.exchange.common.response.ResponseCode;

import java.texchanget.Parseexchangeception;
import java.texchanget.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regexchange.Matcher;
import java.util.regexchange.Pattern;

public class ValidatorUtil {
  public static final String REG_INTEGER_OR_DECIMAL = "^[0-9]+\\.{0,1}[0-9]{0,2}$";
  public static final String REG_DECIMAL = "^[0]+\\.{0,1}[0-9]{0,3}$";
  public static final String REG_INTEGER = "^[0-9]*$";
  public static final String REG_INTEGER_GT_ZERO = "^\\+?[1-9][0-9]*$";
  public static final String REG_CHINESE = "^[一-龥]{0,}$";
  public static final String REG_TELEPTHONE = "^(\\(\\d{3,4}-)|\\d{3.4}-)?\\d{7,8}$";

  public static void IDCardValidate(String IDStr) throws Parseexchangeception {
    String errorInfo = "";
    String[] ValCodeArr = new String[]{"1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2"};
    String[] Wi = new String[]{"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2"};
    String Ai = "";
    if (IDStr.length() != 15 && IDStr.length() != 18) {
      throw new ResultErrexchangeception(ResponseCode.FAIL, "请输入正确的身份证号码");
    } else {
      if (IDStr.length() == 18) {
        Ai = IDStr.substring(0, 17);
      } else if (IDStr.length() == 15) {
        Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
      }

      if (!isNumeric(Ai)) {
        throw new ResultErrexchangeception(ResponseCode.FAIL, "请输入正确的身份证号码");
      } else {
        String strYear = Ai.substring(6, 10);
        String strMonth = Ai.substring(10, 12);
        String strDay = Ai.substring(12, 14);
        if (!isDate(strYear + "-" + strMonth + "-" + strDay)) {
          throw new ResultErrexchangeception(ResponseCode.FAIL, "请输入正确的身份证号码");
        } else {
          GregorianCalendar gc = new GregorianCalendar();
          SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

          try {
            if (gc.get(1) - Integer.parseInt(strYear) > 150 || gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime() < 0L) {
              throw new ResultErrexchangeception(ResponseCode.FAIL, "请输入正确的身份证号码");
            }
          } catch (NumberFormatexchangeception var14) {
            var14.printStackTrace();
          } catch (Parseexchangeception var15) {
            var15.printStackTrace();
          }

          if (Integer.parseInt(strMonth) <= 12 && Integer.parseInt(strMonth) != 0) {
            if (Integer.parseInt(strDay) <= 31 && Integer.parseInt(strDay) != 0) {
              Hashtable h = GetAreaCode();
              if (h.get(Ai.substring(0, 2)) == null) {
                throw new ResultErrexchangeception(ResponseCode.FAIL, "请输入正确的身份证号码");
              } else {
                int TotalmulAiWi = 0;

                int modValue;
                for(modValue = 0; modValue < 17; ++modValue) {
                  TotalmulAiWi += Integer.parseInt(String.valueOf(Ai.charAt(modValue))) * Integer.parseInt(Wi[modValue]);
                }

                modValue = TotalmulAiWi % 11;
                String strVerifyCode = ValCodeArr[modValue];
                Ai = Ai + strVerifyCode;
                if (IDStr.length() == 18) {
                  if (!Ai.equals(IDStr)) {
                    throw new ResultErrexchangeception(ResponseCode.FAIL, "请输入正确的身份证号码");
                  }
                } else {
                  throw new ResultErrexchangeception(ResponseCode.FAIL, "请输入正确的身份证号码");
                }
              }
            } else {
              throw new ResultErrexchangeception(ResponseCode.FAIL, "请输入正确的身份证号码");
            }
          } else {
            throw new ResultErrexchangeception(ResponseCode.FAIL, "请输入正确的身份证号码");
          }
        }
      }
    }
  }

  private static Hashtable GetAreaCode() {
    Hashtable hashtable = new Hashtable();
    hashtable.put("11", "北京");
    hashtable.put("12", "天津");
    hashtable.put("13", "河北");
    hashtable.put("14", "山西");
    hashtable.put("15", "内蒙古");
    hashtable.put("21", "辽宁");
    hashtable.put("22", "吉林");
    hashtable.put("23", "黑龙江");
    hashtable.put("31", "上海");
    hashtable.put("32", "江苏");
    hashtable.put("33", "浙江");
    hashtable.put("34", "安徽");
    hashtable.put("35", "福建");
    hashtable.put("36", "江西");
    hashtable.put("37", "山东");
    hashtable.put("41", "河南");
    hashtable.put("42", "湖北");
    hashtable.put("43", "湖南");
    hashtable.put("44", "广东");
    hashtable.put("45", "广西");
    hashtable.put("46", "海南");
    hashtable.put("50", "重庆");
    hashtable.put("51", "四川");
    hashtable.put("52", "贵州");
    hashtable.put("53", "云南");
    hashtable.put("54", "西藏");
    hashtable.put("61", "陕西");
    hashtable.put("62", "甘肃");
    hashtable.put("63", "青海");
    hashtable.put("64", "宁夏");
    hashtable.put("65", "新疆");
    hashtable.put("71", "台湾");
    hashtable.put("81", "香港");
    hashtable.put("82", "澳门");
    hashtable.put("91", "国外");
    return hashtable;
  }

  public static boolean isDate(String strDate) {
    Pattern pattern = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
    Matcher m = pattern.matcher(strDate);
    return m.matches();
  }



  public static boolean isNumeric(String str) {
    Pattern pattern = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");
    Matcher isNum = pattern.matcher(str);
    return isNum.matches();
  }

  public static void main(String[] args) {
    System.out.println(isNumeric("2312"));
  }

  public static boolean isEmail(String str) {
    return StringUtils.isEmpty(str) ? false : str.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
  }

  public static boolean isMobile(String str) {
    return StringUtils.isEmpty(str) ? false : str.matches("^(13|14|15|16|18|17)\\d{9}$");
  }

  public static boolean isChineseName(String str) {
    return StringUtils.isEmpty(str) ? false : str.matches("[\\u4e00-\\u9fa5]{2,4}");
  }
}
