-- 系统管理员表
drop table if exists system_admin;
create table system_admin(
   id int not null auto_increment,
   login_name varchar(100) not null,
   real_name varchar(100) default null,
   password varchar(100) not null,
   encrypt varchar(100) not null,
   last_login_ip varchar(100) default null,
   mobile varchar(100) default null,
   nack_name varchar(100) default null,
   create_at datetime default null,
   update_at datetime default null,
   admin_type tinyint(2) default 0,
   disabled_flag tinyint(1) default 0,
   mail varchar(100) default null,
   last_login_time datetime default null,
   super_flag tinyint(1) default 1,
   role_id int default 0,
   login_count int default 0,
   login_error int default 0,
   primary key(id)
)engine=innodb charset=utf8 collate=utf8_general_ci;

-- 系统操作日志表
drop table if exists system_log;
create table system_log(
   id int not null auto_increment,
   oper_name varchar(100) default "",
   oper_time datetime default null,
   oper_ip varchar(100) default "",
   oper_desc varchar(1024) default "",
   login_type tinyint default 0,
   platform_type tinyint default 0,
   user_type tinyint default 0,
   user_id int default 0,
   primary key (id)
) engine=innodb default charset=utf8;

-- 短信发送记录表
drop table if exists sms_send_log;
create table sms_send_log(
    id int not null auto_increment,
    user_info_id int  not null,
    mobile varchar(100) not null,
    content varchar(100) default null,
    send_status tinyint(1) default 1,
    send_remark varchar(100) default null,
    create_at datetime default null,
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

-- 商品信息表
drop table if exists shopping_goods_info;
create table shopping_goods_info(
   id int not null auto_increment,
   shopping_goods_type_id int not null comment "商品类别id",
   goods_name varchar(100) not null comment "商品名称",
   image varchar(255) default "",
   count int not null comment "商品总量",
   sold_count int not null comment "售价",
   description text default "" comment "商品描述",
   price decimal(10,2) default 0 comment "商品价格",
   goods_code varchar(100) default null comment "货品编号",
   primary key (id)
) engine=innodb default charset=utf8;

-- 商品类别表
drop table if exists shopping_goods_type(
   id int not null auto_increment,
   goods_type_name varchar(100) not null comment "类别名称",
   parent_id int default 0;
   sort int default 0 comment "排序"
   primary key (id)
) engine=innodb default charset=utf8;

-- 商品订单表
drop table if exists shopping_goods_order;
create table shopping_goods_order(
   id int not null auto_increment,
   user_info_id int not null,
   order_code vahchar(100) not null,
   amount decimal(10,2) default null,
   order_time datetime defualt null,
   send_time datetime defualt null,
   order_status int default 1,
   create_user varchar(100) default null,
   create_at datetime defualt null,
   remark varchar(255) default null
   primary key (id)
)engine=innodb default charset=utf8;

-- 商品品牌表
drop table if exists shopping_goods_brand;
create table shopping_goods_brand(
  id int not null auto_increment,
  name varchar(100) not null,
  create_user varchar(100) default null,
  create_at datetime defualt null,
  primary key (id)
)engine=innodb default charset=utf8;

--商品订单关联货品表
drop table if exists shopping_goods_order_relevance;
create table shopping_goods_order(
  id int not null auto_increment,
  shopping_goods_order_id int not null,
  shopping_goods_info_id int not null,
  buy_number int default 0,
  amount int default 0，
  price int not null,
  primary key (id)
)engine=innodb default charset=utf8;

-- 用户地址表
drop table if exists shopping_user_address;
create table shopping_user_address(
  id int not null auto_increment,
  user_info _id int not null,
  receiver varchar(100) not null,
  mobile varchar(100) not null,
  default_flag tinyint(1) default 0,
  province_code varchar(100) not null,
  province_name varchar(100) not null,
  city_code varchar(100) not null,
  city_name varchar(100) not null,
  county_code varchar(100) not null,
  county_name varchar(100) not null,
  address varchar(255) not null,
  create_user  varchar(255) default null,
  create_at  varchar(255) default null,
  primary key (id)
)engine=innodb default charset=utf8;

-- 商品颜色表
drop table if exists goods_color;
create table goods_color(
   id int not null auto_increment,
   code varchar(100) default null,
   name varchar(100) not null,
   create_user  varchar(255) default null,
   create_at  varchar(255) default null,
   remark  varchar(100) not null
   primary key (id)
)engine=innodb default charset=utf8;

-- 商品单位表
drop table if exists goods_unit;
create table goods_unit(
   id int not null auto_increment,
   name varchar(100) not null,
   create_user  varchar(255) default null,
   create_at  varchar(255) default null,
   remark  varchar(100) not null
   primary key (id)
)engine=innodb default charset=utf8;

-- 用户信息表
drop table if exists user_info;
create table user_info(
  id int not null auto_increment,
  login_name varchar(100) default null,
  password varchar(100) not null,
  email varchar(100) default null,
  mobile varchar(20) not null,
  code varchar(100) not null,
  ip varchar(100) default null,
  regist_time datetime default null,
  disabled_flag tinyint(1) default 0,
  encrypt varchar(100) default null,
  primary key (id)
)engine=innodb default charset=utf8;

