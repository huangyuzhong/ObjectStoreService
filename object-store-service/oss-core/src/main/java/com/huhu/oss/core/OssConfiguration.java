package com.huhu.oss.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * 将OSS所有的properties文件都加载到一起，从而去获取所有配置文件中的所有的属性
 * 将该类设置为一个单例类
 *
 * @Author: huhu
 * @Date: 2019-06-30 16:45
 */
public class OssConfiguration {

  private static OssConfiguration configuration;
  private static Properties properties;

  // 去获取classpath下所有的properties，然后全部放到Properties类的实例中
  static {
    PathMatchingResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
    configuration = new OssConfiguration();
    try {
      configuration.properties = new Properties();
      Resource[] resources = resourceLoader.getResources("classpath:*.properties");
      // 依次读取所有的properties
      for (Resource resource : resources) {
        Properties props = new Properties();
        InputStream input = resource.getInputStream();
        props.load(input);
        input.close();

        // 将读取到的properties添加到OssConfiguration中的properties来
        configuration.properties.putAll(props);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private OssConfiguration() {
  }

  public static OssConfiguration getConfiguration() {
    return configuration;
  }

  /**
   * 通过key获取对应属性值
   *
   * @param key
   * @return
   */
  public String getString(String key) {
    return properties.getProperty(key);
  }

  public int getInt(String key) {
    return Integer.parseInt(properties.getProperty(key));
  }

  public boolean getBoolean(String key) {
    return Boolean.parseBoolean(properties.getProperty(key));
  }

  public long getLong(String key) {
    return Long.parseLong(properties.getProperty(key));
  }

}
