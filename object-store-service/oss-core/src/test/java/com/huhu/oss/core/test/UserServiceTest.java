package com.huhu.oss.core.test;

import com.huhu.oss.core.usermgr.IUserService;
import com.huhu.oss.core.usermgr.model.SystemRole;
import com.huhu.oss.core.usermgr.model.UserInfo;
import com.huhu.oss.mybatis.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.junit.Assert.assertTrue;

/**
 * @Author: huhu
 * @Date: 2019-06-30 18:56
 */
public class UserServiceTest extends BaseTest {

  @Autowired
  @Qualifier("userServiceImpl")
  IUserService userService;

  @Test
  public void addUser() {
    UserInfo userInfo = new UserInfo("huhu", "123456", SystemRole.ADMIN, "no desc");
    assertTrue(userService.addUser(userInfo));
  }

  @Test
  public void getUser() {
    UserInfo userInfo = userService.getUserInfoByName("huhu");
    System.out.println(userInfo.getUserId() + "|" + userInfo.getUserName() + "|" + userInfo.getPassword());
  }

  @Test
  public void deleteUser() {
    UserInfo userInfo = userService.getUserInfoByName("huhu");
    assertTrue(userService.deleteUser(userInfo.getUserId()));
  }
}
