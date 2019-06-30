package com.huhu.oss.core.usermgr;


import com.huhu.oss.core.OssException;

/**
 * 用户模块管理异常
 * 在OssException的基础上添加了code，code会从ErrorCodes中所设定的值当中进行选择并传入
 *
 * @Author: huhu
 * @Date: 2019-06-30 17:02
 */
public class OssUsermgrException extends OssException {

  private int code;
  private String message;

  public OssUsermgrException(int code, String message, Throwable cause) {
    super(message, cause);
    this.code = code;
    this.message = message;
  }

  public OssUsermgrException(int code, String message) {
    super(message, null);
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public int errorCode() {
    return this.code;
  }
}
