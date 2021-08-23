CREATE TABLE `conygre`.`stock` (
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
INSERT INTO `conygre`.`stock` (`ticker`, `portfolio_id`, `currency`, `quantity`, `cost_per_share`, `post_cost`, `market_price`, `market_value`, `profit_and_loss`, `percent_retained`, `percent_port`) VALUES ('BILI', '1', 'USD', '100', '10', '10', '10', '1000', '0', '0', '0.3333');
