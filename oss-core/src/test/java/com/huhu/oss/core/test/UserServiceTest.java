package com.huhu.oss.core.test;

import com.huhu.oss.core.usermgr.CoreUtil;
import com.huhu.oss.core.usermgr.service.IUserService;
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
  public void updateUserInfo() {
    userService.updateUserInfo("7c827c148d1b48c4a70c59dc26c3c4c1","123456","first update");
  }

  @Test
  public void getUserInfoByName() {
    UserInfo userInfo = userService.getUserInfoByName("huhu");
    System.out.println(userInfo.getUserId() + "|" + userInfo.getUserName() + "|" + userInfo.getPassword());
  }

  @Test
  public void getUserInfo() {
    UserInfo userInfo = userService.getUserInfoByName("huhu");
    System.out.println(userService.getUserInfo(userInfo.getUserId()));
  }

  @Test
  public void checkPassword() {
    System.out.println(userService.checkPassword("huhu", CoreUtil.getMd5Password("123456")));
  }

  @Test
  public void deleteUser() {
    UserInfo userInfo = userService.getUserInfoByName("huhu");
    assertTrue(userService.deleteUser(userInfo.getUserId()));
  }
}
