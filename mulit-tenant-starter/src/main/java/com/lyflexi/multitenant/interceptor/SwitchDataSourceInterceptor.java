package com.lyflexi.multitenant.interceptor;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.TableNameParser;
import com.lyflexi.multitenant.datasource.DynamicDataSourceHolder;
import com.lyflexi.multitenant.utils.TenantContext;
import org.aopalliance.intercept.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @Description:
 * @Author: lyflexi
 * @project: handson-mulit-tenant
 * @Date: 2024/10/21 15:54
 */
@Order(1)
@Intercepts({@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class, Integer.class}
), @Signature(
        type = Executor.class,
        method = "queryCursor",
        args = {MappedStatement.class, Object.class, RowBounds.class}
), @Signature(
        type = StatementHandler.class,
        method = "getBoundSql",
        args = {}
), @Signature(
        type = Executor.class,
        method = "update",
        args = {MappedStatement.class, Object.class}
), @Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
), @Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}
)})
public class SwitchDataSourceInterceptor implements Interceptor {
    private static final Logger log = LoggerFactory.getLogger(SwitchDataSourceInterceptor.class);
    private static final List<String> WHITE_LIST = Arrays.asList("tenants", "tenant_users", "tenant_datasource", "config");
    @Value("${tenant.model:singleTenant}")
    private String tenantMode;

    public Object intercept(Invocation invocation) throws Throwable {
        String currentTenant = TenantContext.getInstance().getCurrentTenant();
        if (StrUtil.equals(this.tenantMode, "singleTenant")) {
            DynamicDataSourceHolder.getInstance().setDataSourceKey("default");
            return invocation.proceed();
        } else if (StrUtil.equals(this.tenantMode, "multiTenant") && StrUtil.isEmpty(currentTenant)) {
            DynamicDataSourceHolder.getInstance().setDataSourceKey("admin");
            return invocation.proceed();
        } else if (StringUtils.equals("admin", currentTenant)) {
            DynamicDataSourceHolder.getInstance().setDataSourceKey("admin");
            return invocation.proceed();
        } else {
            String methodName = invocation.getMethod().getName();
            if (StringUtils.equals("update", methodName) || StringUtils.equals("query", methodName)) {
                Object[] args = invocation.getArgs();
                if (args.length < 2 || !(args[0] instanceof MappedStatement)) {
                    return invocation.proceed();
                }

                MappedStatement mappedStatement = (MappedStatement)args[0];
                SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
                if (sqlCommandType != SqlCommandType.INSERT && sqlCommandType != SqlCommandType.DELETE && sqlCommandType != SqlCommandType.UPDATE && sqlCommandType != SqlCommandType.SELECT) {
                    return invocation.proceed();
                }

                BoundSql boundSql = mappedStatement.getBoundSql(args[1]);
                String originalSql = boundSql.getSql().replaceAll("[\\s]+", " ");
                log.debug("intercept sql:{}", originalSql);
                Collection<String> tableNameList = (new TableNameParser(originalSql)).tables();
                Stream var10000 = tableNameList.stream();
                List var10001 = WHITE_LIST;
                var10001.getClass();
                if (var10000.anyMatch(var10001::contains)) {
                    DynamicDataSourceHolder.getInstance().setDataSourceKey("admin");
                    return invocation.proceed();
                }
            }

            if (!DynamicDataSourceHolder.getInstance().containDataSourceKey(currentTenant)) {
                Map<Object, Object> dataSourceMap = DynamicDataSourceHolder.getInstance().queryAllDataSource();
                Map<String, DataSource> dataSources = TenantContext.getInstance().getDataSources();
                if (CollectionUtil.isNotEmpty(dataSources)) {
                    dataSourceMap.putAll(dataSources);
                }

                if (!dataSourceMap.containsKey(currentTenant)) {
                    throw new IllegalStateException("not exist data source for tenant: " + currentTenant);
                }

                DynamicDataSourceHolder.getInstance().initDataSource(dataSourceMap);
            }

            DynamicDataSourceHolder.getInstance().setDataSourceKey(currentTenant);
            return invocation.proceed();
        }
    }
}
