package com.exchange.common.utils;

import com.exchange.common.utils.email.MailContent;
import com.exchange.common.utils.email.MailSender;
import com.exchange.common.utils.enums.VerifyCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendTeleEmailUtils {

  private final static Logger log = LoggerFactory.getLogger(SendTeleEmailUtils.class);

  public static boolean sendEmail(String[] responseStr, String verifyCode, String email){
    MailContent mailContent = new MailContent();
    mailContent.setUserTo(new String[]{email});
    mailContent.setTitle("【BITRR】用户邮箱验证码");
    mailContent.setBody(MailSender.MAIL_AUTH_CODE.replace("${code}", verifyCode));
    MailSender.sendSingleMail(mailContent);
    responseStr[0] = "验证码已发送到您的邮箱" + email.substring(0,1) + "***" + email.substring(email.indexOf('@')-1) + "，请登录邮箱查看，5分钟内有效。";
    log.info("发送邮箱信息验证码成功 account ===> {} code {}", email, verifyCode);
    return true;
  }

  public static boolean sendTelephone(String[] responseStr, String verifyCode, String telephone,int codeType){
    String codeName = VerifyCodeEnum.getCodeName(codeType);
    // 发送短信 一共尝试3次
    boolean sendMsgFlag = false;
    for (int i = 0; i < 3; i++) {
      sendMsgFlag = HBSmsUtil.sendMessage(telephone, "【BITRR】您正在进行" + codeName +"，验证码是：" +
          verifyCode);
      if (sendMsgFlag) {
        responseStr[0] = "验证码已发送到您的手机" + telephone.substring(0,3) + "***" + telephone.substring(7) + "，5分钟内有效。";
        log.info("发送手机短信验证码成功 account ===> {} code {}", telephone, verifyCode);
        break;
      }
    }
    return sendMsgFlag;
  }
}
