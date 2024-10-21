package com.lyflexi.multitenant.service;

import com.lyflexi.multitenant.model.TenantDataSource;

import java.util.Collections;
import java.util.List;

/**
 * @Description:
 * @Author: lyflexi
 * @project: handson-mulit-tenant
 * @Date: 2024/10/21 15:59
 */
public interface TenantDataSourceInitService {
    default List<TenantDataSource> listDataSource() {
        return Collections.emptyList();
    }
}
