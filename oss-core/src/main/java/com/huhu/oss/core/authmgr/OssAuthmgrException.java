package com.huhu.oss.core.authmgr;

import com.huhu.oss.core.OssException;

/**
 * 权限管理模块异常
 * 与OssUsermgrException类内容一致
 *
 * @Author: huhu
 * @Date: 2019-07-13 13:17
 */
public class OssAuthmgrException extends OssException {

  private int code;
  private String message;

  public OssAuthmgrException(int code, String message, Throwable cause) {
    super(message, cause);
    this.code = code;
    this.message = message;
  }

  public OssAuthmgrException(int code, String message) {
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
