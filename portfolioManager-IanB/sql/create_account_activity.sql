CREATE TABLE `conygre`.`account_activity` (
                                              `date` DATETIME NOT NULL,
                                              `net_worth` DECIMAL NOT NULL DEFAULT 0,
                                              `cash_value` DECIMAL(10,2) NOT NULL DEFAULT 0,
                                              `investment_value` DECIMAL(10,2) NULL DEFAULT 0,
                                              `total_equity` DECIMAL(10,2) NULL DEFAULT 0,
                                              `account_activity_id` INT NOT NULL DEFAULT 1,
                                                  PRIMARY KEY (`date`));
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2020-08-19', '0', '10000', '0', '8000');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-01-26', '0', '8500', '0', '9500');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-02-26', '-200', '2000', '6300', '9300');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-03-26', '200', '6500', '3529', '9700');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-04-28', '500', '4000', '6250', '10000');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-05-20', '650', '2950', '7250', '10150');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-06-28', '900', '2600', '7700', '10300');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-07-01', '900', '2600', '7700', '10300');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-07-05', '950', '2600', '7750', '10350');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-07-08', '1100', '2600', '7800', '10400');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-07-12', '1050', '1600', '8950', '10550');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-07-15', '1000', '1600', '8900', '10500');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-07-17', '950', '1600', '8850', '10450');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-07-20', '1070', '1600', '8970', '10570');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-07-23', '1170', '1000', '9670', '10670');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-07-24', '1200', '1000', '9700', '10700');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-07-28', '1250', '1000', '9750', '10750');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-08-16', '1200', '1000', '8500', '10700');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-08-17', '1190', '800', '9200', '10690');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-08-18', '1150', '800', '9160', '10650');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-08-19', '1220', '10720', '0', '10720');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-08-20', '1350', '6350', '4500', '10850');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-08-21', '1420', '1020', '9900', '10920');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-08-22', '1500', '1020', '9900', '11000');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-08-25', '1600', '1020', '9900', '11200');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-08-26', '1500', '550', '10450', '11000');
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-08-27', '1600', '1100', '10000', '11100');