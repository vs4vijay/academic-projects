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
INSERT INTO `group_notification` VALUES (6,14),(4,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(3,23),(1,24),(1,25),(1,26),(1,27),(1,28),(6,30),(3,32),(6,33),(5,34),(3,36),(6,37),(6,38),(6,39),(6,40),(6,42),(1,43),(2,43),(3,43),(4,43),(5,43),(7,43),(3,44),(3,52),(3,53),(6,54),(6,58),(6,59),(6,61),(6,65),(6,71),(6,72),(6,73),(1,82),(4,83),(1,84),(4,85),(1,86),(4,87),(1,88),(4,89),(6,92),(6,97),(3,117),(6,118);
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
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
  CONSTRAINT `myconcon` FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`) ON UPDATE CASCADE,
  CONSTRAINT `myconcon1` FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`) ON DELETE CASCADE,
  CONSTRAINT `myconcon12` FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`) ON UPDATE CASCADE,
  CONSTRAINT `myconcon13` FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `myconconcon` FOREIGN KEY (`cat_id`) REFERENCES `member_category` (`cat_id`) ON UPDATE CASCADE,
  CONSTRAINT `myconconcon1` FOREIGN KEY (`cat_id`) REFERENCES `member_category` (`cat_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES (1,'Ashish','ashish.sharma@metagurukul.com','9602181180',1,1,'2012-11-11'),(2,'Vijay','vijay.soni@metagurukul.com','9166276700',2,1,'2012-11-11'),(3,'Satya','satya.chaudhary@metagurukul.com','9928202366',3,2,'2012-11-11'),(4,'Ankita','ankita.jain@metagurukul.com','9999999999',4,1,'2012-11-11'),(5,'Ekta','ekta.sharma@metagurukul.com','7597507466',5,2,'2012-11-11'),(6,'Mohit','mohit.chawla@metagurukul.com','7597028680',3,1,'2012-11-11'),(7,'Himanshu','himanshu.jain@metagurukul.com','9460681079',4,2,'2012-10-12'),(8,'Naveen','naveen.sharma@metagurukul.com','9869543678',5,1,'2012-10-12'),(9,'Vikas','vikas.menon@metagurukul.com','7737695583',4,2,'2012-10-12'),(10,'Jinesh','jinesh.goyal@metagurukul.com','9785791283',5,1,'2012-10-12'),(11,'VizaySoni','vs4vijay@gmail.com','9166276700',3,2,'2012-10-12'),(14,'satyanarayan choudhary','satya.mca2012@gmail.com','9928202366',3,2,'2012-10-15'),(15,'Satyanarayan Choudhary','satyanarayan.choudhary@metagurukul.com','9928202366',3,1,'2012-10-23'),(16,'Numb Er','numbe3r@gmail.com','9254687405',3,2,'2012-10-22'),(19,'camera astrology','coincidence137@gmail.com','9928202366',1,2,'2012-10-16'),(20,'Ashish','ashish.sharma@metagurukul.com','9602181180',1,1,NULL),(21,'Ashish','ashish.sharma@metagurukul.com','9602181180',1,1,NULL),(22,'Ashish','ashish.sharma@metagurukul.com','9602181180',1,1,NULL),(23,'Ashish','ashish.sharma@metagurukul.com','9602181180',1,1,NULL),(24,'Sandeep Kumar','sandeep.kumar1@metagurukul.com','9999999999',3,2,'2002-10-20'),(29,'satyanarayan choudhary','satya.bangalor@gmail.com','9928202366',3,2,'2012-10-16'),(30,'satyanarayan choudhary','emailidofsatya@gmail.com','9928202366',3,2,'2012-10-16');
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
  CONSTRAINT `mcon` FOREIGN KEY (`creator_id`) REFERENCES `members` (`m_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `creator_id` FOREIGN KEY (`creator_id`) REFERENCES `members` (`m_id`),
  CONSTRAINT `section_id` FOREIGN KEY (`section_id`) REFERENCES `section_type` (`section_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=143 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (2,'Advance Java - Final Quiz','Will be taken after Workshop!!',2,'2012-10-16 07:12:23',1,'2012-10-16 07:12:23',1),(3,'Holiday','today will be holiday',3,'2012-10-09 06:24:02',2,'2020-01-01 01:01:01',1),(6,'Holiday','tomorrow will be holiday',6,'2012-10-09 06:24:02',2,'2020-01-01 01:01:01',1),(7,'JDBC Dicussion','Today we will have JDBC session at 3:00 PM',7,'2012-10-09 06:24:02',1,'2020-01-01 01:01:01',0),(8,'Servlet Assignment Uploaded','Last date of submission is 20th September, 2012.',8,'2012-10-09 06:24:02',1,'2020-01-01 01:01:01',1),(9,'Web Programming - Servlet','Today\'s session will start on 3 PM.',9,'2012-10-09 06:24:02',1,'2020-01-01 01:01:01',0),(14,'Workshop','at 3.00 pm',4,'2012-10-12 05:57:44',1,'2012-11-19 19:31:01',0),(15,'Test on Java','at 2.00 pm',4,'2012-10-12 05:58:03',1,'2012-11-19 19:31:01',0),(16,'java','add',1,'2012-10-12 08:01:15',1,'2012-12-12 05:41:11',1),(17,'java','add',1,'2012-10-12 08:01:32',1,'2012-12-12 05:41:11',1),(18,'java','add',1,'2012-10-12 08:01:56',1,'2012-12-12 05:41:11',1),(19,'java','add',1,'2012-10-12 08:02:21',1,'2012-12-12 05:41:11',1),(20,'java','add',1,'2012-10-12 08:02:55',1,'2012-12-12 05:41:11',1),(21,'java','add',1,'2012-10-12 08:04:26',1,'2012-12-12 05:41:11',1),(22,'java','add',1,'2012-10-12 08:04:51',1,'2012-12-12 05:41:11',1),(23,'Seminar','Seminar on Android',11,'2012-10-12 08:04:52',1,'2012-10-10 05:41:11',1),(24,'java','add',1,'2012-10-12 08:09:05',1,'2012-12-12 05:41:11',1),(25,'java','add',1,'2012-10-12 08:11:08',1,'2012-12-12 05:41:11',1),(26,'java','add',1,'2012-10-12 08:11:26',1,'2012-12-12 05:41:11',1),(27,'java','add',1,'2012-10-12 08:11:30',1,'2012-12-12 05:41:11',1),(28,'java','add',1,'2012-10-12 08:11:47',1,'2012-12-12 05:41:11',1),(30,'new','neww',4,'2012-10-12 13:17:53',1,'2012-11-09 19:31:01',1),(32,'Notification','rgergerg',4,'2012-10-12 11:08:13',1,'2012-11-09 19:31:01',0),(33,'cfdgv','sdffdvsdfv',5,'2012-10-15 04:56:00',1,'2012-11-09 19:31:01',1),(34,'fgthgj','lhjkhdsdr',5,'2012-10-15 05:07:22',1,'2012-11-09 19:31:01',1),(36,'Notification test 2','f8uyyjgkjyujkyuk',2,'2012-10-15 06:07:27',1,'2012-11-09 19:31:01',0),(37,'Workshop','dfververv',2,'2012-10-15 06:07:47',1,'2012-11-09 19:31:01',0),(38,'weerada','vdfbdf',2,'2012-10-15 06:23:29',1,'2012-11-09 19:31:01',1),(39,'gjggfgh','gjghjghjk',2,'2012-10-15 06:40:47',1,'2012-11-19 19:31:01',1),(40,'yoyoyoyo','yoyoyoyo',4,'2012-10-15 07:04:56',1,'2012-11-09 19:31:01',1),(41,'iou','dfsd',2,'2012-10-15 06:52:10',1,'2012-10-11 18:30:00',1),(42,'try','yoyoyoyooy',2,'2012-10-15 11:54:13',1,'2012-10-22 18:30:00',0),(43,'sdrgsrgcvc','seregsdrdg',4,'2012-10-15 08:07:15',1,'2012-10-15 18:30:00',1),(44,'staya','dbkdmfbdsjkbf jkbd jkfbdskjf bkjdsfhbkjdshf kjsdhf jkghjkhjk\r\ngvf\r\nsadfsdaf\r\nsadf\r\nasdf\r\nadsf\r\nadsf\r\nasdf\r\nsadfadsfadsfads\r\nfadsfdsafdsa\r\nfdsaf\r\nasdf\r\nadsf\r\ndasf\r\nadsf\r\nasdf\r\nads\r\nfads\r\nfasdfasdfasd\r\nf\r\nasdfasdfasdfasd\r\nfsda\r\nf\r\ngt\r\nb\r\nhgb\r\nhbhnbhyjnjymnuyjmuimj\r\nui\r\nmuimuimsd\r\nfg\r\nsdfgfds',14,'2012-10-15 09:50:18',1,'2012-10-16 18:30:00',0),(52,'mai hu','mai hu',2,'2012-10-15 11:54:33',1,'2012-10-17 18:30:00',0),(53,'zzzzzzzzzzzz','zzzzzzzz',2,'2012-10-15 11:54:53',1,'2012-10-18 18:30:00',0),(54,'agcj','vyhfysdfdsr',2,'2012-10-15 11:55:17',1,'2012-10-15 18:30:00',1),(58,'Project Submission','All Teams,Please do final check-in of your project code and documentation (Deliverable) in google svn by 2pm. Make a zip of final version of project',4,'2012-10-16 05:31:21',1,'2012-10-16 18:30:00',0),(59,'sdfgvxc','cgbcvbcvbgbhgfgfg',5,'2012-10-15 13:14:21',2,'2012-10-30 18:30:00',0),(61,'Advance Java Final Quiz','Will be taken after Workshop!!Enjoy and perform well in Workshop!!!',4,'2012-10-16 05:30:15',1,'2012-10-15 18:30:00',1),(65,'Project evaluation','All Team,Submit your project today ASAP as mentioned earlier.',4,'2012-10-16 05:23:02',1,'2012-10-16 18:30:00',0),(71,'cvgghvcg','dffdhdjmj',19,'2012-10-16 06:02:30',1,'2012-10-16 18:30:00',0),(72,'adddd','addddadddd',2,'2012-10-16 06:02:13',1,'2012-10-16 18:30:00',0),(73,'sas','sasasas',19,'2012-10-16 06:02:58',1,'2012-10-16 18:30:00',0),(82,'java','add',1,'2012-10-16 07:10:11',1,'2012-12-12 05:41:11',1),(83,'Positive Test','Positive Testing of Post!!',2,'2012-10-16 07:10:11',1,'2012-10-16 07:10:11',1),(84,'java','add',1,'2012-10-16 07:10:11',1,'2012-12-12 05:41:11',1),(85,'Positive Test','Positive Testing of Post!!',2,'2012-10-16 07:10:11',1,'2012-10-16 07:10:11',1),(86,'java','add',1,'2012-10-16 07:12:22',1,'2012-12-12 05:41:11',1),(87,'Positive Test','Positive Testing of Post!!',2,'2012-10-16 07:12:22',1,'2012-10-16 07:12:22',1),(88,'java','add',1,'2012-10-16 07:12:23',1,'2012-12-12 05:41:11',1),(89,'Positive Test','Positive Testing of Post!!',2,'2012-10-16 07:12:23',1,'2012-10-16 07:12:23',1),(92,'ghdksfgksdgkjf','ghdksfgksdgkjf',2,'2012-10-16 07:19:33',1,'2012-10-16 18:30:00',0),(97,'sfgfgfgfgfdgfg','fgfgfgfgffddd',15,'2012-10-16 07:52:45',1,'2012-10-16 18:30:00',0),(117,'vijay','safdsafsadfasdf',29,'2012-10-16 08:37:31',1,'2012-10-22 18:30:00',0),(118,'add','dfdfdf',30,'2012-10-16 08:38:31',1,'2012-10-21 18:30:00',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
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

-- Dump completed on 2012-10-16 14:59:47
