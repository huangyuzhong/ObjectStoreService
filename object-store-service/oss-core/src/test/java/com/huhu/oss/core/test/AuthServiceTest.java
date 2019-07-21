package com.huhu.oss.core.test;

import com.huhu.oss.core.authmgr.model.ServiceAuth;
import com.huhu.oss.core.authmgr.model.TokenInfo;
import com.huhu.oss.core.authmgr.service.IAuthService;
import com.huhu.oss.mybatis.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @Author: huhu
 * @Date: 2019-07-13 15:04
 */
public class AuthServiceTest extends BaseTest {

  @Autowired
  @Qualifier("authServiceImpl")
  IAuthService authService;

  @Test
  public void addToken() {
    TokenInfo tokenInfo = new TokenInfo("huhu");
    authService.addToken(tokenInfo);
  }

  @Test
  public void updateToken() {
    assertTrue(authService.updateToken("dfa8d4b03a004d8bbe6bb4831c94ba7f", 7, true));
  }

  @Test
  public void refreshToken() {
    assertTrue(authService.refreshToken("7c827c148d1b48c4a70c59dc26c3c4c1"));
  }

  @Test
  public void deleteToken() {
    assertTrue(authService.deleteToken("7c827c148d1b48c4a70c59dc26c3c4c1"));
  }

  @Test
  public void getTokenInfo() {
    System.out.println(authService.getTokenInfo("319dfae2b7a2405fbac0350dcdb08f45"));
  }

  @Test
  public void getTokenInfoList() {
    List<TokenInfo> tokenInfos = authService.getTokenInfoList("huhu");
    tokenInfos.forEach(tokenInfo -> {
      System.out.println(tokenInfo.getToken());
    });
  }

  @Test
  public void addServiceAuth() {
    ServiceAuth serviceAuth = new ServiceAuth();
    serviceAuth.setBucketName("bucket1");
    serviceAuth.setTargetToken("07b8ea215a2c4d3cb3d52b19c0c352d8");
    serviceAuth.setAuthTime(new Date());
    authService.addAuth(serviceAuth);
  }

  @Test
  public void getServiceAuth() {
    ServiceAuth serviceAuth = authService.getServiceAuth("bucket1", "07b8ea215a2c4d3cb3d52b19c0c352d8");
    System.out.println(serviceAuth.getBucketName() + "|" + serviceAuth.getTargetToken());
  }

  @Test
  public void deleteAuth() {
//    assertTrue(authService.deleteAuth("bucket1", "07b8ea215a2c4d3cb3d52b19c0c352d8"));
//    assertTrue(authService.deleteAuthByBucket("bucket1"));
    assertTrue(authService.deleteAuthByToken("07b8ea215a2c4d3cb3d52b19c0c352d8"));
  }
}
