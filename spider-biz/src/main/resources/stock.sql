/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.7.25-log : Database - stock
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`stock` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `stock`;

/*Table structure for table `active_dept_operation` */

DROP TABLE IF EXISTS `active_dept_operation`;

CREATE TABLE `active_dept_operation` (
  `operation_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trade_date` varchar(10) DEFAULT NULL COMMENT '交易日期',
  `buy_dept` int(11) DEFAULT NULL COMMENT '买入营业部',
  `sell_dept` int(11) DEFAULT NULL COMMENT '卖出营业部',
  `total_buy_amount` double(10,2) DEFAULT NULL COMMENT '总买额',
  `total_buy_stock` int(11) DEFAULT NULL COMMENT '总买票',
  `total_sell_amount` double(10,2) DEFAULT NULL COMMENT '总卖额',
  `total_sell_stock` int(11) DEFAULT NULL COMMENT '总卖票',
  `net` double(10,2) DEFAULT NULL COMMENT '净买',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`operation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1525 DEFAULT CHARSET=utf8;

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `trade_date` varchar(10) DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `content` text,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bidding` */

DROP TABLE IF EXISTS `bidding`;

CREATE TABLE `bidding` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `symbol` varchar(10) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `vol` int(11) DEFAULT NULL,
  `sell_surplus` int(11) DEFAULT NULL,
  `buy_surplus` int(11) DEFAULT NULL,
  `trade_time` varchar(20) DEFAULT NULL,
  `deal_amount` double(10,2) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  KEY `id` (`id`),
  KEY `symbol` (`symbol`),
  KEY `trade_time` (`trade_time`)
) ENGINE=MyISAM AUTO_INCREMENT=1352 DEFAULT CHARSET=utf8;

/*Table structure for table `bidding_result` */

DROP TABLE IF EXISTS `bidding_result`;

CREATE TABLE `bidding_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `symbol` varchar(10) DEFAULT NULL,
  `trade_time` varchar(10) DEFAULT NULL,
  `vol` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `percent` double DEFAULT NULL,
  `trade_date` varchar(10) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '交易类型，1：买，2：卖',
  `gmt_create` datetime DEFAULT NULL,
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `clique` */

DROP TABLE IF EXISTS `clique`;

CREATE TABLE `clique` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL COMMENT '帮派名称',
  `profile` varchar(1000) DEFAULT NULL COMMENT '介绍',
  `gmt_create` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1027 DEFAULT CHARSET=utf8 COMMENT='帮派';

/*Table structure for table `clique_dept` */

DROP TABLE IF EXISTS `clique_dept`;

CREATE TABLE `clique_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `clique_id` bigint(20) DEFAULT NULL COMMENT '帮派ID',
  `sec_dept_code` varchar(20) DEFAULT NULL,
  `sec_dept_name` varchar(100) DEFAULT NULL COMMENT '营业部名称',
  `dept_type` int(11) DEFAULT NULL COMMENT '营业部类型，1、主力营业部，2，辅助营业部',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_update` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1211 DEFAULT CHARSET=utf8 COMMENT='帮派营业部';

/*Table structure for table `clique_stock` */

DROP TABLE IF EXISTS `clique_stock`;

CREATE TABLE `clique_stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `clique_id` bigint(20) DEFAULT NULL,
  `stock_code` varchar(10) DEFAULT NULL,
  `stock_name` varchar(20) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='帮派股票池';

/*Table structure for table `company` */

DROP TABLE IF EXISTS `company`;

CREATE TABLE `company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `symbol` varchar(10) DEFAULT NULL COMMENT '股票符号',
  `stock_code` varchar(10) DEFAULT NULL,
  `stock_name` varchar(45) DEFAULT NULL,
  `company_name` varchar(100) DEFAULT NULL,
  `market_value` double(15,2) DEFAULT NULL COMMENT '市值',
  `float_market_value` double DEFAULT NULL COMMENT '流通市值',
  `active_market_value` double DEFAULT NULL COMMENT '实际流通市值',
  `last_price` decimal(10,3) DEFAULT NULL COMMENT '上个交易日股价',
  `stock_total` double DEFAULT NULL COMMENT '总股本',
  `float_stock_total` double DEFAULT NULL COMMENT '流通股本',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_update` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `symbol_UNIQUE` (`symbol`)
) ENGINE=InnoDB AUTO_INCREMENT=6079 DEFAULT CHARSET=utf8;

/*Table structure for table `five_range_statis` */

DROP TABLE IF EXISTS `five_range_statis`;

CREATE TABLE `five_range_statis` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `symbol` varchar(10) DEFAULT NULL,
  `big_sell` int(11) DEFAULT '0' COMMENT '大卖单数量',
  `big_buy` int(11) DEFAULT '0' COMMENT '大买单数量',
  `trade_date` varchar(10) DEFAULT NULL,
  `active_market_value` double(10,2) DEFAULT NULL COMMENT '实际流通市值',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_update` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `symbol` (`symbol`),
  KEY `date` (`trade_date`)
) ENGINE=InnoDB AUTO_INCREMENT=16024 DEFAULT CHARSET=utf8;

/*Table structure for table `holder_num` */

DROP TABLE IF EXISTS `holder_num`;

CREATE TABLE `holder_num` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `symbol` varchar(10) DEFAULT NULL,
  `end_date` varchar(10) DEFAULT NULL COMMENT '截止日期',
  `totality` int(11) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL COMMENT '价格',
  `chg_rate` double(10,4) DEFAULT NULL COMMENT '比上期变化',
  `gmt_create` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `symbol` (`symbol`),
  KEY `end_date` (`end_date`)
) ENGINE=MyISAM AUTO_INCREMENT=40841 DEFAULT CHARSET=utf8;

/*Table structure for table `holiday` */

DROP TABLE IF EXISTS `holiday`;

CREATE TABLE `holiday` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `year` varchar(4) DEFAULT NULL COMMENT '年份',
  `dates` varchar(200) DEFAULT NULL COMMENT '日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Table structure for table `hui_che_detail` */

DROP TABLE IF EXISTS `hui_che_detail`;

CREATE TABLE `hui_che_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `symbol` varchar(10) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `rate` double DEFAULT NULL COMMENT '回撤率',
  `percent` double DEFAULT NULL COMMENT '涨跌幅',
  `trade_date` varchar(10) DEFAULT NULL,
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `trade_date_symbol` (`trade_date`,`symbol`)
) ENGINE=InnoDB AUTO_INCREMENT=4524 DEFAULT CHARSET=utf8;

/*Table structure for table `jd_statis` */

DROP TABLE IF EXISTS `jd_statis`;

CREATE TABLE `jd_statis` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `symbol` varchar(10) DEFAULT NULL,
  `active_float_market` double DEFAULT NULL,
  `jd_total` int(11) DEFAULT NULL,
  `trade_date` varchar(10) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_update` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `symbol` (`symbol`),
  KEY `trade_date` (`trade_date`)
) ENGINE=InnoDB AUTO_INCREMENT=2934 DEFAULT CHARSET=utf8 COMMENT='夹单统计';

/*Table structure for table `k_line` */

DROP TABLE IF EXISTS `k_line`;

CREATE TABLE `k_line` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `symbol` varchar(10) NOT NULL,
  `volume` bigint(20) DEFAULT NULL,
  `open` double DEFAULT NULL,
  `close` double DEFAULT NULL,
  `high` double DEFAULT NULL,
  `low` double DEFAULT NULL,
  `change_amount` double DEFAULT NULL,
  `percent` double DEFAULT NULL,
  `turnrate` double DEFAULT NULL,
  `ma5` double DEFAULT NULL,
  `ma10` double DEFAULT NULL,
  `ma20` double DEFAULT NULL,
  `ma30` double DEFAULT NULL,
  `trade_date` varchar(20) DEFAULT NULL,
  `amount` double DEFAULT NULL COMMENT '成交额',
  `yesterday_close` double DEFAULT NULL COMMENT '昨收',
  `shape` int(11) DEFAULT '0' COMMENT 'K线形态，1：多头',
  `gmt_create` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `symbol_tradeDate` (`symbol`,`trade_date`),
  KEY `trade_date` (`trade_date`)
) ENGINE=InnoDB AUTO_INCREMENT=1324370 DEFAULT CHARSET=utf8;

/*Table structure for table `k_line_statis` */

DROP TABLE IF EXISTS `k_line_statis`;

CREATE TABLE `k_line_statis` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trade_date` varchar(20) DEFAULT NULL COMMENT '交易日期',
  `zt` int(11) DEFAULT '0' COMMENT '涨停数',
  `dt` int(11) DEFAULT '0' COMMENT '跌停数',
  `hc` int(11) DEFAULT '0' COMMENT '大回撤数',
  `sz` int(11) DEFAULT NULL COMMENT '上涨数',
  `xd` int(11) DEFAULT NULL COMMENT '下跌数',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_update` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `trade_date` (`trade_date`)
) ENGINE=InnoDB AUTO_INCREMENT=2040 DEFAULT CHARSET=utf8;

/*Table structure for table `large_vol` */

DROP TABLE IF EXISTS `large_vol`;

CREATE TABLE `large_vol` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `symbol` varchar(10) DEFAULT NULL,
  `vol` int(11) DEFAULT NULL COMMENT '成交量',
  `trade_type` int(11) DEFAULT NULL COMMENT '交易类型，-1：卖，0：中性，1：买',
  `percent` double DEFAULT NULL COMMENT '变化百分比',
  `amount` double DEFAULT NULL COMMENT '成交额',
  `trade_time` varchar(20) DEFAULT NULL COMMENT '交易时间',
  `gmt_create` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `symbol` (`symbol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `large_vol_statis` */

DROP TABLE IF EXISTS `large_vol_statis`;

CREATE TABLE `large_vol_statis` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `symbol` varchar(10) DEFAULT NULL,
  `sell_total` int(11) DEFAULT '0',
  `buy_total` int(11) DEFAULT '0',
  `equal_total` int(11) DEFAULT '0',
  `trade_date` varchar(10) DEFAULT NULL,
  `active_market_value` double(10,2) DEFAULT NULL COMMENT '实际流通市值',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_update` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `symbol` (`symbol`),
  KEY `date` (`trade_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='大单统计';

/*Table structure for table `lian_ban` */

DROP TABLE IF EXISTS `lian_ban`;

CREATE TABLE `lian_ban` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `symbol` varchar(10) DEFAULT NULL COMMENT '股票代码',
  `stock_name` varchar(10) DEFAULT NULL COMMENT '股票名称',
  `days` int(11) DEFAULT NULL COMMENT '总天数',
  `lb_days` int(11) DEFAULT NULL COMMENT '连板天数',
  `percent` double(10,3) DEFAULT NULL COMMENT '涨跌幅',
  `trade_date` varchar(10) DEFAULT NULL COMMENT '交易日期',
  `shape` varchar(20) DEFAULT NULL COMMENT '形态,N+1的形态',
  `plate` bigint(20) DEFAULT NULL COMMENT '板块',
  `reason` varchar(20) DEFAULT NULL COMMENT '涨停原因',
  `status` int(11) DEFAULT NULL COMMENT '状态，1：涨停，2：未涨停，3：停牌',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_update` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `trade_date` (`trade_date`),
  KEY `list` (`days`,`trade_date`,`status`)
) ENGINE=InnoDB AUTO_INCREMENT=138188 DEFAULT CHARSET=utf8;

/*Table structure for table `lian_ban_plate` */

DROP TABLE IF EXISTS `lian_ban_plate`;

CREATE TABLE `lian_ban_plate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trade_date` varchar(10) DEFAULT NULL,
  `plate` varchar(10) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_update` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `trade_date` (`trade_date`)
) ENGINE=InnoDB AUTO_INCREMENT=241 DEFAULT CHARSET=utf8;

/*Table structure for table `long_hu` */

DROP TABLE IF EXISTS `long_hu`;

CREATE TABLE `long_hu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `symbol` varchar(10) DEFAULT NULL COMMENT '股票代码',
  `stock_name` varchar(45) DEFAULT NULL COMMENT '股票名称',
  `trade_date` varchar(10) DEFAULT NULL COMMENT '交易日期',
  `price` double DEFAULT NULL COMMENT '价格',
  `chg` double DEFAULT NULL COMMENT '涨额',
  `chg_percent` double DEFAULT NULL COMMENT '涨幅',
  `turnover` double DEFAULT NULL COMMENT '换手率',
  `amplitude` double DEFAULT NULL COMMENT '振幅',
  `tot_mkt_val` double DEFAULT NULL COMMENT '总市值',
  `neg_mkt_val` double DEFAULT NULL COMMENT '流通市值',
  `sec_dept_relation` int(11) DEFAULT NULL COMMENT '营业部关联性',
  `operate_clique` bigint(20) DEFAULT NULL COMMENT '操作帮派',
  `yr_type` varchar(30) DEFAULT NULL COMMENT '一日龙虎类型',
  `yr_amt` varchar(35) DEFAULT NULL COMMENT '一日金额,"买入,卖出,净买入"',
  `er_type` varchar(30) DEFAULT NULL COMMENT '二日龙虎类型',
  `er_amt` varchar(35) DEFAULT NULL COMMENT '二日金额,"买入,卖出,净买入"',
  `sr_type` varchar(30) DEFAULT NULL COMMENT '三日龙虎类型',
  `sr_amt` varchar(35) DEFAULT NULL COMMENT '三日金额,"买入,卖出,净买入"',
  `main_force` varchar(50) DEFAULT NULL COMMENT '主力',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_update` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `symbol` (`symbol`),
  KEY `trade_date` (`trade_date`),
  KEY `operate_clique` (`operate_clique`)
) ENGINE=MyISAM AUTO_INCREMENT=156897 DEFAULT CHARSET=utf8;

/*Table structure for table `long_hu_date` */

DROP TABLE IF EXISTS `long_hu_date`;

CREATE TABLE `long_hu_date` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `symbol` varchar(10) DEFAULT NULL,
  `trade_date` date DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='龙虎榜日期';

/*Table structure for table `long_hu_detail` */

DROP TABLE IF EXISTS `long_hu_detail`;

CREATE TABLE `long_hu_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `long_hu_id` bigint(20) DEFAULT NULL,
  `symbol` varchar(10) DEFAULT NULL COMMENT '股票代码',
  `trade_date` varchar(10) DEFAULT NULL,
  `buy_amt` decimal(10,2) DEFAULT NULL COMMENT '买入，单位万',
  `sell_amt` decimal(10,2) DEFAULT NULL COMMENT '卖出，单位万',
  `net_buy` decimal(10,2) DEFAULT NULL COMMENT '净买入，单位万',
  `sec_dept_code` varchar(20) DEFAULT NULL COMMENT '证券公司营业部',
  `date_type` int(11) DEFAULT NULL COMMENT '交易日期类型：1、一日，2、二日，3、三日',
  `clique_id` bigint(20) DEFAULT NULL COMMENT '帮派',
  `relation` int(11) DEFAULT NULL COMMENT '关联度，相同数字表示关联在一起，数字靠前表示关联的营业部越多',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_update` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `symbol` (`symbol`),
  KEY `sec_dept_code` (`sec_dept_code`),
  KEY `long_hu_id` (`long_hu_id`),
  KEY `trade_date` (`trade_date`)
) ENGINE=MyISAM AUTO_INCREMENT=1562688 DEFAULT CHARSET=utf8;

/*Table structure for table `long_hu_type` */

DROP TABLE IF EXISTS `long_hu_type`;

CREATE TABLE `long_hu_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lh_type` varchar(10) DEFAULT NULL COMMENT '龙虎类型',
  `lh_desc` varchar(100) DEFAULT NULL,
  `date_type` int(11) DEFAULT NULL COMMENT '龙虎榜日期类型：1、一日，2、二日，3、三日',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

/*Table structure for table `my_follow_dept` */

DROP TABLE IF EXISTS `my_follow_dept`;

CREATE TABLE `my_follow_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '营业部ID',
  `dept_code` varchar(20) DEFAULT NULL COMMENT '营业部编号',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `gmt_create` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='我关注的营业部';

/*Table structure for table `news` */

DROP TABLE IF EXISTS `news`;

CREATE TABLE `news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `summary` varchar(200) DEFAULT NULL COMMENT '摘要',
  `type_id` bigint(20) DEFAULT NULL COMMENT '类型',
  `issue_time` varchar(20) DEFAULT NULL COMMENT '发布时间',
  `source` varchar(20) DEFAULT '原创' COMMENT '来源',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_update` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `news_type` */

DROP TABLE IF EXISTS `news_type`;

CREATE TABLE `news_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_update` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `province` */

DROP TABLE IF EXISTS `province`;

CREATE TABLE `province` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `province_name` varchar(50) DEFAULT NULL,
  `code` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=63 DEFAULT CHARSET=utf8 COMMENT='省份';

/*Table structure for table `rmtb_social_user` */

DROP TABLE IF EXISTS `rmtb_social_user`;

CREATE TABLE `rmtb_social_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `social_media` varchar(20) DEFAULT NULL COMMENT '社交媒体',
  `username` varchar(50) DEFAULT NULL COMMENT '社交媒体账号',
  `header_image` varchar(200) DEFAULT NULL COMMENT '头像',
  `rm_username` varchar(50) DEFAULT NULL COMMENT 'rebatesme用户名',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `social_user` (`social_media`,`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sec_company` */

DROP TABLE IF EXISTS `sec_company`;

CREATE TABLE `sec_company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(30) DEFAULT NULL,
  `code` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=355 DEFAULT CHARSET=utf8 COMMENT='证券公司';

/*Table structure for table `sec_dept` */

DROP TABLE IF EXISTS `sec_dept`;

CREATE TABLE `sec_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dept_addr` varchar(200) DEFAULT NULL COMMENT '营业部地址',
  `dept_addr_short` varchar(100) DEFAULT NULL COMMENT '营业部简称',
  `code` varchar(20) DEFAULT NULL,
  `province` varchar(10) DEFAULT NULL,
  `dept_type` int(11) DEFAULT NULL COMMENT '营业部类型，1：敢死队，2：实力游资',
  `active_dept` int(11) DEFAULT NULL COMMENT '活跃营业部',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_update` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `code` (`code`),
  KEY `dept_addr` (`dept_addr`)
) ENGINE=InnoDB AUTO_INCREMENT=15126 DEFAULT CHARSET=utf8 COMMENT='证券营业部';

/*Table structure for table `shareholder` */

DROP TABLE IF EXISTS `shareholder`;

CREATE TABLE `shareholder` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `holder_name` varchar(150) DEFAULT NULL COMMENT '股东名称',
  `holder_code` varchar(20) DEFAULT NULL COMMENT '股东代码，这里只有机构才有代码',
  `holder_type` int(11) DEFAULT NULL COMMENT '股东类型，1:个人，2:机构',
  `clique_id` bigint(20) DEFAULT NULL COMMENT '帮派',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_update` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `holder_code` (`holder_code`)
) ENGINE=MyISAM AUTO_INCREMENT=102377 DEFAULT CHARSET=utf8 COMMENT='股东';

/*Table structure for table `stock_diary` */

DROP TABLE IF EXISTS `stock_diary`;

CREATE TABLE `stock_diary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `stock_code` varchar(10) DEFAULT NULL COMMENT '股票代码',
  `stock_name` varchar(20) DEFAULT NULL COMMENT '股票名称',
  `start_date` varchar(10) DEFAULT NULL COMMENT '开始日期',
  `end_date` varchar(10) DEFAULT NULL COMMENT '结束日期',
  `phase` int(11) DEFAULT NULL COMMENT '阶段',
  `chip_least` double(10,2) DEFAULT NULL COMMENT '最少筹码，万为单位',
  `chip_date` varchar(10) DEFAULT NULL COMMENT '筹码日期',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `clique_id` bigint(20) DEFAULT NULL COMMENT '操作帮派',
  `authority` int(11) DEFAULT NULL COMMENT '权限，0 不公开，1 公开，2 指定人可见',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_update` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `stock_code` (`stock_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `stock_name_initial` */

DROP TABLE IF EXISTS `stock_name_initial`;

CREATE TABLE `stock_name_initial` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `symbol` varchar(10) DEFAULT NULL,
  `stock_name` varchar(10) DEFAULT NULL,
  `initial_group` varchar(10) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `capital_group` (`initial_group`)
) ENGINE=MyISAM AUTO_INCREMENT=17569 DEFAULT CHARSET=utf8;

/*Table structure for table `stock_shareholder` */

DROP TABLE IF EXISTS `stock_shareholder`;

CREATE TABLE `stock_shareholder` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `symbol` varchar(10) DEFAULT NULL,
  `shareholder` bigint(20) DEFAULT NULL COMMENT '股东',
  `end_date` varchar(10) DEFAULT NULL COMMENT '截止时间',
  `ranking` int(11) DEFAULT NULL COMMENT '序号',
  `hold_count` int(11) DEFAULT NULL COMMENT '股数',
  `hold_float_rate` double(5,2) DEFAULT NULL COMMENT '持有流通股比例',
  `hold_rate` double DEFAULT NULL COMMENT '持股比例',
  `is_new_holder` int(11) DEFAULT NULL COMMENT '是否为新股东，1:是，0:否',
  `chg_count` int(11) DEFAULT NULL COMMENT '较上期变化股数',
  `chg_rate` double DEFAULT NULL COMMENT '变化比例',
  `stock_type` int(11) DEFAULT NULL COMMENT '股本类型，1、流通股东，2、股东',
  `publish_date` varchar(10) DEFAULT NULL COMMENT '发布日期',
  `gmt_create` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `shareholder` (`shareholder`),
  KEY `symbol` (`symbol`),
  KEY `publish_date` (`publish_date`)
) ENGINE=InnoDB AUTO_INCREMENT=983263 DEFAULT CHARSET=utf8;

/*Table structure for table `synergy` */

DROP TABLE IF EXISTS `synergy`;

CREATE TABLE `synergy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dept_count` int(11) DEFAULT NULL COMMENT '营业部数目',
  `total` int(11) DEFAULT NULL COMMENT '协同次数',
  `s_g` int(11) DEFAULT NULL COMMENT '分组',
  `gmt_create` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `s_g` (`s_g`)
) ENGINE=InnoDB AUTO_INCREMENT=84429 DEFAULT CHARSET=utf8 COMMENT='营业部龙虎榜协同数据';

/*Table structure for table `synergy_detail` */

DROP TABLE IF EXISTS `synergy_detail`;

CREATE TABLE `synergy_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `s_g` int(11) DEFAULT NULL COMMENT '分组',
  `dept` varchar(10) DEFAULT NULL COMMENT '营业部',
  PRIMARY KEY (`id`),
  KEY `s_id` (`s_g`),
  KEY `dept` (`dept`)
) ENGINE=InnoDB AUTO_INCREMENT=222886 DEFAULT CHARSET=utf8 COMMENT='营业部龙虎协同详情';

/*Table structure for table `upper_shadow` */

DROP TABLE IF EXISTS `upper_shadow`;

CREATE TABLE `upper_shadow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `symbol` varchar(10) DEFAULT NULL,
  `trade_date` varchar(10) DEFAULT NULL COMMENT '交易日',
  `chg_percent` double DEFAULT NULL COMMENT '涨跌幅',
  `max_chg_percent` double DEFAULT NULL COMMENT '最大涨幅',
  `shadow_percent` double DEFAULT NULL COMMENT '上影线幅度',
  `active_float_market` double DEFAULT NULL COMMENT '可流通市值',
  `gmt_create` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4927 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
