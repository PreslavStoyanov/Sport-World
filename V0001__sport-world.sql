-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: sport_world
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Temporary view structure for view `banned_users`
--

DROP TABLE IF EXISTS `banned_users`;
/*!50001 DROP VIEW IF EXISTS `banned_users`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `banned_users` AS SELECT 
 1 AS `UserID`,
 1 AS `Username`,
 1 AS `Email`,
 1 AS `Status`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `creation_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `user_id` int NOT NULL,
  `match_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `content_UNIQUE` (`content`),
  KEY `user_id` (`user_id`),
  KEY `match_id` (`match_id`),
  CONSTRAINT `comment_match_id_conection` FOREIGN KEY (`match_id`) REFERENCES `matches` (`id`) ON DELETE CASCADE,
  CONSTRAINT `comment_user_id_conection` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leagues`
--

DROP TABLE IF EXISTS `leagues`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `leagues` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leagues`
--

LOCK TABLES `leagues` WRITE;
/*!40000 ALTER TABLE `leagues` DISABLE KEYS */;
INSERT INTO `leagues` VALUES (1,'La Liga'),(2,'Bundesliga'),(3,'Premier League'),(4,'Seria A'),(5,'League 1'),(6,'EfBet League');
/*!40000 ALTER TABLE `leagues` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mail_tokens`
--

DROP TABLE IF EXISTS `mail_tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mail_tokens` (
  `id` int NOT NULL AUTO_INCREMENT,
  `token` varchar(45) NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_mail_token_conection_idx` (`user_id`),
  CONSTRAINT `user_mail_token_conection` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mail_tokens`
--

LOCK TABLES `mail_tokens` WRITE;
/*!40000 ALTER TABLE `mail_tokens` DISABLE KEYS */;
/*!40000 ALTER TABLE `mail_tokens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matches`
--

DROP TABLE IF EXISTS `matches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `matches` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `content` varchar(255) NOT NULL,
  `creation_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `league_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ligue_id` (`league_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `league` FOREIGN KEY (`league_id`) REFERENCES `leagues` (`id`),
  CONSTRAINT `user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matches`
--

LOCK TABLES `matches` WRITE;
/*!40000 ALTER TABLE `matches` DISABLE KEYS */;
INSERT INTO `matches` VALUES (3,'Levski VS CSKA','1:1','2022-06-20 13:09:55',6,1),(4,'FC Bayern Munchen VS Borusia Dortmund','4:1','2022-06-20 13:11:04',2,1),(5,'FC Schalke 04 VS Hamburger SV','2:1','2022-06-20 13:12:39',2,1),(6,'Bayer 04 Leverkusen VS FSV Mainz 05','1:2','2022-06-20 13:13:36',2,1),(7,'RB Leipzig VS FC Köln','1:2','2022-06-20 13:15:02',2,1),(8,'Barcelona VS Real Madrid','2:2','2022-06-20 14:39:10',1,1),(9,'Celta Vigo VS Sevilla','3:2','2022-06-20 14:39:59',1,1),(10,'Granada VS Getafe','3:2','2022-06-20 14:40:39',1,1),(11,'Cadiz VS Elche','3:2','2022-06-20 14:40:59',1,1),(12,'Espanyol VS Rayo Vallecano','3:2','2022-06-20 14:41:19',1,1),(13,'Valencia VS Villarreal','3:2','2022-06-20 14:41:32',1,1),(14,'Real Betis VS Real Sociedad','3:2','2022-06-20 14:42:19',1,1),(15,'Atlético Madrid VS Athletic Bilbao','3:2','2022-06-20 14:42:44',1,1),(16,'Atalanta VS Bologna','0:0','2022-06-20 14:47:02',4,1),(17,'Cremonese VS Empoli','3:0','2022-06-20 14:47:16',4,1),(18,'Fiorentina VS Juventus','1:4','2022-06-20 14:47:36',4,1),(19,'Inter Milan VS Milan','5:4','2022-06-20 14:47:48',4,1),(20,'Monza VS Napoli','1:4','2022-06-20 14:48:07',4,1),(21,'Roma VS Sassuolo','1:2','2022-06-20 14:48:23',4,1),(22,'Paris Saint-Germain VS Lille','4:2','2022-06-20 14:52:58',5,1),(23,'Lens VS Clermont','0:0','2022-06-20 14:53:54',5,1),(24,'Montpelliera VS Reims','0:0','2022-06-20 14:54:16',5,1),(25,'Strasbourg VS Lyon','0:1','2022-06-20 14:54:42',5,1),(26,'Botev Plovdiv VS Lokomotiv Plovdiv','0:1','2022-06-20 15:43:15',6,1),(27,'Beroe VS Arda','5:1','2022-06-20 15:43:55',6,1),(28,'Slavia VS Pirin','3:3','2022-06-20 15:56:13',6,1),(29,'Arsenal VS Fulham','3:3','2022-06-20 16:01:37',3,1),(30,'Leicester City VS Newcastle United','3:1','2022-06-20 16:01:46',3,1),(31,'Chelsea VS Manchester City','2:1','2022-06-20 16:02:11',3,1),(32,'Manchester United VS West Ham United','2:1','2022-06-20 16:02:33',3,1),(33,'Liverpool VS Tottenham Hotspur','3:1','2022-06-20 16:03:08',3,1);
/*!40000 ALTER TABLE `matches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `moderators`
--

DROP TABLE IF EXISTS `moderators`;
/*!50001 DROP VIEW IF EXISTS `moderators`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `moderators` AS SELECT 
 1 AS `UserID`,
 1 AS `Username`,
 1 AS `Email`,
 1 AS `Status`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `recent_posts`
--

DROP TABLE IF EXISTS `recent_posts`;
/*!50001 DROP VIEW IF EXISTS `recent_posts`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `recent_posts` AS SELECT 
 1 AS `id`,
 1 AS `title`,
 1 AS `content`,
 1 AS `creation_date`,
 1 AS `league_id`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (2,'ADMIN'),(4,'BANNED'),(3,'MODERATOR'),(1,'STANDARD');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_activity_log`
--

DROP TABLE IF EXISTS `user_activity_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_activity_log` (
  `user_id` int DEFAULT NULL,
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  `activity` varchar(255) DEFAULT NULL,
  KEY `user_id_connection_idx` (`user_id`),
  CONSTRAINT `activity_log_user_id_connection` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_activity_log`
--

LOCK TABLES `user_activity_log` WRITE;
/*!40000 ALTER TABLE `user_activity_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_activity_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone_number` varchar(10) DEFAULT NULL,
  `registration_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `salt` varchar(255) DEFAULT NULL,
  `role_id` int DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`username`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE,
  CONSTRAINT `check_phone_number_length` CHECK ((length(`phone_number`) = 10)),
  CONSTRAINT `check_username_length` CHECK ((length(`username`) >= 6)),
  CONSTRAINT `check_valid_email` CHECK (regexp_like(`email`,_utf8mb4'^[a-zA-Z0-9][a-zA-Z0-9.!#$%&\'*+-/=?^_`{|}~]*?[a-zA-Z0-9._-]?@[a-zA-Z0-9][a-zA-Z0-9._-]*?[a-zA-Z0-9]?.[a-zA-Z]{2,63}$'))
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Preslav','34c0918b8e6e9f12ca2a8ecfd213011b29cfe9d2601bb870c20f51fc2a3ea946','pres@gmail.com',NULL,'2022-06-15 19:49:44','VeqzfePhXlKNOLZ+ENromHTJLvs=',2),(2,'Viktor','5c75578fc0c56715c07fc39b68405b5e456c6dfee0c0ded50bc2bf5dbdf82536','viksata@gmail.com',NULL,'2022-06-15 19:50:22','Tp9lMQ7QQ/e8AhzvI0IMKf3EAD4=',1),(5,'Veskoo','8e5211004899411da1e2ba39c729a26400c42a8880d659c4cf32a2cc47c3e8f9','vesko@abv.bg',NULL,'2022-06-18 18:51:31','0RNSSEP3yYc5pSJUrUB4S3LuvtQ=',1),(6,'presli','dcce728e2314b9c68f6787b7bfbd759aaba81c189b07f8861e3b609ffb540df1','preslav0609@gmail.com',NULL,'2022-06-19 17:17:18','aZFE2yDDtkjCHc+MZ+f6g/xvb5g=',1),(7,'angelcho','8e8a5bfcb1f49e371da283a5a49e58ca81d6730b3d0f455b9a77abf0415da01e','angelbohosyan@gmail.com',NULL,'2022-06-19 20:18:27','BT1ysRLUoQuthimCaaYcMCfYkm4=',1),(8,'georgi','15d11022e67140a76582afc9e11f0f7fa46fbfee6fa14ba3bb03b1df82802ea3','gosho@gmail.com',NULL,'2022-06-20 11:01:00','8VrwZ7cYXtSGg/+S/vlPu6kU3Yw=',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'sport_world'
--
/*!50003 DROP PROCEDURE IF EXISTS `drop_tables` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `drop_tables`()
BEGIN
drop table comments;
drop table matches;
drop table leagues;
drop table user_activity_log;
drop table users;
drop table roles;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `banned_users`
--

/*!50001 DROP VIEW IF EXISTS `banned_users`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `banned_users` AS select `users`.`id` AS `UserID`,`users`.`username` AS `Username`,`users`.`email` AS `Email`,`roles`.`name` AS `Status` from (`users` join `roles` on((`users`.`role_id` = `roles`.`id`))) where (`users`.`role_id` = 4) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `moderators`
--

/*!50001 DROP VIEW IF EXISTS `moderators`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `moderators` AS select `users`.`id` AS `UserID`,`users`.`username` AS `Username`,`users`.`email` AS `Email`,`roles`.`name` AS `Status` from (`users` join `roles` on((`users`.`role_id` = `roles`.`id`))) where (`users`.`role_id` = 3) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `recent_posts`
--

/*!50001 DROP VIEW IF EXISTS `recent_posts`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `recent_posts` AS select `m`.`id` AS `id`,`m`.`title` AS `title`,`m`.`content` AS `content`,`m`.`creation_date` AS `creation_date`,`m`.`league_id` AS `league_id` from `matches` `m` where (`m`.`creation_date` > (curdate() - interval 7 day)) order by `m`.`creation_date` limit 20 */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-20 16:06:13
