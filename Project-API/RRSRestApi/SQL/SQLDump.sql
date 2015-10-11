CREATE DATABASE  IF NOT EXISTS `egen_proj` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `egen_proj`;
-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: egen_proj
-- ------------------------------------------------------
-- Server version	5.6.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customer_details`
--

DROP TABLE IF EXISTS `customer_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_details` (
  `first_name` varchar(45) NOT NULL COMMENT 'First name of customer',
  `last_name` varchar(45) NOT NULL COMMENT 'Last name of customer',
  `email` varchar(45) NOT NULL COMMENT 'Email of customer',
  `phone` varchar(10) NOT NULL COMMENT 'Phone numeber of customer',
  `rest_id` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_details`
--

LOCK TABLES `customer_details` WRITE;
/*!40000 ALTER TABLE `customer_details` DISABLE KEYS */;
INSERT INTO `customer_details` VALUES ('string','string','string','string',1);
/*!40000 ALTER TABLE `customer_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `owner`
--

DROP TABLE IF EXISTS `owner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `owner` (
  `email` varchar(45) NOT NULL COMMENT 'Email id of Owner',
  `password` varchar(45) NOT NULL COMMENT 'Password for admin access',
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `owner`
--

LOCK TABLES `owner` WRITE;
/*!40000 ALTER TABLE `owner` DISABLE KEYS */;
INSERT INTO `owner` VALUES ('string','string2');
/*!40000 ALTER TABLE `owner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservations` (
  `cust_email` varchar(45) CHARACTER SET dec8 NOT NULL COMMENT 'Email Id of customer for this reservation',
  `datetime` varchar(45) NOT NULL COMMENT 'Date of reservation',
  `party_size` int(11) NOT NULL COMMENT 'Party size for this reservation',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT 'Status of reservation (Success or Waiting)',
  `table_id` int(11) NOT NULL DEFAULT '-1' COMMENT 'Table number assigned for this reservation',
  `conf_no` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Unique confirmation number for this reservation',
  `rest_id` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`conf_no`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
INSERT INTO `reservations` VALUES ('string','2015-10-11-10-00',2,1,-1,1,1),('string','2015-10-11-10-00',4,1,-1,2,1),('string','2015-10-11-10-00',2,1,-1,3,1),('string','2015-10-11-10-00',2,1,-1,4,1),('string','2015-10-11-10-00',2,0,-1,5,1),('string','2015-10-11-10-00',2,1,12,6,1);
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant_details`
--

DROP TABLE IF EXISTS `restaurant_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant_details` (
  `name` varchar(45) NOT NULL COMMENT 'Restaurant Name',
  `open_time` varchar(45) NOT NULL COMMENT 'Restaurant opening time',
  `close_time` varchar(45) NOT NULL COMMENT 'Restaurant closing time',
  `address1` varchar(45) NOT NULL COMMENT 'Restaurant address',
  `address2` varchar(45) NOT NULL COMMENT 'Restaurant address',
  `city` varchar(45) NOT NULL COMMENT 'Restaurant is located in this city',
  `state` varchar(45) NOT NULL COMMENT 'Restaurant is located in this state',
  `zip` int(11) NOT NULL COMMENT 'Zip code of the state in which Restaurant is located',
  `email` varchar(45) NOT NULL COMMENT 'Email id to contact Restaurant',
  `phone` varchar(10) NOT NULL COMMENT 'Phone number of Restaurant ',
  `table_1` int(11) NOT NULL COMMENT 'Number of tables with capacity 1 in this Restaurant',
  `table_2` int(11) NOT NULL COMMENT 'Number of tables with capacity 2 in this Restaurant',
  `table_4` int(11) NOT NULL COMMENT 'Number of tables with capacity 4 in this Restaurant',
  `table_6` int(11) NOT NULL COMMENT 'Number of tables with capacity 6 in this Restaurant',
  `table_8` int(11) NOT NULL COMMENT 'Number of tables with capacity 8 in this Restaurant',
  `auto_assign` int(11) NOT NULL DEFAULT '1' COMMENT 'Can a table be autoassigned to customer when he reserves',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_details`
--

LOCK TABLES `restaurant_details` WRITE;
/*!40000 ALTER TABLE `restaurant_details` DISABLE KEYS */;
INSERT INTO `restaurant_details` VALUES ('string','2015-10-11-8-00','2015-10-11-20-00','string','string','string','string',0,'string','string',2,4,2,1,0,1,1);
/*!40000 ALTER TABLE `restaurant_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant_tables`
--

DROP TABLE IF EXISTS `restaurant_tables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant_tables` (
  `table_id` int(11) NOT NULL AUTO_INCREMENT,
  `table_size` int(11) NOT NULL,
  `rest_id` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_tables`
--

LOCK TABLES `restaurant_tables` WRITE;
/*!40000 ALTER TABLE `restaurant_tables` DISABLE KEYS */;
INSERT INTO `restaurant_tables` VALUES (10,1,1),(11,1,1),(12,2,1),(13,2,1),(14,2,1),(15,2,1),(16,4,1),(17,4,1),(18,6,1);
/*!40000 ALTER TABLE `restaurant_tables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tables_assigned`
--

DROP TABLE IF EXISTS `tables_assigned`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tables_assigned` (
  `table_size` int(11) NOT NULL COMMENT 'Size of table',
  `conf_no` int(11) NOT NULL COMMENT 'Reservation confirmation number',
  `is_tentative` int(11) NOT NULL COMMENT 'Is table assigned tentatively',
  `datetime` varchar(45) NOT NULL COMMENT 'Date of reseravation',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID auto incremented value as primary key',
  `table_id` int(11) NOT NULL DEFAULT '-1' COMMENT 'Table number',
  `over_assigned` int(11) NOT NULL DEFAULT '0' COMMENT 'Over assigned is a state when a table size assigned for the party is larger than the party size. Like assigning a table of size 8 for a pary of size 4 (Even if they are tentative). ',
  `rest_id` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tables_assigned`
--

LOCK TABLES `tables_assigned` WRITE;
/*!40000 ALTER TABLE `tables_assigned` DISABLE KEYS */;
INSERT INTO `tables_assigned` VALUES (2,6,0,'2015-10-11-10-00',1,12,0,1);
/*!40000 ALTER TABLE `tables_assigned` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-10 21:32:44
