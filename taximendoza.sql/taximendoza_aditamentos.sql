-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: taximendoza
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `aditamentos`
--

DROP TABLE IF EXISTS `aditamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aditamentos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `car_id` bigint NOT NULL,
  `delegation_id` bigint NOT NULL,
  `permitholder_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmlon33apfre1uwebxix6vx9dg` (`car_id`),
  KEY `FK1wbom2whdwqbf9xfrf2mciw4` (`delegation_id`),
  KEY `FK77gvh0rscu181k89m0k6ls3wj` (`permitholder_id`),
  CONSTRAINT `FK1wbom2whdwqbf9xfrf2mciw4` FOREIGN KEY (`delegation_id`) REFERENCES `delegations` (`id`),
  CONSTRAINT `FK77gvh0rscu181k89m0k6ls3wj` FOREIGN KEY (`permitholder_id`) REFERENCES `permitholders` (`id`),
  CONSTRAINT `FKmlon33apfre1uwebxix6vx9dg` FOREIGN KEY (`car_id`) REFERENCES `cars` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aditamentos`
--

LOCK TABLES `aditamentos` WRITE;
/*!40000 ALTER TABLE `aditamentos` DISABLE KEYS */;
INSERT INTO `aditamentos` VALUES (1,'12-031',1,2,1),(2,'12-037',2,2,2),(3,'12-040',3,2,3),(4,'1142',4,1,1);
/*!40000 ALTER TABLE `aditamentos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-30 22:14:39
