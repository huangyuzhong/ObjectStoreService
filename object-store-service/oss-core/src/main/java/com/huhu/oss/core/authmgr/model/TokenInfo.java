package com.huhu.oss.core.authmgr.model;

import com.huhu.oss.core.usermgr.CoreUtil;

import java.util.Date;

/**
 * Token表
 *
 * @Author: huhu
 * @Date: 2019-07-13 13:19
 */
public class TokenInfo {
  private String token;
  private int expireTime; //过期时间
  private Date refreshTime; //刷新时间
  private Date createTime; //创建时间
  private boolean isActive; //可用状态的标志
  private String creator;

  public TokenInfo() {}

  public TokenInfo(String creator) {
    this.token = CoreUtil.getUUID();
    this.expireTime = 7;
    this.creator = creator;
    Date now = new Date();
    this.refreshTime = now;
    this.createTime = now;
    this.isActive = true;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public int getExpireTime() {
    return expireTime;
  }

  public void setExpireTime(int expireTime) {
    this.expireTime = expireTime;
  }

  public Date getRefreshTime() {
    return refreshTime;
  }

  public void setRefreshTime(Date refreshTime) {
    this.refreshTime = refreshTime;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }
}
