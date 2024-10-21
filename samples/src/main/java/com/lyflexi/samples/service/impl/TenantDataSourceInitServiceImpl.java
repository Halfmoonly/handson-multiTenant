package com.lyflexi.samples.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lyflexi.multitenant.model.TenantDataSource;
import com.lyflexi.multitenant.service.TenantDataSourceInitService;
import com.lyflexi.samples.dao.TenantDataSourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.Collections;
import java.util.List;

/**
 * @Description:
 * @Author: lyflexi
 * @project: handson-mulit-tenant
 * @Date: 2024/10/21 19:40
 */
@Service
public class TenantDataSourceInitServiceImpl implements TenantDataSourceInitService {
    @Autowired
    TenantDataSourceMapper tenantDataSourceMapper;
    @Override
    public List<TenantDataSource> listDataSource() {
        QueryWrapper<TenantDataSource> wrapper = new QueryWrapper<>();
        return tenantDataSourceMapper.selectList(wrapper);
    }

}
