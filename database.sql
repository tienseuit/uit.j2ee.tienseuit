CREATE DATABASE  IF NOT EXISTS `jpayment` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `jpayment`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: jpayment
-- ------------------------------------------------------
-- Server version	5.7.14

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
-- Table structure for table `Bill`
--

DROP TABLE IF EXISTS `Bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Bill` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `StaffID` int(11) NOT NULL,
  `CustomerID` int(11) DEFAULT '-1',
  `Time` datetime(6) NOT NULL,
  `Subtotal` decimal(18,0) NOT NULL,
  `ShopID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Bill_Shop` (`ShopID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bill`
--

LOCK TABLES `Bill` WRITE;
/*!40000 ALTER TABLE `Bill` DISABLE KEYS */;
/*!40000 ALTER TABLE `Bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BillDetail`
--

DROP TABLE IF EXISTS `BillDetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BillDetail` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BillID` int(11) NOT NULL,
  `ProductID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `Subtotal` decimal(18,0) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_BillDetail_Bill` (`BillID`),
  KEY `FK_BillDetail_Product` (`ProductID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BillDetail`
--

LOCK TABLES `BillDetail` WRITE;
/*!40000 ALTER TABLE `BillDetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `BillDetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Device`
--

DROP TABLE IF EXISTS `Device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Device` (
  `ID` int(11) NOT NULL,
  `Name` varchar(200) CHARACTER SET utf8mb4 NOT NULL,
  `MAC` char(10) CHARACTER SET utf8mb4 NOT NULL,
  `Address` varchar(500) CHARACTER SET utf8mb4 DEFAULT NULL,
  `ShopID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Device_Shop` (`ShopID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Device`
--

LOCK TABLES `Device` WRITE;
/*!40000 ALTER TABLE `Device` DISABLE KEYS */;
/*!40000 ALTER TABLE `Device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PaymentHistory`
--

DROP TABLE IF EXISTS `PaymentHistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PaymentHistory` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FromUserID` int(11) NOT NULL,
  `ToUserID` int(11) NOT NULL,
  `Subtotal` decimal(18,4) NOT NULL,
  `Detail` varchar(500) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_PaymentHistory_User` (`FromUserID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PaymentHistory`
--

LOCK TABLES `PaymentHistory` WRITE;
/*!40000 ALTER TABLE `PaymentHistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `PaymentHistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Product`
--

DROP TABLE IF EXISTS `Product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Product` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(200) CHARACTER SET utf8mb4 NOT NULL,
  `Price` bigint(20) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `CheckCount` tinyint(1) NOT NULL,
  `ShortDescription` varchar(200) CHARACTER SET utf8mb4 DEFAULT NULL,
  `ShopID` int(11) NOT NULL,
  `IsPublic` bit(1) DEFAULT b'1',
  PRIMARY KEY (`ID`),
  KEY `FK_Product_Shop` (`ShopID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product`
--

LOCK TABLES `Product` WRITE;
/*!40000 ALTER TABLE `Product` DISABLE KEYS */;
/*!40000 ALTER TABLE `Product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Shop`
--

DROP TABLE IF EXISTS `Shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Shop` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) CHARACTER SET utf8mb4 NOT NULL,
  `Address` varchar(1000) CHARACTER SET utf8mb4 DEFAULT NULL,
  `UserID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Shop_User` (`UserID`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Shop`
--

LOCK TABLES `Shop` WRITE;
/*!40000 ALTER TABLE `Shop` DISABLE KEYS */;
INSERT INTO `Shop` VALUES (1,'Căn tin UIT','112 Xa Lộ Hà Nội, Thủ Đức',1),(2,'Tạp hóa YY','113 Lê Đại Hành, Q 11',1),(3,'Trà Sữa Yoyo','Làng Đại Học Thủ Đức',2);
/*!40000 ALTER TABLE `Shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
  `LastName` varchar(100) CHARACTER SET utf8mb4 NOT NULL,
  `DateOfBirth` date DEFAULT NULL,
  `Email` varchar(100) CHARACTER SET utf8mb4 NOT NULL,
  `Phone` char(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  `Address` varchar(500) CHARACTER SET utf8mb4 DEFAULT NULL,
  `Password` varchar(32) CHARACTER SET utf8mb4 NOT NULL,
  `UserTypeID` int(11) DEFAULT '1',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'Tiến','Nguyễn Văn',NULL,'tienseuit@gmail.com','0166 442 6606','Linh Trung, Thủ Đức','4297f44b13955235245b2497399d7a93',1),(2,'Như','Lý Huỳnh Như',NULL,'nhulh@gmail.com','0166 442 6607','Linh Trung, Thủ Đức','4297f44b13955235245b2497399d7a93',1),(3,'sadasd','ádasa',NULL,'sdasdasd',NULL,NULL,'c99a11a53a3748269e3f86d7ac38df11',1),(4,'sadasd','sadasd',NULL,'sadasd',NULL,NULL,'c99a11a53a3748269e3f86d7ac38df11',1),(5,'user.email','user.email',NULL,'user.email',NULL,NULL,'57ec3017ee5c15aaf21f619b8aa037e5',1),(6,'hahahaha','hahahaha',NULL,'hahahaha',NULL,NULL,'4f0b36a34946153c358f8b243428a1eb',1),(7,'123123','123123',NULL,'123123',NULL,NULL,'4297f44b13955235245b2497399d7a93',1);
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserPaymentInfo`
--

DROP TABLE IF EXISTS `UserPaymentInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserPaymentInfo` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) NOT NULL,
  `Pin` char(6) CHARACTER SET utf8mb4 NOT NULL,
  `Balance` decimal(18,0) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `IX_UserPaymentInfo` (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserPaymentInfo`
--

LOCK TABLES `UserPaymentInfo` WRITE;
/*!40000 ALTER TABLE `UserPaymentInfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `UserPaymentInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserType`
--

DROP TABLE IF EXISTS `UserType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserType` (
  `ID` int(11) NOT NULL,
  `Name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `Description` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserType`
--

LOCK TABLES `UserType` WRITE;
/*!40000 ALTER TABLE `UserType` DISABLE KEYS */;
INSERT INTO `UserType` VALUES (1,'Người dùng cá nhân','');
/*!40000 ALTER TABLE `UserType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserTypeRole`
--

DROP TABLE IF EXISTS `UserTypeRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserTypeRole` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Function` varchar(45) NOT NULL,
  `UserTypeID` int(11) DEFAULT '1',
  `Accept` bit(2) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserTypeRole`
--

LOCK TABLES `UserTypeRole` WRITE;
/*!40000 ALTER TABLE `UserTypeRole` DISABLE KEYS */;
INSERT INTO `UserTypeRole` VALUES (1,'DeviceBUS.*',1,''),(2,'ShopBUS.*',1,''),(3,'BillBUS.*',1,''),(4,'BillDetailBUS.*',1,''),(5,'PaymentHistoryBUS.*',1,''),(6,'ProductBUS.*',1,''),(7,'UserBUS.*',1,''),(8,'UserPaymentInfoBUS.*',1,''),(9,'UserTypeBUS.*',1,''),(10,'UserTypeRoleBUS.*',1,'');
/*!40000 ALTER TABLE `UserTypeRole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'jpayment'
--

--
-- Dumping routines for database 'jpayment'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-20 14:20:02
