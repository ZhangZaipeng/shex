package com.exchange.common.exception;

import com.exchange.common.response.ResponseCode;

public class ResultErrexchangeception exchangetends Runtimeexchangeception {

  private int errCode;
  private String errMsg;

  public ResultErrexchangeception(ResponseCode errCode, String errMsg) {
    super(errMsg);
    this.errCode = errCode.getCode();
  }

  public ResultErrexchangeception(ResponseCode responseCode) {
    super(responseCode.getMsg());
    this.errCode = responseCode.getCode();
  }

  public ResultErrexchangeception(int errCode, String errMsg) {
    super(errMsg);
    this.errCode = errCode;
  }

  public ResultErrexchangeception(String errMsg) {
    super(errMsg);
    this.errCode = ResponseCode.FAIL.getCode();
  }

  public int getErrCode() {
    return errCode;
  }
}
