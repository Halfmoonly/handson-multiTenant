package com.lyflexi.multitenant.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.lyflexi.multitenant.datasource.DynamicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Description:
 * @Author: lyflexi
 * @project: handson-mulit-tenant
 * @Date: 2024/10/21 15:48
 */
@Configuration
public class DynamicDataSourceConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.druid.first")
    public DataSource defaultDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource mspDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.platform")
    public DataSource platformDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource defaultDataSource) {
        return new DynamicDataSource(defaultDataSource, this.getBuildInDataSources());
    }

    public Map<Object, Object> getBuildInDataSources() {
        Map<Object, Object> buildInDataSources = new HashMap();
        buildInDataSources.put("default", Optional.of(this.defaultDataSource()).orElseGet(() -> {
            return this.mspDataSource();
        }));
        buildInDataSources.put("admin", this.platformDataSource());
        return buildInDataSources;
    }
}
