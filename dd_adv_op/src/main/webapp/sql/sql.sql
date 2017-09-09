CREATE TABLE `p_customer` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `promotionId` int(11) NOT NULL DEFAULT '0',
  `RedirectUrl` varchar(400) NOT NULL DEFAULT '',
  `CreateTime` datetime NOT NULL,
  `Status` tinyint(4) NOT NULL DEFAULT '0',
  `Notes` varchar(50) DEFAULT NULL,
  `customerId` int(11) NOT NULL,
  `LinkUrl` varchar(200) DEFAULT NULL,
  `DredirectUrl` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `pcid` (`promotionId`,`customerId`)
)  ;

CREATE TABLE `promotion` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `AdverId` int(11) NOT NULL DEFAULT '0',
  `RedirectUrl` varchar(400) NOT NULL DEFAULT '',
  `CreateTime` datetime NOT NULL,
  `Status` tinyint(4) NOT NULL DEFAULT '0',
  `Notes` varchar(50) DEFAULT NULL,
  `advLinkmanId` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
)  ;

CREATE TABLE `promotion_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `cdate` datetime DEFAULT NULL,
  `notes` varchar(50) DEFAULT NULL,
  `contact` varchar(50) DEFAULT NULL COMMENT '联系人',
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
)  ;

CREATE TABLE `promotion_domain` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `download` varchar(2000) DEFAULT NULL,
  `cdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
)  ;






