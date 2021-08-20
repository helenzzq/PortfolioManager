CREATE TABLE `conygre`.`account_activity` (
                                              `date` DATETIME NOT NULL,
                                              `net_worth` DECIMAL NOT NULL DEFAULT 0,
                                              `cash_value` DECIMAL NOT NULL DEFAULT 0,
                                              `investment_value` DECIMAL NULL DEFAULT 0,
                                              `total_equity` DECIMAL NULL DEFAULT 0,
                                              PRIMARY KEY (`date`));
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-08-20', '0', '2000', '1000', '3000');
