-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: quanlycafe
-- ------------------------------------------------------
-- Server version	8.0.45

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
-- Table structure for table `thucuong`
--

DROP TABLE IF EXISTS `thucuong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thucuong` (
  `MaThucUong` char(5) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TenThucUong` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Gia` decimal(10,2) DEFAULT NULL,
  `MaLoai` char(5) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Url` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`MaThucUong`),
  UNIQUE KEY `Url_UNIQUE` (`Url`),
  KEY `MaLoai` (`MaLoai`),
  CONSTRAINT `thucuong_ibfk_1` FOREIGN KEY (`MaLoai`) REFERENCES `loaithucuong` (`MaLoai`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thucuong`
--

LOCK TABLES `thucuong` WRITE;
/*!40000 ALTER TABLE `thucuong` DISABLE KEYS */;
INSERT INTO `thucuong` VALUES ('TU01','Cà phê sữa đá',123.00,'L01','/images/ca_phe_sua_da.jpg'),('TU02','Cà phê đen đá',25000.00,'L01','/images/ca_phe_den_da.jpg'),('TU03','Trà đào cam sả',35000.00,'L02','/images/tra_dao_cam_sa.jpg'),('TU04','Trà vải',32000.00,'L02','/images/tra_vai.jpg'),('TU05','Matcha đá xay',40000.00,'L03','/images/matcha_da_xay.jpg'),('TU06','Socola đá xay',42000.00,'L03','/images/socola_da_xay.jpg'),('TU07','Nước ép cam',30000.00,'L04','/images/nuoc_cam.jpg'),('TU08','Nước ép dưa hấu',28000.00,'L04','/images/nuoc_ep_dua_hau.jpg'),('TU09','Trà sữa trân châu',45000.00,'L05','/images/tra_sua_tran_chau.jpg'),('TU10','Trà sữa matcha',47000.00,'L05','/images/tra_sua_matcha.jpg'),('TU11','Cà phê muối',25000.00,'L01','/images/ca_phe_muoi.jpg');
/*!40000 ALTER TABLE `thucuong` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-08 12:55:33
