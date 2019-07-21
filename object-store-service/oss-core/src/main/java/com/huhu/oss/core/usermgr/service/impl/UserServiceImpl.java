package com.huhu.oss.core.usermgr.service.impl;

import com.huhu.oss.core.authmgr.model.TokenInfo;
import com.huhu.oss.core.authmgr.service.IAuthService;
import com.huhu.oss.core.usermgr.CoreUtil;
import com.huhu.oss.core.usermgr.dao.UserInfoMapper;
import com.huhu.oss.core.usermgr.model.UserInfo;
import com.huhu.oss.core.usermgr.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

import java.util.Date;

/**
 * 加入@Transactional 事务注解，方法要么全部执行成功，要么全部执行失败
 *
 * @Author: huhu
 * @Date: 2019-06-30 18:39
 */
@Transactional
@Service("userServiceImpl")
public class UserServiceImpl implements IUserService {

  private long LONG_REFRESH_TIME = 4670409600000L;

  @Autowired
  UserInfoMapper userInfoMapper;

  @Autowired
  @Qualifier("authServiceImpl")
  IAuthService authService;

  @Override
  public boolean addUser(UserInfo userInfo) {
    userInfoMapper.addUser(userInfo);

    // todo add token
    //  添加用户时，默认需要给用户添加token；由于目前权限管理模块尚未开发，因此该步骤待做
    TokenInfo tokenInfo = new TokenInfo();
    tokenInfo.setToken(userInfo.getUserId());
    tokenInfo.setActive(true);
    tokenInfo.setExpireTime(7);
    tokenInfo.setRefreshTime(new Date());
    tokenInfo.setCreator(CoreUtil.SYSTEM_USER);
    tokenInfo.setCreateTime(new Date());
    authService.addToken(tokenInfo);

    return true;
  }

  @Override
  public boolean updateUserInfo(String userId, String password, String detail) {
    // 需要对用户输入的password是否为null或者空，如果是就不会对password进行更改，否则会对password进行md5加密后进行更改
    userInfoMapper.updateUserInfo(
            userId,
            Strings.isNullOrEmpty(password) ? null : CoreUtil.getMd5Password(password),
            Strings.emptyToNull(detail));
    return true;
  }

  @Override
  public boolean deleteUser(String userId) {
    userInfoMapper.deleteUser(userId);

    // todo delete token and auth
    //  目前权限模块尚未开发，因此该步骤待做
    authService.deleteToken(userId);
    authService.deleteAuthByToken(userId);

    return true;
  }

  @Override
  public UserInfo getUserInfo(String userId) {
    return userInfoMapper.getUserInfo(userId);
  }

  @Override
  public UserInfo checkPassword(String userName, String password) {
    return userInfoMapper.checkPassword(userName, password);
  }

  @Override
  public UserInfo getUserInfoByName(String userName) {
    return userInfoMapper.getUserInfoByName(userName);
  }
}
