CREATE TABLE `conygre`.`account_activity` (
                                              `date` DATETIME NOT NULL,
                                              `net_worth` DECIMAL NOT NULL DEFAULT 0,
                                              `cash_value` DECIMAL(10,2) NOT NULL DEFAULT 0,
                                              `investment_value` DECIMAL(10,2) NULL DEFAULT 0,
                                              `total_equity` DECIMAL(10,2) NULL DEFAULT 0,
                                              `account_activity_id` INT NOT NULL DEFAULT 1,
                                                  PRIMARY KEY (`date`));
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2020-08-19', '0', '10000', '0', '10000');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-04-24', '250', '4000', '6250', '10250');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-05-20', '150', '10150', '0', '10150');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-06-20', '500', '2500', '8000', '10500');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-07-16', '750', '1000', '9000', '10750');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-08-16', '700', '1000', '8500', '10700');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-08-17', '690', '800', '9200', '10690');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-08-19', '720', '10720', '0', '10720');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-08-20', '850', '6350', '4500', '10850');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-08-24', '920', '1076', '9940', '10920');