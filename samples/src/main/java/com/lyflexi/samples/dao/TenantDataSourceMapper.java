package com.lyflexi.samples.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyflexi.multitenant.model.TenantDataSource;
import com.lyflexi.samples.model.po.TenantDataSourcePo;
import com.lyflexi.samples.model.po.UserPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:
 * @Author: lyflexi
 * @project: handson-mulit-tenant
 * @Date: 2024/10/21 19:41
 */
@Mapper
public interface TenantDataSourceMapper extends BaseMapper<TenantDataSourcePo> {
}
