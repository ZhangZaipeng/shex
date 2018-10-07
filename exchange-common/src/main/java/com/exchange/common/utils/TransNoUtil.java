package com.exchange.common.utils;

import com.exchange.common.utils.enums.RecordTypeEnum;

/**
 * Class
 *
 * @author hurun
 */
public class TransNoUtil {


  public static String getNo(RecordTypeEnum recordType, long userId) {
    String systemCode = recordType.getTransNo();
    return OrderGenerater.generateFlowNo(String.valueOf(userId), systemCode);
  }

  public static void main(String[] args) {
    System.out.println(getNo(RecordTypeEnum.SELL,98345321));
  }
}
