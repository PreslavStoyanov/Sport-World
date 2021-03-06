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
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (47,'This was a great game!','2022-06-20 16:14:55',1,8),(48,'Strahoten gol\n','2022-06-20 20:25:00',1,8);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `leagues`
--

LOCK TABLES `leagues` WRITE;
/*!40000 ALTER TABLE `leagues` DISABLE KEYS */;
INSERT INTO `leagues` VALUES (1,'La Liga'),(2,'Bundesliga'),(3,'Premier League'),(4,'Seria A'),(5,'League 1'),(6,'EfBet League');
/*!40000 ALTER TABLE `leagues` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `mail_tokens`
--

LOCK TABLES `mail_tokens` WRITE;
/*!40000 ALTER TABLE `mail_tokens` DISABLE KEYS */;
INSERT INTO `mail_tokens` VALUES (7,'11218',6);
/*!40000 ALTER TABLE `mail_tokens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `matches`
--

LOCK TABLES `matches` WRITE;
/*!40000 ALTER TABLE `matches` DISABLE KEYS */;
INSERT INTO `matches` VALUES (3,'Levski VS CSKA','1:1','2022-06-20 13:09:55',6,1),(4,'FC Bayern Munchen VS Borusia Dortmund','4:1','2022-06-20 13:11:04',2,1),(5,'FC Schalke 04 VS Hamburger SV','2:1','2022-06-20 13:12:39',2,1),(6,'Bayer 04 Leverkusen VS FSV Mainz 05','1:2','2022-06-20 13:13:36',2,1),(7,'RB Leipzig VS FC K??ln','1:2','2022-06-20 13:15:02',2,1),(8,'Barcelona VS Real Madrid','2:2','2022-06-20 14:39:10',1,1),(9,'Celta Vigo VS Sevilla','3:2','2022-06-20 14:39:59',1,1),(10,'Granada VS Getafe','3:2','2022-06-20 14:40:39',1,1),(11,'Cadiz VS Elche','3:2','2022-06-20 14:40:59',1,1),(12,'Espanyol VS Rayo Vallecano','3:2','2022-06-20 14:41:19',1,1),(13,'Valencia VS Villarreal','3:2','2022-06-20 14:41:32',1,1),(14,'Real Betis VS Real Sociedad','3:2','2022-06-20 14:42:19',1,1),(15,'Atl??tico Madrid VS Athletic Bilbao','3:2','2022-06-20 14:42:44',1,1),(16,'Atalanta VS Bologna','0:0','2022-06-20 14:47:02',4,1),(17,'Cremonese VS Empoli','3:0','2022-06-20 14:47:16',4,1),(18,'Fiorentina VS Juventus','1:4','2022-06-20 14:47:36',4,1),(19,'Inter Milan VS Milan','5:4','2022-06-20 14:47:48',4,1),(20,'Monza VS Napoli','1:4','2022-06-20 14:48:07',4,1),(21,'Roma VS Sassuolo','1:2','2022-06-20 14:48:23',4,1),(22,'Paris Saint-Germain VS Lille','4:2','2022-06-20 14:52:58',5,1),(23,'Lens VS Clermont','0:0','2022-06-20 14:53:54',5,1),(24,'Montpelliera VS Reims','0:0','2022-06-20 14:54:16',5,1),(25,'Strasbourg VS Lyon','0:1','2022-06-20 14:54:42',5,1),(26,'Botev Plovdiv VS Lokomotiv Plovdiv','0:1','2022-06-20 15:43:15',6,1),(27,'Beroe VS Arda','5:1','2022-06-20 15:43:55',6,1),(28,'Slavia VS Pirin','3:3','2022-06-20 15:56:13',6,1),(29,'Arsenal VS Fulham','3:3','2022-06-20 16:01:37',3,1),(30,'Leicester City VS Newcastle United','3:1','2022-06-20 16:01:46',3,1),(31,'Chelsea VS Manchester City','2:1','2022-06-20 16:02:11',3,1),(32,'Manchester United VS West Ham United','2:1','2022-06-20 16:02:33',3,1),(33,'Liverpool VS Tottenham Hotspur','3:1','2022-06-20 16:03:08',3,1);
/*!40000 ALTER TABLE `matches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (2,'ADMIN'),(4,'BANNED'),(3,'MODERATOR'),(1,'STANDARD');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_activity_log`
--

LOCK TABLES `user_activity_log` WRITE;
/*!40000 ALTER TABLE `user_activity_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_activity_log` ENABLE KEYS */;
UNLOCK TABLES;

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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-29 14:45:40
