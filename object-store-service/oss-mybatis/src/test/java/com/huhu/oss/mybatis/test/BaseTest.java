package com.huhu.oss.mybatis.test;

import com.huhu.oss.mybatis.OSSDataSourceConfig;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test的基类，后面和Mapper相关的测试只需要继承该基类就行了
 *
 * @Author: huhu
 * @Date: 2019-06-01 23:32
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Import(OSSDataSourceConfig.class)
@PropertySource("classpath:application.properties")
@ComponentScan("com.huhu.*")
@MapperScan("com.huhu.*")
public class BaseTest {
}
