/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50528
Source Host           : 127.0.0.1:3306
Source Database       : ovs

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2016-04-23 12:12:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for help_board
-- ----------------------------
DROP TABLE IF EXISTS `help_board`;
CREATE TABLE `help_board` (
  `help_board_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `help_title` varchar(200) DEFAULT NULL,
  `help_content` varchar(400) DEFAULT NULL,
  `help_build_time` datetime DEFAULT NULL,
  `help_level` int(11) DEFAULT NULL,
  PRIMARY KEY (`help_board_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of help_board
-- ----------------------------

-- ----------------------------
-- Table structure for help_center
-- ----------------------------
DROP TABLE IF EXISTS `help_center`;
CREATE TABLE `help_center` (
  `help_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL,
  `username` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `content` varchar(400) CHARACTER SET utf8 DEFAULT NULL,
  `parent_id` int(10) DEFAULT NULL,
  `build_time` datetime NOT NULL,
  `help_deal_type` int(11) DEFAULT '0' COMMENT '0未处理，1已回复,2,不做处理，忽略，3此消息是管理员发的,',
  `help_level` int(11) DEFAULT '0' COMMENT '优先级，0重要代表普通，依次类推，数值越大越',
  PRIMARY KEY (`help_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of help_center
-- ----------------------------

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `image_id` int(11) NOT NULL AUTO_INCREMENT,
  `option_id` int(11) DEFAULT NULL COMMENT '图片关联项的id',
  `user_id` int(11) DEFAULT NULL COMMENT '图片上传者',
  `page_id` int(11) DEFAULT NULL COMMENT '所属网页的id',
  `image_path` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '图片路径',
  `old_name` varchar(50) DEFAULT NULL,
  `now_name` varchar(50) DEFAULT NULL,
  `upload_time` datetime DEFAULT NULL COMMENT '上传时间',
  `image_type` varchar(50) DEFAULT NULL COMMENT '图片类型',
  `browse_times` int(11) DEFAULT '0' COMMENT '图片被点击次数',
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of image
-- ----------------------------

-- ----------------------------
-- Table structure for message_board
-- ----------------------------
DROP TABLE IF EXISTS `message_board`;
CREATE TABLE `message_board` (
  `message_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL,
  `content` varchar(400) CHARACTER SET utf8 DEFAULT NULL,
  `build_time` datetime NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `parent_id` int(10) DEFAULT '0' COMMENT '父项，即话题的开始',
  `deal_type` int(11) DEFAULT '0' COMMENT '处理类型,0未审核，2通过审核，1忽略不可见，',
  `receive_id` int(11) DEFAULT '0' COMMENT '信息接收者',
  `position` int(11) DEFAULT '0' COMMENT '所在位置',
  `receive_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of message_board
-- ----------------------------

-- ----------------------------
-- Table structure for option
-- ----------------------------
DROP TABLE IF EXISTS `option`;
CREATE TABLE `option` (
  `option_id` int(11) NOT NULL AUTO_INCREMENT,
  `title_id` int(11) DEFAULT NULL COMMENT '对应的父项id',
  `option_content` varchar(400) DEFAULT NULL COMMENT '选项内容',
  `option_select_times` int(11) DEFAULT '0' COMMENT '选中的次数',
  `option_position` int(11) DEFAULT '0' COMMENT '选项中的位置,默认为0',
  PRIMARY KEY (`option_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of option
-- ----------------------------

-- ----------------------------
-- Table structure for other_option
-- ----------------------------
DROP TABLE IF EXISTS `other_option`;
CREATE TABLE `other_option` (
  `other_option_id` int(11) NOT NULL AUTO_INCREMENT,
  `title_id` int(11) DEFAULT NULL COMMENT '对应的父项id',
  `other_option_content` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户填写的信息',
  `other_option_select_times` int(11) DEFAULT '0' COMMENT '提交后被选中的次数',
  `other_option_disable` smallint(6) DEFAULT '0' COMMENT '0代表该其他项可用，1代表禁用',
  `other_build_time` datetime DEFAULT NULL COMMENT '产生时间',
  PRIMARY KEY (`other_option_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of other_option
-- ----------------------------

-- ----------------------------
-- Table structure for page
-- ----------------------------
DROP TABLE IF EXISTS `page`;
CREATE TABLE `page` (
  `page_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '页面id',
  `user_id` int(10) DEFAULT NULL COMMENT '创建者id',
  `url` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '访问的url',
  `build_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `disable` smallint(6) DEFAULT '0' COMMENT '禁用,0代表否,1代表是',
  `browse_times` int(11) DEFAULT '0' COMMENT '浏览次数',
  `submit_times` int(11) DEFAULT '0' COMMENT '提交次数',
  `dead_line` datetime DEFAULT NULL COMMENT '截止日期',
  `main_title` varchar(400) CHARACTER SET utf8 DEFAULT NULL COMMENT '投票主标题',
  `fast_vote` int(11) DEFAULT '0' COMMENT '快速投票,0代表否，1代表是',
  `private_vote` int(11) DEFAULT '0' COMMENT '投票隐私,0代表任何人可查看和投票,1代表凭密码查看和投票',
  `page_password` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `see_after_vote` int(11) DEFAULT '0' COMMENT '投票后看结果,0代表否,1代表是',
  `vote_desc` varchar(400) CHARACTER SET utf8 DEFAULT NULL COMMENT '活动介绍',
  `display_after_vote` varchar(400) CHARACTER SET utf8 DEFAULT NULL COMMENT '投票后显示的内容',
  `add_collect` int(11) DEFAULT '0' COMMENT '添加收集用户信息的集合，0代表否，1代表是',
  `agree_term` int(11) DEFAULT '0' COMMENT '同意条款，0代表否，1代表是',
  `is_image_vote` int(11) DEFAULT '0' COMMENT '0代表没有图片，1代表有图片',
  PRIMARY KEY (`page_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of page
-- ----------------------------

-- ----------------------------
-- Table structure for request_record
-- ----------------------------
DROP TABLE IF EXISTS `request_record`;
CREATE TABLE `request_record` (
  `request_record_seq` int(11) NOT NULL AUTO_INCREMENT,
  `request_page_id` int(11) DEFAULT NULL,
  `request_ip` varchar(255) DEFAULT NULL,
  `request_date` datetime DEFAULT NULL,
  `request_content` text,
  `request_mac_address` text,
  `other_option_ids` text,
  PRIMARY KEY (`request_record_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of request_record
-- ----------------------------

-- ----------------------------
-- Table structure for title
-- ----------------------------
DROP TABLE IF EXISTS `title`;
CREATE TABLE `title` (
  `title_id` int(11) NOT NULL AUTO_INCREMENT,
  `page_id` int(11) DEFAULT NULL,
  `title_content` varchar(400) CHARACTER SET utf8 DEFAULT NULL,
  `title_type` int(11) DEFAULT '0' COMMENT '投票类型，0代表单选，1代表多选',
  `other_option` int(11) DEFAULT '0' COMMENT '启用其他项,0代表否，1代表是',
  `title_select_times` int(11) DEFAULT '0' COMMENT '该项标题被选中的次数',
  `title_position` int(11) DEFAULT '0' COMMENT '在选项中的位置',
  `other_option_select_times` int(11) DEFAULT '0' COMMENT '其他项被选择的次数',
  `title_other_content` varchar(400) DEFAULT '',
  PRIMARY KEY (`title_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of title
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT 'test spring+hibernate',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of t_user
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '用户密码',
  `email` varchar(50) NOT NULL,
  `user_type` int(1) DEFAULT '0' COMMENT '0代表普通用户，1代表管理员',
  `enable` int(11) DEFAULT '0' COMMENT '0启用，1禁用',
  `is_delete` int(11) DEFAULT '0' COMMENT '逻辑删除，0代表否，1代表是',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'ovs', 'admin', '1', '0', '0');
