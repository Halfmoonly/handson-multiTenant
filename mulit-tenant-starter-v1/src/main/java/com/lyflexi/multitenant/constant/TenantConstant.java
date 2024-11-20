package com.lyflexi.multitenant.constant;

/**
 * @Description:
 * @Author: lyflexi
 * @project: handson-mulit-tenant
 * @Date: 2024/10/21 15:51
 */
public interface TenantConstant {
    String X_TENANT_ID = "x-tenant-id";
    String TENANT_MODE = "tenant_mode";
    String TENANT_PLATFORM = "admin";
    String TENANT_GENERAL = "tenant";
    String TENANT_DEFAULT = "default";
    String TENANT_ROLES = "tenant_roles";
    String TENANT_LIST = "tenants";
    String MODE_SINGLE = "singleTenant";
    String MODE_MULTI = "multiTenant";
    String ADMIN_ROLE_CODE = "10101";
}