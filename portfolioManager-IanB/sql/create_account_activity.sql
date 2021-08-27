CREATE TABLE `conygre`.`account_activity` (
                                              `date` DATETIME NOT NULL,
                                              `net_worth` DECIMAL NOT NULL DEFAULT 0,
                                              `cash_value` DECIMAL(10,2) NOT NULL DEFAULT 0,
                                              `investment_value` DECIMAL(10,2) NULL DEFAULT 0,
                                              `total_equity` DECIMAL(10,2) NULL DEFAULT 0,
                                              `account_activity_id` INT NOT NULL DEFAULT 1,
                                                  PRIMARY KEY (`date`));
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2020-08-19', '0', '10000', '0', '10000');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-01-26', '50', '7000', '3000', '10050');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-02-26', '-100', '6500', '3400', '9900');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-03-26', '129', '6500', '3529', '10129');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-04-28', '250', '4000', '6250', '10250');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-05-20', '150', '10150', '0', '10150');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-06-28', '500', '2500', '8000', '10500');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-07-28', '750', '1000', '9000', '10750');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-08-16', '700', '1000', '8500', '10700');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-08-17', '690', '800', '9200', '10690');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-08-18', '650', '800', '9160', '10650');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-08-19', '720', '10720', '0', '10720');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-08-20', '850', '6350', '4500', '10850');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-08-21', '920', '1020', '9900', '10920');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-08-22', '1000', '1020', '9900', '11000');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-08-25', '1100', '1020', '9900', '11200');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-08-26', '1000', '550', '10450', '11000');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-08-27', '1050', '550', '10500', '11050');