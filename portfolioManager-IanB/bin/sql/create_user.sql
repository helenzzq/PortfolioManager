CREATE TABLE `conygre`.`user` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(100) NOT NULL,
  `CASH` DECIMAL NOT NULL DEFAULT 0,
  `MARKET_VALUE` DECIMAL NOT NULL DEFAULT 0,
  `TOTAL_EQUITY` DECIMAL NOT NULL DEFAULT 0,
  PRIMARY KEY (`ID`));