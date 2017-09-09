CREATE TABLE `desktop_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DeskIconUrl` varchar(200) NOT NULL DEFAULT '',
  `DeskName` varchar(50) DEFAULT NULL,
  `HomePage` varchar(200) DEFAULT NULL,
  `Status` int(4) DEFAULT NULL,
  `CreateTime` datetime NOT NULL,
  PRIMARY KEY (`ID`)
)  ;

CREATE TABLE `link_adver` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL DEFAULT '',
  `AdvLinkmanId`  int(11) DEFAULT NULL,
  `RedirectUrl` varchar(200) NOT NULL DEFAULT '',
  `AdverId` int(11) DEFAULT NULL,
  `Status` int(4) DEFAULT NULL,
  `ClickType` int(4) DEFAULT NULL,
  `Cap` int(11) DEFAULT NULL,
  `Cpm` int(11) DEFAULT NULL,
  `Notes` varchar(300)  DEFAULT NULL,
  `ExtensionContry` varchar(2000)  DEFAULT NULL,
  `IconUrl` varchar(200)  DEFAULT NULL ,
  `CreateTime` datetime NOT NULL,
  PRIMARY KEY (`ID`)
)  ;

CREATE TABLE `sdk_config` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `IntercepterRate` int(11) DEFAULT NULL,
  `PushNoticeRate` int(11) DEFAULT NULL,
  `Version` varchar(20) NOT NULL UNIQUE ,
  `CreateTime` datetime NOT NULL,
  PRIMARY KEY (`ID`)
)  ;

CREATE TABLE `sdk_uplimit` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LinkAdvId` int(11) DEFAULT NULL,
  `UpLimitValue` int(11) DEFAULT NULL,
  `PushNoticeRate` int(11) DEFAULT NULL,
  `CreateTime` datetime NOT NULL,
  PRIMARY KEY (`ID`)
)  ;

CREATE TABLE `app_name` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `packageName` varchar(50) DEFAULT NULL,
  `appName` varchar(50) DEFAULT NULL,
  `createDate` datetime NOT NULL,
  PRIMARY KEY (`id`)
)  ;

CREATE TABLE `subscribe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `adverId` int(11) NOT NULL,
  `redirectUrl` varchar(400) NOT NULL,
  `createTime` datetime NOT NULL,
  `status` int(4) NOT NULL,
  `advLinkmanId` int(11) DEFAULT NULL,
  `operatorId` varchar(3000) NOT NULL,
  `cou` varchar(3000) NOT NULL,
  `versionId` int(11) DEFAULT NULL,
  `modelId` int(11) DEFAULT NULL,
  `internet` int(4) DEFAULT NULL,
  `notes` varchar(400) DEFAULT NULL,
  `type` int(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `operator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`,`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;