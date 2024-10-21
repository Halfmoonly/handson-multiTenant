package com.lyflexi.multitenant.config;

import com.lyflexi.multitenant.interceptor.SwitchDataSourceInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description:
 * @Author: lyflexi
 * @project: handson-mulit-tenant
 * @Date: 2024/10/21 15:50
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({SwitchDataSourceInterceptor.class})
public @interface EnableDynamicDataSource {
}
