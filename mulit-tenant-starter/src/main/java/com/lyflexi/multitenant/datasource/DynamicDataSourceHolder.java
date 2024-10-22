package com.lyflexi.multitenant.datasource;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.lyflexi.multitenant.config.DynamicDataSourceConfig;
import com.lyflexi.multitenant.enums.DBDriverEnum;
import com.lyflexi.multitenant.model.TenantDataSource;
import com.lyflexi.multitenant.service.TenantDataSourceInitService;
import com.lyflexi.multitenant.utils.TenantContext;
import com.lyflexi.multitenant.utils.TenantSpringContextUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.util.*;

/**
 * @Description:
 * @Author: lyflexi
 * @project: handson-mulit-tenant
 * @Date: 2024/10/21 15:53
 */
public class DynamicDataSourceHolder {
    private static final Logger log = LoggerFactory.getLogger(DynamicDataSourceHolder.class);
    private static final ThreadLocal<String> contextHolder = new ThreadLocal();
    public static List<Object> dataSourceKeys = new ArrayList();
    private static volatile DynamicDataSourceHolder instance;

    private DynamicDataSourceHolder() {
    }

    public static DynamicDataSourceHolder getInstance() {
        if (Objects.isNull(instance)) {
            Class var0 = DynamicDataSourceHolder.class;
            synchronized(DynamicDataSourceHolder.class) {
                if (Objects.isNull(instance)) {
                    instance = new DynamicDataSourceHolder();
                }
            }
        }

        return instance;
    }

    public void setDataSourceKey(String dataSourceKey) {
        if (StringUtils.isNotBlank(dataSourceKey)) {
            contextHolder.set(dataSourceKey);
        }

    }

    public String getDataSourceKey() {
        String dataSourceKey = (String)contextHolder.get();
        if (StringUtils.isNotBlank(dataSourceKey)) {
            return dataSourceKey;
        } else {
            String currentTenant = TenantContext.getInstance().getCurrentTenant();
            return StringUtils.isNotBlank(currentTenant) ? currentTenant : "default";
        }
    }

    public static void clearDataSourceKey() {
        contextHolder.remove();
    }

    public Map<Object, Object> queryAllDataSource() {
        Map<Object, Object> dataSourceMap = new HashMap();
        List<TenantDataSource> tenantDataSourceList = ((TenantDataSourceInitService) TenantSpringContextUtils.getBean(TenantDataSourceInitService.class)).listDataSource();
        Iterator var3 = tenantDataSourceList.iterator();

        while(var3.hasNext()) {
            TenantDataSource tenantDataSource = (TenantDataSource)var3.next();
            DataSource dataSource = this.createDataSource(tenantDataSource);
            dataSourceMap.put(tenantDataSource.getTenantId(), dataSource);
        }

        DynamicDataSourceConfig dynamicDataSourceConfig = (DynamicDataSourceConfig)TenantSpringContextUtils.getBean(DynamicDataSourceConfig.class);
        dataSourceMap.putAll(dynamicDataSourceConfig.getBuildInDataSources());
        return dataSourceMap;
    }

    public synchronized void initDataSource(Map<Object, Object> targetDataSources) {
        log.info(">>>>>>>>>>start init data source>>>>>>>>>>");
        ((DynamicDataSource)TenantSpringContextUtils.getBean(DynamicDataSource.class)).setDataSources(targetDataSources);
        dataSourceKeys.addAll(targetDataSources.keySet());
    }

    public synchronized boolean containDataSourceKey(String key) {
        return dataSourceKeys.stream().anyMatch((a) -> {
            return StrUtil.equals((CharSequence)a, key);
        });
    }

    private DataSource createDataSource(TenantDataSource tenantDataSource) {
        if (ObjectUtil.isEmpty(tenantDataSource.getInitPoolSize()) || tenantDataSource.getInitPoolSize() == 0) {
            tenantDataSource.setInitPoolSize(8);
        }

        if (ObjectUtil.isEmpty(tenantDataSource.getMaxPoolSize()) || tenantDataSource.getMaxPoolSize() == 0) {
            tenantDataSource.setMaxPoolSize(8);
        }

        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setName(tenantDataSource.getName());
        druidDataSource.setDriverClassName(DBDriverEnum.getDriver(tenantDataSource.getDriver()));
        //pgsql
//        druidDataSource.setUrl(tenantDataSource.getUrl() + "?currentSchema=" + tenantDataSource.getName());
        //mysql
        druidDataSource.setUrl(tenantDataSource.getUrl());
        druidDataSource.setUsername(tenantDataSource.getUserName());
        druidDataSource.setPassword(tenantDataSource.getPassword());
        druidDataSource.setMaxActive(tenantDataSource.getMaxPoolSize());
        druidDataSource.setInitialSize(tenantDataSource.getInitPoolSize());
        return druidDataSource;
    }

    public void initNewDataSource(TenantDataSource tenantDataSource) {
        DataSource dataSource = this.createDataSource(tenantDataSource);
        Map<String, Object> dataSourceMap = new HashMap();
        dataSourceMap.put(tenantDataSource.getTenantId(), dataSource);
        dataSourceKeys.addAll(dataSourceMap.keySet());
    }
}
