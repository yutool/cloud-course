SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course` (
`course_id` char(30) NOT NULL,
`course_num` char(10) NOT NULL,
`course_name` char(30) NOT NULL,
`course_pic` char(90) NOT NULL,
`clazz_name` char(30) NOT NULL,
`term` char(10) NOT NULL,
`synopsis` char(90) NULL,
`arrive_num` int(11) NOT NULL DEFAULT 0,
`resource_num` int(11) NOT NULL DEFAULT 0,
`experience_num` int(11) NOT NULL DEFAULT 0,
`appraise` tinyint NOT NULL DEFAULT 0,
`teacher_id` char(30) NOT NULL,
PRIMARY KEY (`course_id`)
);

DROP TABLE IF EXISTS `t_member`;
CREATE TABLE `t_member` (
`user_id` char(30) NOT NULL,
`course_id` char(30) NOT NULL,
`arrive` int(11) NOT NULL DEFAULT 0,
`score` int(11) NOT NULL DEFAULT 0,
`remark` varchar(100) NULL,
PRIMARY KEY (`user_id`, `course_id`)
);

DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
`notice_id` char(30) NOT NULL,
`author` char(30) NOT NULL,
`content` varchar(100) NOT NULL,
`release_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
`type` int NOT NULL DEFAULT 0,
`course_id` char(30) NOT NULL,
PRIMARY KEY (`notice_id`)
);

DROP TABLE IF EXISTS `t_resource`;
CREATE TABLE `t_resource` (
`res_id` char(30) NOT NULL,
`res_name` char(30) NOT NULL,
`res_size` int NOT NULL,
`upload_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
`down_link` char(90) NOT NULL,
`experience` int NOT NULL,
`course_id` char(30) NOT NULL,
PRIMARY KEY (`res_id`)
);

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
`user_id` char(30) NOT NULL,
`user_name` char(30) NOT NULL,
`email` char(30) NOT NULL,
`password` char(60) NOT NULL,
`avatar` char(90) NOT NULL,
`nickname` char(30) NOT NULL,
`gender` varchar(3) NOT NULL DEFAULT '男',
`stu_num` char(20) NULL,
`phone_num` char(11) NULL,
`birthday` date NULL,
`signature` varchar(100) NULL,
`salt` char(60) NOT NULL,
`role` char(10) NOT NULL,
PRIMARY KEY (`user_id`)
);

DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
`id` bigint NOT NULL AUTO_INCREMENT,
`user_id` char(30) NULL,
`module` varchar(60) NULL,
`operation` varchar(60) NULL,
`method` varchar(255) NULL,
`params` varchar(255) NULL,
`time` bigint NOT NULL,
`ip` char(20) NOT NULL,
`create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`id`)
);

ALTER TABLE `t_member` ADD CONSTRAINT `fk_t_member_t_user_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`);
ALTER TABLE `t_member` ADD CONSTRAINT `fk_t_member_t_course_1` FOREIGN KEY (`course_id`) REFERENCES `t_course` (`course_id`);
ALTER TABLE `t_notice` ADD CONSTRAINT `fk_t_notice_t_course_1` FOREIGN KEY (`course_id`) REFERENCES `t_course` (`course_id`);
ALTER TABLE `t_resource` ADD CONSTRAINT `fk_t_resource_t_course_1` FOREIGN KEY (`course_id`) REFERENCES `t_course` (`course_id`);
ALTER TABLE `t_course` ADD CONSTRAINT `fk_t_course_t_user_1` FOREIGN KEY (`teacher_id`) REFERENCES `t_user` (`user_id`);
ALTER TABLE `t_log` ADD CONSTRAINT `fk_t_log_t_user_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`);

SET FOREIGN_KEY_CHECKS = 1;
