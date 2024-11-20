package com.lyflexi.multitenant;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: lyflexi
 * @project: handson-mulit-tenant
 * @Date: 2024/10/21 16:07
 */
@Configuration
@EnableConfigurationProperties
@ComponentScan({"com.lyflexi.multitenant"})
public class MultiTenantAutoConfig {
}
