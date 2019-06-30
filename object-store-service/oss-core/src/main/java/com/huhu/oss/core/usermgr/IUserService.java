package com.huhu.oss.core.usermgr;

import com.huhu.oss.core.usermgr.model.UserInfo;

/**
 * @Author: huhu
 * @Date: 2019-06-30 18:35
 */
public interface IUserService {

  public boolean addUser(UserInfo userInfo);

  public boolean updateUserInfo(String userId, String password, String detail);

  public boolean deleteUser(String userId);

  public UserInfo getUserInfo(String userId);

  public UserInfo checkPassword(String userName, String password);

  public UserInfo getUserInfoByName(String userName);
}
