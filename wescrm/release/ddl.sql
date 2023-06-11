/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50525
Source Host           : 127.0.0.1:3306
Source Database       : wescrm

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2021-05-28 18:16:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for attachment
-- ----------------------------
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `height` int(11) NOT NULL,
  `width` int(11) NOT NULL,
  `resourceId` bigint(20) NOT NULL COMMENT '资源表id',
  `size` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL COMMENT '用户id',
  `mime` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `createAt` datetime DEFAULT NULL,
  `updateAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 COMMENT='附件表';

-- ----------------------------
-- Table structure for auth_user
-- ----------------------------
DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE `auth_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
  `name` varchar(50) DEFAULT '' COMMENT '姓名',
  `corpuserid` varchar(50) DEFAULT NULL COMMENT '企业微信userid',
  `password` varchar(100) NOT NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(100) NOT NULL COMMENT 'salt',
  `gender` tinyint(1) DEFAULT '1' COMMENT '性别',
  `header` varchar(512) DEFAULT '' COMMENT '头像',
  `mobile` varchar(15) DEFAULT '' COMMENT '手机号码',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态：正常（1），禁用（2）',
  `role` tinyint(1) DEFAULT NULL COMMENT '角色',
  `birthday` date DEFAULT '1900-01-01' COMMENT '生日',
  `position` varchar(50) DEFAULT '' COMMENT '职位',
  `wechat` varchar(50) DEFAULT '' COMMENT '微信号',
  `qq` varchar(20) DEFAULT '' COMMENT 'qq号',
  `loginAt` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  `ip` varchar(15) NOT NULL DEFAULT '' COMMENT '最后一次登录IP',
  `createAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `T_AUTH_USER_USERNAME_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '名称',
  `code` int(11) NOT NULL DEFAULT '0',
  `parent_code` int(11) NOT NULL DEFAULT '0' COMMENT '父级别code',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `createAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_index` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='产品分类表';

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '所属员工id',
  `nickname` varchar(100) DEFAULT NULL COMMENT '昵称',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `wxid` varchar(100) DEFAULT NULL COMMENT '微信id',
  `openid` varchar(100) DEFAULT NULL COMMENT '微信公众号openid',
  `copenid` varchar(100) DEFAULT NULL COMMENT '企业号openid',
  `mopenid` varchar(100) DEFAULT NULL COMMENT '小程序openid',
  `unionid` varchar(100) DEFAULT NULL COMMENT 'unionid',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `gender` int(11) DEFAULT NULL COMMENT '性别',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `card` varchar(100) DEFAULT NULL COMMENT '证件类型',
  `card_no` varchar(100) DEFAULT NULL COMMENT '证件号码',
  `bank` varchar(100) DEFAULT NULL COMMENT '银行',
  `bank_no` varchar(100) DEFAULT NULL COMMENT '银行卡号',
  `bank_site` varchar(100) DEFAULT NULL COMMENT '银行网点',
  `money` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '用户余额',
  `point` int(11) NOT NULL DEFAULT '0' COMMENT '用户积分',
  `level` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0-普通用户，1-普通会员，2-白银，3-黄金，4-白金会员，5-黑金会员，6-钻石',
  `rebate_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '佣金级别：0-普通用户，1-1级，2-2级',
  `rebate_money` decimal(8,2) DEFAULT NULL COMMENT '总佣金',
  `parent_id` int(11) NOT NULL COMMENT '上级分销商id',
  `parent_name` varchar(100) DEFAULT NULL COMMENT '上级分销商姓名',
  `order_money` decimal(8,2) DEFAULT NULL COMMENT '总订单金额',
  `order_time` datetime DEFAULT NULL COMMENT '最后一次下单时间',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `user_count` int(11) DEFAULT '0',
  `createAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='客户表，客户购买';

-- ----------------------------
-- Table structure for customer_rebate
-- ----------------------------
DROP TABLE IF EXISTS `customer_rebate`;
CREATE TABLE `customer_rebate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '所属员工id',
  `customer_id` int(11) DEFAULT NULL COMMENT '客户id',
  `parent_id` int(11) NOT NULL COMMENT '上级分销商id',
  `money` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '返佣金额',
  `rebate` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '返佣比例',
  `order_id` int(11) NOT NULL,
  `createAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='返佣记录';

-- ----------------------------
-- Table structure for customer_tags
-- ----------------------------
DROP TABLE IF EXISTS `customer_tags`;
CREATE TABLE `customer_tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) DEFAULT NULL COMMENT '客户id',
  `tag_id` int(11) DEFAULT NULL COMMENT '标签id',
  `createAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='客户表标签';

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL COMMENT '部门id',
  `parentid` int(11) NOT NULL COMMENT '父id',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `name_en` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `createAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(100) NOT NULL COMMENT '订单id',
  `customer_id` int(11) NOT NULL COMMENT '下单人id',
  `rebate_customer_id` int(11) DEFAULT '0' COMMENT '获取佣金用户id',
  `user_id` int(11) DEFAULT '0' COMMENT '用户id',
  `product_id` varchar(100) NOT NULL COMMENT '商品ID',
  `product_name` varchar(100) NOT NULL COMMENT '商品名称',
  `product_count` int(11) NOT NULL DEFAULT '1' COMMENT '商品数量',
  `product_price` decimal(8,2) NOT NULL COMMENT '购买商品单价',
  `product_rebate` decimal(8,2) NOT NULL COMMENT '返佣百分比',
  `product_picture` int(11) DEFAULT NULL COMMENT '商品图片id',
  `pay_method` int(11) NOT NULL COMMENT '支付方式：1-现金，2-余额，3-微信，4-支付宝，5-网银，6-公交卡',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `money` decimal(8,2) NOT NULL COMMENT '订单金额',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '订单状态：1-待支付，2-已支付，3-待发货，4-待收货，5-已完成，6-退单，7-完成退单',
  `address` varchar(100) NOT NULL COMMENT '订单地址',
  `point` int(11) NOT NULL DEFAULT '0' COMMENT '使用积分',
  `invoice` varchar(100) DEFAULT NULL COMMENT '发票信息',
  `shipping_sn` varchar(50) DEFAULT NULL COMMENT '快递单号',
  `shipping_time` datetime DEFAULT NULL COMMENT '发货时间',
  `receive_time` datetime DEFAULT NULL COMMENT '收货时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `createAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` varchar(100) NOT NULL COMMENT '系统唯一id',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `price` decimal(8,2) NOT NULL COMMENT '销售价格',
  `rebate` decimal(8,2) NOT NULL COMMENT '返佣百分比',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '上下架状态：0下架,1上架',
  `picture` int(11) DEFAULT NULL COMMENT '商品图片',
  `category` int(11) DEFAULT NULL COMMENT '分类',
  `category_name` varchar(100) DEFAULT NULL COMMENT '分类名称',
  `description` text COMMENT '商品描述',
  `main_picture` int(11) DEFAULT '0' COMMENT '主图',
  `recommend` int(11) DEFAULT '0' COMMENT '是否推荐',
  `createAt` datetime DEFAULT '2021-05-01 12:12:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` bigint(20) NOT NULL,
  `encoding` varchar(50) NOT NULL COMMENT '编码',
  `hash` varchar(64) NOT NULL,
  `content` mediumtext NOT NULL COMMENT '内容',
  `createAt` datetime DEFAULT NULL,
  `updateAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNI_HASH` (`hash`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='附件资源表';

-- ----------------------------
-- Table structure for tags
-- ----------------------------
DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(100) NOT NULL COMMENT '标签',
  `type` varchar(100) DEFAULT NULL COMMENT '标签类型',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `createAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='标签表';

-- ----------------------------
-- Table structure for wechat_config
-- ----------------------------
DROP TABLE IF EXISTS `wechat_config`;
CREATE TABLE `wechat_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wid` varchar(100) NOT NULL COMMENT '唯一id',
  `name` varchar(100) DEFAULT NULL COMMENT '账号名称',
  `appid` varchar(100) DEFAULT NULL COMMENT '公众号id',
  `appsecret` varchar(100) DEFAULT NULL COMMENT '公众号secret',
  `url` varchar(255) NOT NULL COMMENT '公众号url',
  `token` varchar(255) NOT NULL COMMENT '公众号token',
  `corpid` varchar(100) DEFAULT NULL COMMENT '企业微信id',
  `agentid` varchar(100) DEFAULT NULL COMMENT '企业微信应用id',
  `corsecret` varchar(100) DEFAULT NULL COMMENT '企业微信secret',
  `mappid` varchar(100) DEFAULT NULL COMMENT '小程序id',
  `mappsecret` varchar(100) DEFAULT NULL COMMENT '小程序secret',
  `userid` int(11) DEFAULT NULL COMMENT '用户id',
  `createAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='微信配置';

-- ----------------------------
-- Table structure for werun_steps
-- ----------------------------
DROP TABLE IF EXISTS `werun_steps`;
CREATE TABLE `werun_steps` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(100) NOT NULL COMMENT 'openid',
  `year` int(11) DEFAULT NULL COMMENT '年',
  `month` int(11) DEFAULT NULL COMMENT '月',
  `dates` int(11) DEFAULT NULL COMMENT '日',
  `steps` int(11) DEFAULT NULL COMMENT '步数',
  `kill_steps` int(11) DEFAULT NULL COMMENT '已消耗步数',
  `createAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for werun_user
-- ----------------------------
DROP TABLE IF EXISTS `werun_user`;
CREATE TABLE `werun_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(100) NOT NULL,
  `steps` int(11) DEFAULT '0' COMMENT '总步数',
  `createAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
