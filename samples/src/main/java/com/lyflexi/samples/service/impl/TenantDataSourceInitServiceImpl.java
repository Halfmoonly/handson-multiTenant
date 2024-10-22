package com.lyflexi.samples.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lyflexi.multitenant.model.TenantDataSource;
import com.lyflexi.multitenant.service.TenantDataSourceInitService;
import com.lyflexi.samples.dao.TenantDataSourceMapper;
import com.lyflexi.samples.model.po.TenantDataSourcePo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.ArrayList;
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

    /**
     * admin租户：admin
     * 测试租户1：1844926438923849731
     * 测试租户2：1844926438923849732
     * @return
     */
    @Override
    public List<TenantDataSource> listDataSource() {
//        QueryWrapper<TenantDataSourcePo> wrapper = new QueryWrapper<>();
//        List<TenantDataSourcePo> tenantDataSourcePos = tenantDataSourceMapper.selectList(wrapper);
//        List<TenantDataSource> dataSourceList = new ArrayList<>();
//        for (int i = 0; i < tenantDataSourcePos.size(); i++) {
//            TenantDataSource tenantDataSource = new TenantDataSource();
//            BeanUtils.copyProperties(tenantDataSourcePos.get(i),tenantDataSource);
//            dataSourceList.add(tenantDataSource);
//        }
//        return dataSourceList;
        ArrayList<TenantDataSource> rs = new ArrayList<>();
        //tenant1
        TenantDataSource tenant1 = new TenantDataSource();
        tenant1.setName("tenant1");
        tenant1.setUrl("jdbc:mysql://10.43.138.190:3306/tenant1?useSSL=false");
        tenant1.setUserName("root");
        tenant1.setPassword("root");
        tenant1.setStatus(1);
        tenant1.setTenantId("1844926438923849731");
        tenant1.setDriver("mysql");
        //tenant2
        TenantDataSource tenant2 = new TenantDataSource();
        tenant2.setName("tenant2");
        tenant2.setUrl("jdbc:mysql://10.43.138.190:3306/tenant2?useSSL=false");
        tenant2.setUserName("root");
        tenant2.setPassword("root");
        tenant2.setStatus(1);
        tenant2.setTenantId("1844926438923849732");
        tenant2.setDriver("mysql");
        rs.add(tenant1);
        rs.add(tenant2);
        return rs;
    }

}
