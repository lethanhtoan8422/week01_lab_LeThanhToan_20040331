-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               11.1.2-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for databaselab01
CREATE DATABASE IF NOT EXISTS `databaselab01` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `databaselab01`;

-- Dumping structure for table databaselab01.account
CREATE TABLE IF NOT EXISTS `account` (
  `account_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table databaselab01.account: ~8 rows (approximately)
INSERT INTO `account` (`account_id`, `email`, `full_name`, `password`, `phone`, `status`) VALUES
	(1, 'admin@gmail.com', 'ADMIN', 'admin', '0329623381', 1),
	(2, 'email1@gmail.com', 'Full Name 1', 'password1', '0329623382', 1),
	(3, 'email2@gmail.com', 'Full Name 2', 'password2', '0329623383', 1),
	(4, 'email3@gmail.com', 'Full Name 3', 'password3', '0329623384', -1),
	(5, 'email5@gmail.com', 'Full Name 5', 'password5', '0329623386', -1),
	(6, 'email4@gmail.com', 'Full Name 4', 'password4', '0329623385', -1),
	(15, 'email5update@gmail.com', 'Full Name 5 update', 'password5update', '0329623387', -1),
	(16, 'email4@gmail.com', 'Full Name 4', 'password4', '0329623385', 1);

-- Dumping structure for table databaselab01.grant_access
CREATE TABLE IF NOT EXISTS `grant_access` (
  `is_grant` int(11) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `account_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`account_id`,`role_id`),
  KEY `FKn8vuhdmux6pxjr90wu37ppej0` (`role_id`),
  CONSTRAINT `FK6d5l1bbvt3fbq9wtn94s22hxq` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`),
  CONSTRAINT `FKn8vuhdmux6pxjr90wu37ppej0` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table databaselab01.grant_access: ~7 rows (approximately)
INSERT INTO `grant_access` (`is_grant`, `note`, `account_id`, `role_id`) VALUES
	(1, 'note 1', 1, 1),
	(1, 'note 2', 1, 2),
	(1, 'note 5', 1, 3),
	(1, 'note 3', 2, 1),
	(1, 'note 4', 2, 3),
	(1, 'note 6', 3, 1),
	(0, 'Xem Thong Tin Account Va Role', 4, 1);

-- Dumping structure for table databaselab01.log
CREATE TABLE IF NOT EXISTS `log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `logIn` datetime(6) DEFAULT NULL,
  `logOut` datetime(6) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`log_id`),
  KEY `FK503ama154cr4d3cyc7741l4b7` (`account_id`),
  CONSTRAINT `FK503ama154cr4d3cyc7741l4b7` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table databaselab01.log: ~22 rows (approximately)
INSERT INTO `log` (`log_id`, `logIn`, `logOut`, `note`, `account_id`) VALUES
	(27, '2023-11-04 21:36:56.274278', '2023-11-04 21:41:17.886210', 'note', 1),
	(28, '2023-11-19 14:17:46.554727', '2023-11-19 14:18:02.301895', 'note', 1),
	(29, '2023-11-19 14:18:50.768084', '2023-11-19 14:19:13.779073', 'note', 2),
	(30, '2023-11-19 14:19:17.832077', '2023-11-19 14:20:01.517206', 'note', 1),
	(31, '2023-11-19 14:21:24.954889', NULL, 'note', 1),
	(32, '2023-11-19 14:46:00.865684', NULL, 'note', 1),
	(33, '2023-11-19 14:47:00.769678', NULL, 'note', 1),
	(34, '2023-11-19 14:49:39.421948', NULL, 'note', 1),
	(35, '2023-11-19 14:52:18.326607', NULL, 'note', 1),
	(36, '2023-11-19 14:54:39.488169', NULL, 'note', 1),
	(37, '2023-11-19 15:08:24.995875', NULL, 'note', 1),
	(38, '2023-11-19 15:11:49.594026', NULL, 'note', 1),
	(39, '2023-11-19 15:13:49.436962', NULL, 'note', 1),
	(40, '2023-11-19 15:15:20.849862', NULL, 'note', 1),
	(41, '2023-11-19 15:19:22.929151', NULL, 'note', 1),
	(42, '2023-11-19 15:21:39.568064', NULL, 'note', 1),
	(43, '2023-11-19 15:23:12.003017', NULL, 'note', 1),
	(44, '2023-11-19 15:25:16.253529', NULL, 'note', 1),
	(45, '2023-11-19 15:25:53.401093', NULL, 'note', 1),
	(46, '2023-11-19 15:29:03.800423', NULL, 'note', 1),
	(47, '2023-11-19 15:31:02.266457', NULL, 'note', 1),
	(48, '2023-11-19 15:31:30.558822', NULL, 'note', 1),
	(49, '2023-11-19 15:33:04.310286', NULL, 'note', 1);

-- Dumping structure for table databaselab01.role
CREATE TABLE IF NOT EXISTS `role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table databaselab01.role: ~4 rows (approximately)
INSERT INTO `role` (`role_id`, `description`, `role_name`, `status`) VALUES
	(1, 'Xem Thong Tin Cac Bang', 'Xem Thong Tin', 1),
	(2, 'Them, Sua, Xoa, Tim Kiem', 'CRUD', 1),
	(3, 'Xem Thong Tin Roles', 'Xem Thong Tin Roles', 1),
	(5, 'Cập Nhật Thông Tin', 'Cập Nhật Thông Tin', -1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
