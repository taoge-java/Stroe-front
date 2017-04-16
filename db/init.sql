-- 用户信息表
drop table if exists user_info;
create table user_info(
  id int not null auto_increment,
  login_name varchar(100) not null,
  password varchar(100) not null,
  email varchar(100) default null,
  mobile varchar(20) not null,
  code varchar(100) not null,
  regist_ip varchar(100) default null,
  regist_time datetime default null,
  disabled_flag tinyint default 0,
  login_ip varchar(100) default null,
  encrypt varchar(100) default null,
  primary key (id)
)engine=innodb default charset=utf8;
-- 系统操作日志表
drop table if exists system_log;
create table system_log(
   id int not null auto_increment,
   oper_name varchar(100) default "",
   oper_time datetime default null,
   oper_ip varchar(100) default "",
   oper_desc varchar(1024) default "",
   log_type tinyint default 0,
   platform_type tinyint default 0,
   user_type tinyint default 0,
   user_id int default 0,
   primary key (id)
) engine=innodb default charset=utf8;

-- 地区表
drop table if exists system_region;
create table system_region(
  id int not null auto_increment,
  region_code varchar(100) not null,
  region_level tinyint not null,
  region_name varchar(100) not null,
  alias_name varchar(100) default null,
  city_flag int default 0, 
  parent_id int not null,
  primary key (id)
) engine=innodb default charset=utf8;

