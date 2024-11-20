package com.lyflexi.multitenant.interceptor;

import com.lyflexi.multitenant.datasource.DynamicDataSourceHolder;
import com.lyflexi.multitenant.utils.TenantContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Description:
 * @Author: lyflexi
 * @project: handson-mulit-tenant
 * @Date: 2024/10/21 15:55
 */
public class TenantHeaderInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(TenantHeaderInterceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String tenantId = request.getHeader("x-tenant-id");
        if (StringUtils.isNotBlank(tenantId)) {
            TenantContext.getInstance().setCurrentTenant(tenantId);
        }

        String tenantMode = request.getHeader("tenant_mode");
        if (StringUtils.isNotBlank(tenantMode)) {
            TenantContext.getInstance().setTenantMode(tenantMode);
        }

        return true;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        TenantContext.getInstance().clear();
        DynamicDataSourceHolder.clearDataSourceKey();
    }
}
