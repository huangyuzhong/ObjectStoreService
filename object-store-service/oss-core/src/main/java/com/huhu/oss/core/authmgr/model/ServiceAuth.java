package com.huhu.oss.core.authmgr.model;

import java.util.Date;

/**
 * 权限表
 *
 * @Author: huhu
 * @Date: 2019-07-13 13:24
 */
public class ServiceAuth {
  private String bucketName;
  private String targetToken;
  private Date authTime;

  public String getBucketName() {
    return bucketName;
  }

  public void setBucketName(String bucketName) {
    this.bucketName = bucketName;
  }

  public String getTargetToken() {
    return targetToken;
  }

  public void setTargetToken(String targetToken) {
    this.targetToken = targetToken;
  }

  public Date getAuthTime() {
    return authTime;
  }

  public void setAuthTime(Date authTime) {
    this.authTime = authTime;
  }
}
