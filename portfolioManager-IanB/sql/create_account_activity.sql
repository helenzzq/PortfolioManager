CREATE TABLE `conygre`.`account_activity` (
                                              `date` DATETIME NOT NULL,
                                              `net_worth` DECIMAL NOT NULL DEFAULT 0,
                                              `cash_value` DECIMAL NOT NULL DEFAULT 0,
                                              `investment_value` DECIMAL NULL DEFAULT 0,
                                              `total_equity` DECIMAL NULL DEFAULT 0,
                                              PRIMARY KEY (`date`));
INSERT INTO `conygre`.`account_activity` (`date`, `net_worth`, `cash_value`, `investment_value`, `total_equity`) VALUES ('2021-08-20', '0', '2000', '1000', '3000');
ALTER TABLE `conygre`.`account_activity`
    ADD COLUMN `account_activity_id` INT NOT NULL AFTER `total_equity`,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`date`, `account_activity_id`);
;
ALTER TABLE `conygre`.`account_activity`
DROP PRIMARY KEY,
ADD PRIMARY KEY (`account_activity_id`);
;
