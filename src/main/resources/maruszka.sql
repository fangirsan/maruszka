CREATE DATABASE IF NOT EXISTS `maruszka`;
USE `maruszka`;

SET FOREIGN_KEY_CHECKS=0;

-- Create tables

-- mal_manufacturermalt_manufacturermalt_manufacturer
DROP TABLE IF EXISTS `malt_manufacturer`;
CREATE TABLE `malt_manufacturer` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`manufacturer_name` varchar(45) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE KEY (`manufacturer_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1; 

LOCK TABLE `malt_manufacturer` WRITE;

INSERT INTO `malt_manufacturer` VALUES
  (1, 'Weyerman'),
  (2, 'Strzegom'),
  (3, 'Malteurop');
  
UNLOCK TABLES;

-- country
DROP TABLE IF EXISTS `country`;
CREATE TABLE `country` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`country_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1; 

LOCK TABLE `country` WRITE;

INSERT INTO `country` VALUES
  (1, 'Poland'),
  (2, 'USA'),
  (3, 'England'),
  (4, 'Germany');
  
UNLOCK TABLES;

-- batch
DROP TABLE IF EXISTS `batch`;
CREATE TABLE `batch` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `batch_number` int(3) NOT NULL,
    `batch_style` varchar(45) DEFAULT NULL,
    `batch_name` varchar(45) DEFAULT NULL,
    `batch_creation_date` DATE,
    PRIMARY KEY (`id`),
    UNIQUE KEY(`batch_number`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;    

LOCK TABLE `batch` WRITE;

-- INSERT INTO `batch` VALUES
  -- (1, 1, 'Stout', 'Happy Stout', '2018-06-06');
  
UNLOCK TABLES;

-- malt
DROP TABLE IF EXISTS `malt`;
CREATE TABLE `malt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `malt_name` varchar(45) DEFAULT NULL,
  `malt_manufacturer` varchar(45) DEFAULT NULL,
  `malt_filling` int(3) DEFAULT NULL,
  `malt_ebc` int(3) DEFAULT NULL,
  `malt_usage` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY(`malt_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

LOCK TABLES `malt` WRITE;

INSERT INTO `malt` VALUES
	(1, 'Pale Ale', 'Malteurop', 100, 6, 'All'),
  (2, 'Carafa (R) typ I', 'Weyerman', 10, 900, 'Stout, Porter, Schwarzbier'),
  (3, 'Pils', 'Weyerman', 100, 3, 'All');
    
UNLOCK TABLES;

-- hops
DROP TABLE IF EXISTS `hop`;
CREATE TABLE `hop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hop_name` varchar(45) DEFAULT NULL,
  `alpha_acid_min` DECIMAL(3,1) DEFAULT NULL,
  `alpha_acid_max` DECIMAL(3,1) DEFAULT NULL,
  `hop_taste` char(3) DEFAULT NULL,
  `hop_aroma` char(3) DEFAULT NULL,
  `hop_origin` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`hop_name`)
 ) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
 
 LOCK TABLES `hop` WRITE;
 
 INSERT INTO `hop` VALUES
  (1, 'Marynka', '8.5', '10.5', 'y', 'y', 'Polska'),
  (2, 'Cascade', '5.6', '8.8', 'y', 'y', 'USA');
  
 UNLOCK TABLES;
 
 -- yeast
 DROP TABLE IF EXISTS `yeast`;
 CREATE TABLE `yeast` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `yeast_name` varchar(45) DEFAULT NULL,
  `yeast_manufacturer` varchar(45) DEFAULT NULL,
  `temp_min` int(2) DEFAULT NULL,
  `temp_max` int(2) DEFAULT NULL,
  `species` varchar(45) DEFAULT NULL,
  `flocculation` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`yeast_name`)
 ) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
  
LOCK TABLE `yeast` WRITE;
-- INSERT INTO `yeast` VALUES
  -- (1, 'US-05', 'Fermentis', 12, 25, 'Saccharomyces cerevisiae', 'medium');
 
 UNLOCK TABLES;
 
 -- join table for batch - malt
 DROP TABLE IF EXISTS `batch_malt`;
 CREATE TABLE `batch_malt` (
    `batch_id` int(11) NOT NULL,
    `malt_id` int(11) NOT NULL,
    PRIMARY KEY (`batch_id`, `malt_id`),
    
    -- fk_[referencing table name]_[referenced table name]_[referencing field name]
    CONSTRAINT FK_BATCH_MALT_ID FOREIGN KEY (malt_id) REFERENCES malt (id)
    ON DELETE NO ACTION ON UPDATE NO ACTION,
    
    CONSTRAINT `FK_MALT_BATCH_ID` FOREIGN KEY (`batch_id`) REFERENCES `batch` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION
 ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
 

 
 -- join table for batch - hop
 DROP TABLE if EXISTS `batch_hop`;
 CREATE TABLE `batch_hop` (
 	`batch_id` int(11) NOT NULL,
 	`hop_id` int(11) NOT NULL,
 	PRIMARY KEY(`batch_id`, `hop_id`),
 	
 	CONSTRAINT `FK_BATCH_HOP_ID` FOREIGN KEY (`hop_id`)
 	REFERENCES `hop` (`id`)
 	ON DELETE NO ACTION ON UPDATE NO ACTION,
 	
 	CONSTRAINT `FK_HOP_BATCH_ID` FOREIGN KEY (`batch_id`)
 	REFERENCES `batch` (`id`)
 	ON DELETE NO ACTION ON UPDATE NO ACTION
 ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
 
 
 SET FOREIGN_KEY_CHECKS=1;
 
 
 
