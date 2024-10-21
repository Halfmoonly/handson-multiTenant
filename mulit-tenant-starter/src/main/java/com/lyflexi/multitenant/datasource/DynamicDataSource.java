package com.lyflexi.multitenant.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @Description:
 * @Author: lyflexi
 * @project: handson-mulit-tenant
 * @Date: 2024/10/21 15:52
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    public DynamicDataSource(DataSource defaultDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getInstance().getDataSourceKey();
    }

    public void setDataSources(Map<Object, Object> dataSources) {
        super.setTargetDataSources(dataSources);
        super.afterPropertiesSet();
    }
}
