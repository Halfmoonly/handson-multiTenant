# handson-mulit-tenant

测试完成

```shell
2024-10-22T08:53:08.158+08:00  INFO 49428 --- [nio-8080-exec-1] c.l.multitenant.utils.TenantContext      : 数据源修改======》1844926438923849731
Creating a new SqlSession
SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@726704a3] was not registered for synchronization because synchronization is not active
2024-10-22T08:53:09.882+08:00  INFO 49428 --- [nio-8080-exec-1] c.l.m.d.DynamicDataSourceHolder          : >>>>>>>>>>start init data source>>>>>>>>>>
2024-10-22T08:53:10.492+08:00  INFO 49428 --- [nio-8080-exec-1] com.alibaba.druid.pool.DruidDataSource   : {dataSource-4,tenant1} inited
JDBC Connection [com.alibaba.druid.pool.DruidStatementConnection@12bda450] will not be managed by Spring
==>  Preparing: SELECT id,name FROM user WHERE id=?
==> Parameters: 1(Long)
<==    Columns: id, name
<==        Row: 1, tenant1
<==      Total: 1
Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@726704a3]
```