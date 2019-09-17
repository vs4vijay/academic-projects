-- MySQL dump 10.13  Distrib 5.1.50, for Win32 (ia32)
--
-- Host: localhost    Database: metaboard
-- ------------------------------------------------------
-- Server version	5.1.50-community

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
-- Table structure for table `group_notification`
--

DROP TABLE IF EXISTS `group_notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_notification` (
  `group_id` int(11) DEFAULT NULL,
  `n_id` int(11) DEFAULT NULL,
  KEY `foreign_key01` (`n_id`),
  KEY `foreign_key02` (`group_id`),
  CONSTRAINT `group_notification_ibfk_1` FOREIGN KEY (`n_id`) REFERENCES `notifications` (`n_id`) ON DELETE CASCADE,
  CONSTRAINT `group_notification_ibfk_2` FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_notification`
--

LOCK TABLES `group_notification` WRITE;
/*!40000 ALTER TABLE `group_notification` DISABLE KEYS */;
INSERT INTO `group_notification` VALUES (6,1),(6,2),(6,3),(6,4),(3,5),(6,6),(6,7),(6,8),(6,9),(6,10),(6,11),(6,12),(6,13),(6,14);
/*!40000 ALTER TABLE `group_notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` VALUES (1,'HR'),(2,'Accounts'),(3,'Java'),(4,'.NET'),(5,'SalesForce'),(6,'Public'),(7,'QA'),(8,'GET 2012');
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_category`
--

DROP TABLE IF EXISTS `member_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_category` (
  `cat_id` int(11) NOT NULL AUTO_INCREMENT,
  `cat_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_category`
--

LOCK TABLES `member_category` WRITE;
/*!40000 ALTER TABLE `member_category` DISABLE KEYS */;
INSERT INTO `member_category` VALUES (1,'Admin'),(2,'Others');
/*!40000 ALTER TABLE `member_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `members` (
  `m_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `email_id` varchar(40) DEFAULT NULL,
  `contact` varchar(15) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  `cat_id` int(11) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  PRIMARY KEY (`m_id`),
  KEY `cat_id` (`cat_id`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `cat_id` FOREIGN KEY (`cat_id`) REFERENCES `member_category` (`cat_id`),
  CONSTRAINT `group_id` FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`),
  CONSTRAINT `mycccc` FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `myconcon` FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`) ON UPDATE CASCADE,
  CONSTRAINT `myconcon1` FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`) ON DELETE CASCADE,
  CONSTRAINT `myconcon12` FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`) ON UPDATE CASCADE,
  CONSTRAINT `myconcon13` FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `myconconcon` FOREIGN KEY (`cat_id`) REFERENCES `member_category` (`cat_id`) ON UPDATE CASCADE,
  CONSTRAINT `myconconcon1` FOREIGN KEY (`cat_id`) REFERENCES `member_category` (`cat_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES (1,'Ashish','ashish.sharma@metagurukul.com','9602181180',1,1,'2012-11-11'),(2,'Vijay','vijay.soni@metagurukul.com','9166276700',2,1,'2012-11-11'),(3,'Satya','satya.chaudhary@metagurukul.com','9928202366',3,2,'2012-11-11'),(4,'Ankita','ankita.jain@metagurukul.com','9999999999',4,1,'2012-11-11'),(5,'Ekta','ekta.sharma@metagurukul.com','7597507466',5,2,'2012-11-11'),(6,'Mohit','mohit.chawla@metagurukul.com','7597028680',3,1,'2012-11-11'),(7,'Himanshu','himanshu.jain@metagurukul.com','9460681079',4,2,'2012-10-12'),(8,'Naveen','naveen.sharma@metagurukul.com','9869543678',5,1,'2012-10-12'),(9,'Vikas','vikas.menon@metagurukul.com','7737695583',4,2,'2012-10-12'),(10,'Jinesh','jinesh.goyal@metagurukul.com','9785791283',5,1,'2012-10-12'),(14,'satyanarayan choudhary','satya.mca2012@gmail.com','9928202366',3,2,'2012-10-15'),(15,'Satyanarayan Choudhary','satyanarayan.choudhary@metagurukul.com','9928202366',3,1,'2012-10-23'),(16,'Numb Er','numbe3r@gmail.com','9254687405',3,2,'2012-10-22'),(19,'camera astrology','coincidence137@gmail.com','9928202366',1,2,'2012-10-16'),(20,'Ashish','ashish.sharma@metagurukul.com','9602181180',1,1,NULL),(21,'Ashish','ashish.sharma@metagurukul.com','9602181180',1,1,NULL),(22,'Ashish','ashish.sharma@metagurukul.com','9602181180',1,1,NULL),(23,'Ashish','ashish.sharma@metagurukul.com','9602181180',1,1,NULL),(24,'Sandeep Kumar','sandeep.kumar1@metagurukul.com','9999999999',3,2,'2002-10-20'),(29,'satyanarayan choudhary','satya.bangalor@gmail.com','9928202366',3,2,'2012-10-16'),(30,'satyanarayan choudhary','emailidofsatya@gmail.com','9928202366',3,2,'2012-10-16'),(31,'Barkha Jain','barkha.jain@metagurukul.com','9874563214',3,2,'2002-04-30'),(32,'Rekha Jangir','rekha.jangir@metagurukul.com','9887562131',5,2,'2002-01-06');
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notifications` (
  `n_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `description` text,
  `creator_id` int(11) DEFAULT NULL,
  `posted_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `section_id` int(11) DEFAULT NULL,
  `expiry_date` timestamp NOT NULL DEFAULT '2020-01-01 01:01:01',
  `archived` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`n_id`),
  KEY `creator_id` (`creator_id`),
  KEY `section_id` (`section_id`),
  CONSTRAINT `creator_id` FOREIGN KEY (`creator_id`) REFERENCES `members` (`m_id`),
  CONSTRAINT `mcon` FOREIGN KEY (`creator_id`) REFERENCES `members` (`m_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `mycccc1` FOREIGN KEY (`creator_id`) REFERENCES `members` (`m_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `section_id` FOREIGN KEY (`section_id`) REFERENCES `section_type` (`section_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (1,'Android Seminar','Seminar on Android at 3:00 PM',2,'2012-10-17 07:31:19',1,'2012-10-29 18:30:00',0),(2,'Workshop','Workshop',2,'2012-10-17 07:31:57',1,'2012-10-23 18:30:00',0),(3,'Project Submission','Project Submission Date Extended',2,'2012-10-17 07:32:42',1,'2012-10-19 18:30:00',0),(4,'MPL','MPL will be held',2,'2012-10-17 07:34:42',10,'2012-10-24 18:30:00',0),(5,'jQuery Workshop','jQuery Workshop',31,'2012-10-17 07:37:52',1,'2012-10-21 18:30:00',0),(6,'TT Singles','TT Singles',31,'2012-10-17 07:38:40',10,'2012-10-20 18:30:00',0),(7,'Happy Birthday','Happy Birthday to Vijay Soni',31,'2012-10-17 07:39:31',3,'2012-10-22 18:30:00',0),(8,'Congrats','Congrats to vijay soni \r\nfor winning in Online Competition',2,'2012-10-17 08:49:12',2,'2012-10-17 18:30:00',0),(9,'Happy Birthday','Happy Birthday to Ankita',2,'2012-10-17 07:50:05',3,'2012-10-23 18:30:00',0),(10,'Project Evaluation','Project Evaluation',2,'2012-10-17 07:50:50',1,'2012-10-22 18:30:00',0),(11,'Dance Practice','Dance Practice at 4PM',32,'2012-10-17 08:52:26',1,'2012-10-20 18:30:00',0),(12,'Congrats','Congrats to Ekta for winning Jalebi Race..',32,'2012-10-17 08:54:28',2,'2012-10-21 18:30:00',0),(13,'Congrats','Congrats to Satyanarayan for New Phone',32,'2012-10-17 08:55:27',2,'2012-10-24 18:30:00',0),(14,'Volleyball','Volleyball match at ground',32,'2012-10-17 08:56:17',10,'2012-10-23 18:30:00',0);
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section_type`
--

DROP TABLE IF EXISTS `section_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `section_type` (
  `section_id` int(11) NOT NULL AUTO_INCREMENT,
  `section_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`section_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section_type`
--

LOCK TABLES `section_type` WRITE;
/*!40000 ALTER TABLE `section_type` DISABLE KEYS */;
INSERT INTO `section_type` VALUES (1,'Announcement'),(2,'Praise'),(3,'Wishes'),(10,'Events');
/*!40000 ALTER TABLE `section_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-10-17 14:27:53
