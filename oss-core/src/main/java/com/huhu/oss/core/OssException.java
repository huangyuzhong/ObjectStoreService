package com.huhu.oss.core;

/**
 * OSS异常的基类；后续各个模块的基类都需要从OssException类去继承
 *
 * @Author: huhu
 * @Date: 2019-06-30 16:37
 */
public abstract class OssException extends RuntimeException {

  protected String errorMessage;

  public OssException(String message, Throwable cause) {
    super(cause);
    this.errorMessage = message;
  }

  public abstract int errorCode();

  public String errorMessage() {
    return this.errorMessage;
  }
}
