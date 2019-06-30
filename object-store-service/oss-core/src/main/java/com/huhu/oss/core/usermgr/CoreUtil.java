package com.huhu.oss.core.usermgr;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * 用于生成uuid及对password的md5加密
 *
 * @Author: huhu
 * @Date: 2019-06-30 17:15
 */
public class CoreUtil {

  public final static String SYSTEM_USER = "SuperAdmin";

  public static String getMd5Password(String str) {
    String reStr = null;
    try {
      MessageDigest md5 = MessageDigest.getInstance("MD5");
      byte[] bytes = md5.digest(str.getBytes());
      StringBuffer stringBuffer = new StringBuffer();
      for (byte b : bytes) {
        int bt = b & 0xff;
        if (bt < 16) {
          stringBuffer.append(0);
        }
        stringBuffer.append(Integer.toHexString(bt));
      }
      reStr = stringBuffer.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return reStr;
  }

  public static String getUUID() {
    return UUID.randomUUID().toString().replace("-", "");
  }

//    public static void main(String[] args) {
//        System.out.println(getUUIDStr());
//        System.out.println(getMd5Password("hipac"));
//    }
}
