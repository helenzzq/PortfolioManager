CREATE TABLE `conygre`.`bond` (
                                         `ticker` VARCHAR(5) NOT NULL,
                                         `portfolio_id` INT NOT NULL,
                                         `currency` VARCHAR(10) NOT NULL,
                                         `quantity` DECIMAL NOT NULL,
                                         `cost_per_share` DECIMAL NOT NULL,
                                         `post_cost` DECIMAL NOT NULL,
                                         `market_price` DECIMAL NOT NULL,
                                         `market_value` DECIMAL NOT NULL,
                                         `profit_and_loss` DECIMAL NOT NULL,
                                         `percent_retained` DECIMAL NOT NULL,
                                         `percent_port` DECIMAL NOT NULL,
                                         PRIMARY KEY (`ticker`));
