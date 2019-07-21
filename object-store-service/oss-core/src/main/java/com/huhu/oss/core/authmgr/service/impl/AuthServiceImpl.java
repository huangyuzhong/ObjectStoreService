package com.huhu.oss.core.authmgr.service.impl;

import com.huhu.oss.core.authmgr.dao.ServiceAuthMapper;
import com.huhu.oss.core.authmgr.dao.TokenInfoMapper;
import com.huhu.oss.core.authmgr.model.ServiceAuth;
import com.huhu.oss.core.authmgr.model.TokenInfo;
import com.huhu.oss.core.authmgr.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author: huhu
 * @Date: 2019-07-13 14:36
 */
@Service("authServiceImpl")
@Transactional
public class AuthServiceImpl implements IAuthService {

  @Autowired
  TokenInfoMapper tokenInfoMapper;

  @Autowired
  ServiceAuthMapper serviceAuthMapper;

  // 权限相关
  @Override
  public boolean addAuth(ServiceAuth serviceAuth) {
    serviceAuthMapper.addAuth(serviceAuth);
    return true;
  }

  @Override
  public boolean deleteAuth(String bucketName, String token) {
    serviceAuthMapper.deleteAuth(bucketName, token);
    return true;
  }

  @Override
  public boolean deleteAuthByToken(String token) {
    serviceAuthMapper.deleteAuthByToken(token);
    return true;
  }

  @Override
  public boolean deleteAuthByBucket(String bucket) {
    serviceAuthMapper.deleteAuthByBucket(bucket);
    return true;
  }

  @Override
  public ServiceAuth getServiceAuth(String bucket, String token) {
    return serviceAuthMapper.getAuth(bucket, token);
  }


  // token相关
  @Override
  public boolean addToken(TokenInfo tokenInfo) {
    tokenInfoMapper.addToken(tokenInfo);
    return true;
  }

  @Override
  public boolean deleteToken(String token) {
    tokenInfoMapper.deleteToken(token);
    // todo delete auth 删除token时同时删除对应的权限信息
    serviceAuthMapper.deleteAuthByToken(token);
    return true;
  }

  @Override
  public boolean updateToken(String token, int expireTime, boolean isActive) {
    tokenInfoMapper.updateToken(token, expireTime, isActive ? 1 : 0);
    return true;
  }

  @Override
  public boolean refreshToken(String token) {
    tokenInfoMapper.refreshToken(token, new Date());
    return true;
  }

  /**
   * 验证token是否可用
   *
   * @param token
   * @return
   */
  @Override
  public boolean checkToken(String token) {
    TokenInfo tokenInfo = tokenInfoMapper.getTokenInfo(token);
    if (tokenInfo == null) return false;

    // 校验token是否过期
    if (tokenInfo.isActive()) {
      Date nowDate = new Date();
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(tokenInfo.getRefreshTime());
      calendar.add(Calendar.DATE, tokenInfo.getExpireTime());
      // 刷新时间+设定的过期时间 与 当前的时间进行判断
      return nowDate.before(calendar.getTime());
    }

    return false;
  }

  @Override
  public TokenInfo getTokenInfo(String token) {
    return tokenInfoMapper.getTokenInfo(token);
  }

  @Override
  public List<TokenInfo> getTokenInfoList(String creator) {
    return tokenInfoMapper.getTokenInfoList(creator);
  }
}
