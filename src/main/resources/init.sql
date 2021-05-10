SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `stu_id` varchar(11) NOT NULL,
  `pw` varchar(255) NOT NULL,
  `grade` varchar(2) NOT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '21020001001', '21020001001', '19', 'Michael');
INSERT INTO `t_user` VALUES ('2', '22020001001', '22020001001', '19', 'Darjeeling');

-- ----------------------------
-- Table structure for t_lecturer
-- ----------------------------
DROP TABLE IF EXISTS `t_lecturer`;
CREATE TABLE `t_lecturer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `info` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Records of t_lecturer
-- ----------------------------
INSERT INTO `t_lecturer` VALUES ('1', '高峰', '移动软件开发课程教师');
INSERT INTO `t_lecturer` VALUES ('2', '张弛', '开设了概率课程');
INSERT INTO `t_lecturer` VALUES ('3', '于彦伟', '胖乎乎可可爱爱');
INSERT INTO `t_lecturer` VALUES ('4', '马慧', '大学老师是老师吗？');

-- ----------------------------
-- Table structure for t_course
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `lecturer_id` int NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `lecturer_id` FOREIGN KEY (`lecturer_id`) REFERENCES `t_lecturer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
);

-- ----------------------------
-- Records of t_course
-- ----------------------------
INSERT INTO `t_course` VALUES ('1', '移动软件开发', '1');
INSERT INTO `t_course` VALUES ('2', '概率论', '2');
INSERT INTO `t_course` VALUES ('3', '离散数学I', '3');
INSERT INTO `t_course` VALUES ('4', '离散数学II', '3');
INSERT INTO `t_course` VALUES ('5', '数据库', '3');
INSERT INTO `t_course` VALUES ('6', '离散数学I', '4');
INSERT INTO `t_course` VALUES ('7', '离散数学II', '4');

-- ----------------------------
-- Table structure for t_lecturer_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_lecturer_comment`;
CREATE TABLE `t_lecturer_comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `lecturer_id` int NOT NULL,
  `content` varchar(255) NOT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `lecturer` FOREIGN KEY (`lecturer_id`) REFERENCES `t_lecturer` (`id`),
  CONSTRAINT `user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
);

-- ----------------------------
-- Records of t_lecturer_comment
-- ----------------------------
INSERT INTO `t_lecturer_comment` VALUES ('1', '1', '1', '人称高哥', '2021-05-10 15:44:38');
INSERT INTO `t_lecturer_comment` VALUES ('2', '1', '3', '于老师胖乎乎可可爱爱', '2021-05-09 15:44:42');
INSERT INTO `t_lecturer_comment` VALUES ('3', '1', '2', '口头禅是“老板”（？）的青年老师', '2021-05-10 15:44:49');


-- ----------------------------
-- Table structure for t_course_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_course_comment`;
CREATE TABLE `t_course_comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `course_id` int NOT NULL,
  `content` varchar(255) NOT NULL,
  `time` datetime DEFAULT NULL,
  `creditability` int NOT NULL,
  `votes` int NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `course_id` FOREIGN KEY (`course_id`) REFERENCES `t_course` (`id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
);

-- ----------------------------
-- Records of t_course_comment
-- ----------------------------
INSERT INTO `t_course_comment` VALUES ('1', '1', '1', '上课惬意', '2021-05-10 15:44:38', '0000000000', '0000000000');
INSERT INTO `t_course_comment` VALUES ('2', '1', '1', '高老师比较教师和善', '2021-05-09 15:44:42', '0000000000', '0000000000');
INSERT INTO `t_course_comment` VALUES ('3', '2', '3', '于老师有时候作业比较足量', '2021-05-10 15:44:49', '0000000000', '0000000000');
INSERT INTO `t_course_comment` VALUES ('4', '2', '6', '马慧老师优质课程', null, '0000000000', '0000000000');
