package com.lyflexi.samples.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lyflexi.multitenant.model.TenantDataSource;
import lombok.Data;

/**
 * @Description:
 * @Author: lyflexi
 * @project: handson-mulit-tenant
 * @Date: 2024/10/22 8:20
 */
@Data
@TableName("tenant_data_source")
public class TenantDataSourcePo extends TenantDataSource {
}
