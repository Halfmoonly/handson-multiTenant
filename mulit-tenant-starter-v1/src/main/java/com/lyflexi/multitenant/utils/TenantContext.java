package com.lyflexi.multitenant.utils;

import cn.hutool.core.map.MapUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @Author: lyflexi
 * @project: handson-mulit-tenant
 * @Date: 2024/10/21 16:00
 */
public class TenantContext {
    private static final Logger log = LoggerFactory.getLogger(TenantContext.class);
    private static volatile TenantContext instance;
    private static ThreadLocal<Map<String, String>> threadParams = new ThreadLocal();
    private static Map<String, DataSource> threadDataSource = new ConcurrentHashMap();

    private TenantContext() {
    }

    public static TenantContext getInstance() {
        if (Objects.isNull(instance)) {
            Class var0 = TenantContext.class;
            synchronized(TenantContext.class) {
                if (Objects.isNull(instance)) {
                    instance = new TenantContext();
                }
            }
        }

        return instance;
    }

    public String getCurrentTenant() {
        return (String)((Map)Optional.ofNullable(threadParams.get()).orElse(Maps.newHashMap())).get("x-tenant-id");
    }

    public void setCurrentTenant(String tenant) {
        Map<String, String> threadMap = (Map)threadParams.get();
        if (MapUtil.isEmpty((Map)threadMap)) {
            threadMap = new HashMap();
            threadParams.set(threadMap);
        }

        ((Map)threadMap).put("x-tenant-id", tenant);
        log.info("数据源修改======》" + tenant);
    }

    public boolean isMultiTenant() {
        return StringUtils.equalsIgnoreCase("multiTenant", this.getTenantMode());
    }

    public void setTenantMode(String tenantMode) {
        Map<String, String> threadMap = (Map)threadParams.get();
        if (MapUtil.isEmpty((Map)threadMap)) {
            threadMap = new HashMap();
            threadParams.set(threadMap);
        }

        ((Map)threadMap).put("tenant_mode", tenantMode);
    }

    public String getTenantMode() {
        return (String)((Map)Optional.ofNullable(threadParams.get()).orElse(Maps.newHashMap())).get("tenant_mode");
    }

    public void setDataSources(Map<String, DataSource> dstSources) {
        threadDataSource.putAll(dstSources);
    }

    public Map<String, DataSource> getDataSources() {
        return (Map) Optional.ofNullable(threadDataSource).orElse(Maps.newConcurrentMap());
    }

    public void clear() {
        threadParams.remove();
    }
}
