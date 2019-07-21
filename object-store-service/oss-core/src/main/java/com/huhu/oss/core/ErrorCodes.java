package com.huhu.oss.core;

/**
 * ErrorCode，在使用REST和SDK接口时，方便去定位错误
 *
 * @Author: huhu
 * @Date: 2019-06-30 16:42
 */
public interface ErrorCodes {

  public static final int ERROR_PERMISSION_DENIED = 403;
  public static final int ERROR_FILE_NOT_FOUND = 404;
  public static final int ERROR_HBASE = 500;
  public static final int ERROR_HDFS = 501;
}
