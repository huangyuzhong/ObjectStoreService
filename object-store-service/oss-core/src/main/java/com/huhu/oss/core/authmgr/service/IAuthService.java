package com.huhu.oss.core.authmgr.service;

import com.huhu.oss.core.authmgr.model.ServiceAuth;
import com.huhu.oss.core.authmgr.model.TokenInfo;

import java.util.List;

/**
 * @Author: huhu
 * @Date: 2019-07-13 14:27
 */
public interface IAuthService {

  // 权限相关
  public boolean addAuth(ServiceAuth serviceAuth);

  public boolean deleteAuth(String bucketName, String token);

  public boolean deleteAuthByToken(String token);

  public boolean deleteAuthByBucket(String bucket);

  public ServiceAuth getServiceAuth(String bucket, String token);

  // token相关
  public boolean addToken(TokenInfo tokenInfo);

  public boolean deleteToken(String token);

  public boolean updateToken(String token, int expireTime, boolean isActive);

  public boolean refreshToken(String token);

  public boolean checkToken(String token);

  public TokenInfo getTokenInfo(String token);

  public List<TokenInfo> getTokenInfoList(String creator);
}
