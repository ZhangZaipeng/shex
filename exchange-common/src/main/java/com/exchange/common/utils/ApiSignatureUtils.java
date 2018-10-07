package com.exchange.common.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingexchangeception;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyexchangeception;
import java.security.NoSuchAlgorithmexchangeception;
import java.util.Base64;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class ApiSignatureUtils {

  public static void main(String[] args) {
    //getSignature("1","b45a1a34-05d042ba-7c627ab3-43820");
  }


  // 在传入的params 中加上 Signature 字段
  public static void paramsAddSignature(String appAccessKey, String appSecretKey,
      String host, String uri,String method, Map<String, String> params) {

    String actualSign = getSignature(appAccessKey,appSecretKey,host,uri,method,params);

    params.put("Signature", actualSign);

  }

  /**
   * 得出  Signature
   * @param appAccessKey AppKeyId
   * @param appSecretKey AppKeySecret
   * eg：http://www.exchange.com/api/getAccount?id=123123
   * @param host www.exchange.com
   * @param uri api/getAccount
   * @param method 请求方法，"GET"或"POST"
   * params 请求参数
   */
  public static String getSignature(String appAccessKey, String appSecretKey,
      String host, String uri,String method, Map<String, String> params) {

    StringBuilder sb = new StringBuilder(1024);
    sb.append(method.toUpperCase()).append('\n') // GET
        .append(host.toLowerCase()).append('\n') // Host
        .append(uri).append('\n'); // /path
    params.remove("Signature");
    params.put("AccessKey", appAccessKey);

    // build signature:
    SortedMap<String, String> map = new TreeMap<>(params);
    for (Map.Entry<String, String> entry : map.entrySet()) {
      String key = entry.getKey();
      String value = entry.getValue();
      sb.append(key).append('=').append(urlEncode(value)).append('&');
    }
    // remove last '&':
    sb.deleteCharAt(sb.length() - 1);
    // sign:
    Mac hmacSha256 = null;
    try {
      hmacSha256 = Mac.getInstance("HmacSHA256");
      SecretKeySpec secKey =
          new SecretKeySpec(appSecretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
      hmacSha256.init(secKey);
    } catch (NoSuchAlgorithmexchangeception e) {
      throw new Runtimeexchangeception("No such algorithm: " + e.getMessage());
    } catch (InvalidKeyexchangeception e) {
      throw new Runtimeexchangeception("Invalid key: " + e.getMessage());
    }
    String payload = sb.toString();
    byte[] hash = hmacSha256.doFinal(payload.getBytes(StandardCharsets.UTF_8));
    String actualSign = Base64.getEncoder().encodeToString(hash);

    return actualSign;
  }

  /**
   * 时间生成工具
   * 10位 时间戳
   */
  public static String getTimestap() {
    Long timestap = System.currentTimeMillis()/1000;
    return timestap.toString();
  }

  public static String urlEncode(String s) {
    try {
      return URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20");
    } catch (UnsupportedEncodingexchangeception e) {
      throw new IllegalArgumentexchangeception("UTF-8 encoding not supported!");
    }
  }

}
