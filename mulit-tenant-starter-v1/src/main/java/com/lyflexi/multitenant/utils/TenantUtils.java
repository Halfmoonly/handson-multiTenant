package com.lyflexi.multitenant.utils;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * @Description:
 * @Author: lyflexi
 * @project: handson-mulit-tenant
 * @Date: 2024/10/21 16:00
 */
public class TenantUtils {
    public static String getTenantKey(String key, String separator) {
        if (StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException("invalid tenant key");
        } else {
            String currentTenant = TenantContext.getInstance().getCurrentTenant();
            return !StrUtil.isEmpty(currentTenant) && !StrUtil.equals(currentTenant, "default") && !StrUtil.equals(currentTenant, "admin") ? currentTenant + separator + key : key;
        }
    }
}

