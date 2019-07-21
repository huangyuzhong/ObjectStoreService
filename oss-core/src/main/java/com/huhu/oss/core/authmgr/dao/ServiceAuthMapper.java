package com.huhu.oss.core.authmgr.dao;

import com.huhu.oss.core.authmgr.model.ServiceAuth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;

/**
 * @Author: huhu
 * @Date: 2019-07-13 13:27
 */
@Mapper
public interface ServiceAuthMapper {

  void addAuth(@Param("auth") ServiceAuth serviceAuth);

  void deleteAuth(@Param("bucket") String bucket, @Param("token") String token);

  // 删除用户的时候需要将与用户相关的权限全部删除掉
  void deleteAuthByToken(@Param("token") String token);

  // 删除bucket的时候需要将所有相关的授权信息删除掉
  void deleteAuthByBucket(@Param("bucket") String bucket);

  @ResultMap("ServiceAuthResultMap")
  ServiceAuth getAuth(@Param("bucket") String bucket, @Param("token") String token);
}
