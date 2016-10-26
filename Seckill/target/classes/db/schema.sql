#创建数据库
create database seckill;
#使用seckill数据库
use seckill;
#创建seckill表
create table seckill(
	seckill_id bigint not null auto_increment comment '商品库存编号',
	name varchar(120) not null comment '商品名称',
	number int not null comment '库存数量',
	start_time timestamp not null comment '秒杀开始时间',
	end_time timestamp default current_timestamp not null comment '秒杀结束时间',
	create_time timestamp default current_timestamp not null comment '创建时间',
	primary key(seckill_id),
	key idx_start_time(start_time),
	key idx_end_time(end_time),
	key idx_create_time(create_time)
)engine=InnoDB auto_increment=1000 default charset=utf8 comment='秒杀库存表';
#创建秒杀成功明细表
create table success_killed(
	seckill_id bigint not null comment '商品库存编号',
	user_phone bigint not null comment '用户电话号码',
	state tinyint default -1  not null comment '状态标识:-1:无效,0:成功,1:已付款,2:已发货',
	create_time timestamp default current_timestamp not null comment '创建时间',
	primary key(seckill_id,user_phone),
	key idx_create_time(create_time)
)engine=InnoDB default charset=utf8 comment='秒杀成功明细表';
#插入数据
insert into seckill(name,number,start_time,end_time)
values
('1000元秒杀iphone6',100,'2016-10-20 00:00:00','2016-10-21 00:00:00'),
('500元秒杀ipad2',200,'2016-10-20 00:00:00','2016-10-21 00:00:00'),
('300元秒杀小米4',300,'2016-10-20 00:00:00','2016-10-21 00:00:00'),
('200元秒杀红米note',400,'2016-10-20 00:00:00','2016-10-21 00:00:00')