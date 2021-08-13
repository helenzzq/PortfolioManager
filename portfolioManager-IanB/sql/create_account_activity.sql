CREATE TABLE `conygre`.`account_activity` (
                                              `id` INT NOT NULL DEFAULT 0,
                                              `date` DATETIME NULL,
                                              `net_worth` DECIMAL NOT NULL DEFAULT 0,
                                              `cash_value` DECIMAL NOT NULL DEFAULT 0,
                                              `investment_value` DECIMAL NULL DEFAULT 0,
                                              `total_equity` DECIMAL NULL DEFAULT 0,
                                              PRIMARY KEY (`id`));
