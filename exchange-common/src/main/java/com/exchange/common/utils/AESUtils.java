package com.exchange.common.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AESUtils {

  private static final String KEY_ALGORITHM = "AES";
  private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";//默认的加密算法

  /**
   * AES 加密操作
   *
   * @param content 待加密内容
   * @param password 加密密码
   * @return 返回Base64转码后的加密数据
   */
  public static String encrypt(String content, String password) {
    try {
      Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器

      byte[] byteContent = content.getBytes("utf-8");

      cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password));// 初始化为加密模式的密码器

      byte[] result = cipher.doFinal(byteContent);// 加密

      return new String(new BASE64Encoder().encode(result));//通过Base64转码返回
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return null;
  }

  /**
   * AES 解密操作
   *
   * @param content
   * @param password
   * @return
   */
  public static String decrypt(String content, String password) {

    try {
      //实例化
      Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

      //使用密钥初始化，设置为解密模式
      cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password));

      //执行操作
      byte[] result = cipher.doFinal(new BASE64Decoder().decodeBuffer(content));

      return new String(result, "utf-8");
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return null;
  }

  /**
   * 生成加密秘钥
   *
   * @return
   */
  private static SecretKeySpec getSecretKey(final String password) {
    try {
      //返回生成指定算法密钥生成器的 KeyGenerator 对象
      KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
      SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
      secureRandom.setSeed(password.getBytes());

      //AES 要求密钥长度为 128
      kg.init(128, secureRandom);

      //生成一个密钥
      SecretKey secretKey = kg.generateKey();

      return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);// 转换为AES专用密钥
    } catch (NoSuchAlgorithmException ex) {
      ex.printStackTrace();
    }
    return null;
  }

  public static void main(String[] args) {
    String s = "hello,您好";

    System.out.println("s:" + s);

    String s1 = AESUtils.encrypt(s, "1234");
    System.out.println("s1:" + s1);

    System.out.println("s2:"+AESUtils.decrypt(s1, "1234"));


  }

/*  public tatic void main(String[] args) {
    AESUtils se = new AESUtils();

    // 加密的规则
    String encodeRules = "hcwt88888888888";

    *//*
     * 加密
     *//*
    String content = "15629655357";        // 加密的内容
    System.out.println("加密内容" + content);
    String enContent = AESEncode(encodeRules, content);

    System.out.println("根据输入的规则" + encodeRules + "加密后的密文是:" + enContent);

    System.out.println("根据输入的规则" + encodeRules + "解密后的内容是:" + se.AESDncode(encodeRules, enContent));
  }*/
}
