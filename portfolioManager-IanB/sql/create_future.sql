CREATE TABLE `conygre`.`future` (
                                   `ticker` VARCHAR(5) NOT NULL,
                                   `portfolio_id` INT NOT NULL,
                                   `currency` VARCHAR(10) NOT NULL,
                                   `quantity`  DECIMAL(10,1) NOT NULL,
                                   `cost_per_share` DECIMAL(10,2) NOT NULL,
                                   `market_price` DECIMAL(10,2) NOT NULL,
                                   `market_value` DECIMAL(10,2) NOT NULL,
                                   `profit_and_loss` DECIMAL(10,2) NOT NULL,
                                   `percent_retained` DECIMAL(10,2) NOT NULL,
                                   `percent_port` DECIMAL(10,2) NOT NULL,
                                   PRIMARY KEY (`ticker`));
INSERT INTO `conygre`.`future` (`ticker`, `portfolio_id`, `currency`, `quantity`, `cost_per_share`, `market_price`, `market_value`, `profit_and_loss`, `percent_retained`, `percent_port`) VALUES ('SI=F', '1', 'USD', '65', '22', '23.55', '1530', '100.75', '7.75', '14');
