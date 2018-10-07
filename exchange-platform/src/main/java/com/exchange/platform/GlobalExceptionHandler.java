package com.exchange.platform;

import com.exchange.common.exception.ResultErrException;
import com.exchange.common.response.ResponseCode;
import com.exchange.common.response.ResponseModel;
import com.exchange.platform.login.AuthenticationException;
import com.exchange.platform.login.LoginErrException;
import com.exchange.platform.springmvc.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class GlobalExceptionHandler implements HandlerExceptionResolver {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @Override
  public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) {
    ResponseModel model = ResponseModel.error(ResponseCode.API_99999).setMsg("网络不给力呀,请稍后重试!");
    if (ex instanceof ResultErrException) {

      model = ResponseModel.getModel(ResponseCode.FAIL);

      model.setMsg(ex.getMessage());

    } else if (ex instanceof LoginErrException) {

      model = ResponseModel.getModel(ResponseCode.USER_10000);

      model.setMsg(ResponseCode.USER_10000.getMsg());

    } else if (ex instanceof AuthenticationException) {

      model = ResponseModel.getModel(ResponseCode.FAIL);

      model.setMsg(ex.getMessage());

    } else {

      ex.printStackTrace();

    }

    return new ModelAndView(new JsonView(model));
  }
}
