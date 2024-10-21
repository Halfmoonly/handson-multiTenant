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
	 ('1825738761296957441','byd_les_uat_x156','X156-MSP','jdbc:postgresql://10.0.39.47:5432/byd_les_uat_x156','les','les.uat#1.appuser','postgresql',0,0,'2024-08-20 11:36:56.0','2024-08-20 12:35:59.0','admin','admin',1,'1825739296582422530','msp'),
	 ('1825738992839315458','byd_les_uat_x156','X156-BUS','jdbc:postgresql://10.0.39.47:5432/byd_les_uat_x156','les','les.uat#1.appuser','postgresql',0,0,'2024-08-20 11:37:51.0','2024-08-20 12:35:59.0','admin','admin',1,'1825739296582422530','business'),
	 ('1825757974338228226','byd_les_uat_sz54','SZ54-MSP','jdbc:postgresql://10.0.39.47:5432/byd_les_uat_sz54','les','les.uat#1.appuser','postgresql',0,0,'2024-08-20 12:53:17.0','2024-08-20 12:54:58.0','admin','admin',1,'1825758398776627201','msp'),
	 ('1825758167355904002','byd_les_uat_sz54','SZ54-BUS','jdbc:postgresql://10.0.39.47:5432/byd_les_uat_sz54','les','les.uat#1.appuser','postgresql',0,0,'2024-08-20 12:54:02.0','2024-08-20 12:54:58.0','admin','admin',1,'1825758398776627201','business'),
	 ('1833367086599184385',' byd_les_uat_x060','X060_BUS','jdbc:postgresql://10.0.39.47:5432/byd_les_uat_x060','les','les.uat#1.appuser','postgresql',0,0,'2024-09-10 12:49:10.0','2024-09-10 12:56:30.0','5933150','5933150',1,'1833367669871681538','business'),
	 ('1833367494054846466','byd_les_uat_x060','x060_MSP','jdbc:postgresql://10.0.39.47:5432/byd_les_uat_x060','les','les.uat#1.appuser','postgresql',0,0,'2024-09-10 12:50:48.0','2024-09-10 12:56:30.0','5933150','5933150',1,'1833367669871681538','msp'),
	 ('1844563297350209538','byd_les_uat_swj0','SWJ0-MSP','jdbc:postgresql://10.0.39.47:5432/byd_les_uat_swj0','les','les.uat#1.appuser','postgresql',0,0,'2024-10-11 10:18:55.0','2024-10-11 11:02:00.0','7288690','7288690',1,'1844573844682592258','msp'),
	 ('1844563518457139201','byd_les_uat_swj0','SWJ0-BUS','jdbc:postgresql://10.0.39.47:5432/byd_les_uat_swj0','les','les.uat#1.appuser','postgresql',0,0,'2024-10-11 10:19:47.0','2024-10-11 11:02:00.0','7288690','7288690',1,'1844573844682592258','business'),
	 ('1844925408773427202','byd_les_uat_x157','X157_MSP','jdbc:postgresql://10.0.39.47:5432/byd_les_uat_x157','les','les.uat#1.appuser','postgresql',0,0,'2024-10-12 10:17:49.0','2024-10-12 10:21:55.0','admin','admin',1,'1844926438923849730','msp'),
	 ('1844926104935616513','byd_les_uat_x157','X157_BUS','jdbc:postgresql://10.0.39.47:5432/byd_les_uat_x157','les','les.uat#1.appuser','postgresql',0,0,'2024-10-12 10:20:35.0','2024-10-12 10:21:55.0','admin','admin',1,'1844926438923849730','business');
INSERT INTO `multi-tenant-platform`.tenant_data_source (id,name,description,url,user_name,password,driver,init_pool_size,max_pool_size,created_time,updated_time,created_by,updated_by,status,tenant_id,belong) VALUES
	 ('1844926104935616514','tenant1','tenant1','jdbc:mysql://10.43.138.190:3306/tenant1','root','root','mysql',0,0,'2024-10-12 10:20:35.0','2024-10-12 10:21:55.0','admin','admin',1,'1844926438923849731','business'),
	 ('1844926104935616515','tenant2','tenant2','jdbc:mysql://10.43.138.190:3306/tenant2','root','root','mysql',0,0,'2024-10-12 10:20:35.0','2024-10-12 10:21:55.0','admin','admin',1,'1844926438923849732','business');
