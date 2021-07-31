/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 10.4.20-MariaDB : Database - seminarski
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`seminarski` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `seminarski`;

/*Table structure for table `firme` */

DROP TABLE IF EXISTS `firme`;

CREATE TABLE `firme` (
  `PK` int(10) NOT NULL AUTO_INCREMENT,
  `Ime` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`PK`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `firme` */

insert  into `firme`(`PK`,`Ime`) values 
(1,'Microsoft'),
(2,'Nordeus'),
(3,'MSg');

/*Table structure for table `korisnici` */

DROP TABLE IF EXISTS `korisnici`;

CREATE TABLE `korisnici` (
  `Ime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Sifra` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Ime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `korisnici` */

insert  into `korisnici`(`Ime`,`Sifra`) values 
('Milivoje','asfj'),
('Mstislav','wehe'),
('Vujadin','zjka');

/*Table structure for table `oglasi` */

DROP TABLE IF EXISTS `oglasi`;

CREATE TABLE `oglasi` (
  `PK` int(10) NOT NULL AUTO_INCREMENT,
  `Pozicija` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Senioritet` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Datum_isteka` date DEFAULT NULL,
  `Opis` varchar(10000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Firma` int(10) DEFAULT NULL,
  PRIMARY KEY (`PK`),
  KEY `Firma` (`Firma`),
  CONSTRAINT `oglasi_ibfk_1` FOREIGN KEY (`Firma`) REFERENCES `firme` (`PK`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `oglasi` */

insert  into `oglasi`(`PK`,`Pozicija`,`Senioritet`,`Datum_isteka`,`Opis`,`Firma`) values 
(1,'Java developer','Senior','2021-12-21','Trazimo vrhunskog programera sa 10 godina iskustva. Nudimo dobre uslove i stoni tenis na poslu (hehe ionako necete imati vremena da ga igrate naivcine).',3),
(2,'Azure Cpp developer','Medior','2022-02-01','Mi smo Microsoft. Nemamo sta vise da kazemo, sve je jasno.',1),
(3,'C#','Junior','2022-04-14','Developeri su nam malo sranje ali imamo besplatnu hranu, super pocetne plate koje rastu godisnje za cak 5 evra ako se bas potrudite i manekenke koje vam seku jabuke, sta covek vise moze pozeleti u zivotu.',2);

/*Table structure for table `oglasi_po_firmama` */

DROP TABLE IF EXISTS `oglasi_po_firmama`;

CREATE TABLE `oglasi_po_firmama` (
  `Oglas` int(10) NOT NULL,
  `Firma` int(10) NOT NULL,
  PRIMARY KEY (`Oglas`,`Firma`),
  KEY `oglasi_po_firmama_ibfk_2` (`Firma`),
  CONSTRAINT `oglasi_po_firmama_ibfk_1` FOREIGN KEY (`Oglas`) REFERENCES `oglasi` (`PK`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `oglasi_po_firmama_ibfk_2` FOREIGN KEY (`Firma`) REFERENCES `firme` (`PK`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `oglasi_po_firmama` */

insert  into `oglasi_po_firmama`(`Oglas`,`Firma`) values 
(1,3),
(2,1),
(3,2);

/*Table structure for table `prijave` */

DROP TABLE IF EXISTS `prijave`;

CREATE TABLE `prijave` (
  `Korisnik` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Oglas` int(10) NOT NULL,
  `Mail` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CV` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Ime` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Prezime` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Korisnik`,`Oglas`),
  KEY `prijave_ibfk_2` (`Oglas`),
  CONSTRAINT `prijave_ibfk_1` FOREIGN KEY (`Korisnik`) REFERENCES `korisnici` (`Ime`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `prijave_ibfk_2` FOREIGN KEY (`Oglas`) REFERENCES `oglasi` (`PK`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `prijave` */

insert  into `prijave`(`Korisnik`,`Oglas`,`Mail`,`CV`,`Ime`,`Prezime`) values 
('Milivoje',1,'aaa','a','a','a'),
('Milivoje',2,'aa','a','a','a'),
('Milivoje',3,'milivoje@gmail.com','github.com/milivoje','Milivoje','Milivojevic'),
('Mstislav',2,'mstislav@protonmail.com','github.com/mstislav','Milovan','Zupanovic'),
('Vujadin',1,'vujadin@outlook.com','bitbucket.com/vujadin','Vujko','Vitoperic');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
