package com.shex.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 昊博短信 工具类
 */
public class HBSmsUtil {

  private static String urlAddress = "http://101.227.68.49:7891/mt?";   // 请求地址
  private static String uname = ConfigUtils.getString("hb.uname");        // 账号
  private static String pwd = ConfigUtils.getString("hb.passwd");          // 密码

  private static int dc = 15;// 0表示英文，8表示UCS2，15表示中文
  private static String hexString = "0123456789ABCDEF";

  /**
   * @param phone 手机号
   * @param head 表头
   * @param content 内容
   * @return 成功/失败
   */
  public static Boolean sendMessage(String phone, String head, String content) {
    return sendMessage(phone, head + content);
  }

  /**
   * @param phone 手机号
   * @param content 发送短信内容
   * @return 成功/失败
   */
  public static Boolean sendMessage(String phone, String content) {

    String rs = "";
    try {
      String url =
          urlAddress + "un=" + uname + "&pw=" + pwd + "&da=" + phone + "&sm=" + encode(content)
              + "&dc=" + dc + "&rd=1";
      URL urlclass = new URL(url);
      BufferedReader in = null;
      try {
        URLConnection connection = urlclass.openConnection();

        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuffer sb = new StringBuffer();

        String line = null;

        while ((line = in.readLine()) != null) {
          sb.append(line);
        }

        rs = sb.toString();


      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        in.close();
      }

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    // 发送短信  失败 r=9103 , r=9101  |  成功  id=154545211884963170
    if (rs.contains("id=")) {
      return true;
    } else {
      return false;
    }
  }


  public static String encode(String str) throws UnsupportedEncodingException {

    // 根据默认编码获取字节数组

    byte[] bytes = str.getBytes("GB2312");

    StringBuilder sb = new StringBuilder(bytes.length * 2);

    // 将字节数组中每个字节拆解成2位16进制整数

    for (int i = 0; i < bytes.length; i++) {

      sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));

      sb.append(hexString.charAt((bytes[i] & 0x0f)));

    }

    return sb.toString();

  }

  public static void main(String[] args) throws Exception {

    String content = "【签名】 内容 哈哈哈哈";

    System.out.println(sendMessage("13308629859", content));

  }
}
