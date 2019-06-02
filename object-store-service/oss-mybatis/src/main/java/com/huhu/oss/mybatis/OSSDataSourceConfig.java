package com.huhu.oss.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

/**
 * 数据库连接相关Config类
 *
 * @Author: huhu
 * @Date: 2019-06-01 18:11
 */
@Configuration
@MapperScan(basePackages = OSSDataSourceConfig.PACKAGE,
    sqlSessionFactoryRef = "OssSqlSessionFactory")
public class OSSDataSourceConfig {

    // 标识需要扫描的package
    static final String PACKAGE = "com.huhu.oss.**";

    /**
     * 根据配置去获取DataSource
     *
     * @return
     * @throws IOException
     */
    @Bean(name = "OssDataSource")
    @Primary
    public DataSource ossDataSource() throws IOException {
        // 1.获取DataSource相关信息，从application.properties中去获取
        ResourceLoader loader = new DefaultResourceLoader();
        InputStream inputStream = loader.getResource("classpath:application.properties")
                .getInputStream();
        Properties properties = new Properties();
        properties.load(inputStream);

        // 进行过滤，留下与数据库相关的配置，保存到dsProperties中
        Set<Object> keys = properties.keySet();
        Properties dsProperties = new Properties();
        for (Object key : keys) {
            dsProperties.put(key.toString().replace("datasource.",""), properties.get(key));
        }

        // 2.通过HikariDataSourceFactory去生成一个datasource
        HikariDataSourceFactory factory = new HikariDataSourceFactory();
        factory.setProperties(dsProperties);
        inputStream.close();

        return factory.getDataSource();
    }

    // 传入的参数为ossDataSource()方法得到的DataSource，因此使用@Qualifier注解指定后进行引入
    @Bean(name = "OssSqlSessionFactory")
    @Primary
    public SqlSessionFactory ossSqlSessionFactory (
            @Qualifier("OssDataSource") DataSource ossDataSource) throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(ossDataSource);
        // 1.读取mybatis相关的配置，并设置到SqlSessionFactoryBean中去
        ResourceLoader loader = new DefaultResourceLoader();
        sqlSessionFactoryBean.setConfigLocation(loader.getResource("classpath:mybatis-config.xml"));

        // 2.获取sqlsessionfactory实例
        sqlSessionFactoryBean.setSqlSessionFactoryBuilder(new SqlSessionFactoryBuilder());

        return sqlSessionFactoryBean.getObject();
    }
}
