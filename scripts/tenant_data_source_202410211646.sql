-- `multi-tenant-platform`.tenant_data_source definition

CREATE TABLE `tenant_data_source` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `name` varchar(100) DEFAULT NULL COMMENT '数据源名称',
  `description` varchar(32) DEFAULT NULL COMMENT '数据源描述',
  `url` varchar(255) DEFAULT NULL COMMENT '数据源连接名',
  `user_name` varchar(255) DEFAULT NULL COMMENT '数据库用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '数据库密码',
  `driver` varchar(32) DEFAULT NULL COMMENT '数据库驱动',
  `init_pool_size` int(11) DEFAULT NULL COMMENT '初始化连接池',
  `max_pool_size` int(11) DEFAULT NULL COMMENT '最大连接池',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(100) DEFAULT NULL COMMENT '更新人',
  `status` int(11) DEFAULT NULL COMMENT '数据源状态 -1：未初始化，0：停用，1：启用',
  `tenant_id` varchar(32) DEFAULT NULL COMMENT '租户id',
  `belong` varchar(10) DEFAULT NULL COMMENT '数据源归属',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `multi-tenant-platform`.tenant_data_source (id,name,description,url,user_name,password,driver,init_pool_size,max_pool_size,created_time,updated_time,created_by,updated_by,status,tenant_id,belong) VALUES
	 ('1844926104935616514','tenant1','tenant1','jdbc:mysql://10.43.138.190:3306/tenant1','root','root','mysql',0,0,'2024-10-12 10:20:35.0','2024-10-12 10:21:55.0','admin','admin',1,'1844926438923849731','business'),
	 ('1844926104935616515','tenant2','tenant2','jdbc:mysql://10.43.138.190:3306/tenant2','root','root','mysql',0,0,'2024-10-12 10:20:35.0','2024-10-12 10:21:55.0','admin','admin',1,'1844926438923849732','business');
