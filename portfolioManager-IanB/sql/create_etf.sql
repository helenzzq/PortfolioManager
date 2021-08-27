CREATE TABLE `conygre`.`etf` (
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
                                   INSERT INTO `conygre`.`etf` (`ticker`, `portfolio_id`, `currency`, `quantity`, `cost_per_share`, `market_price`, `market_value`, `profit_and_loss`, `percent_retained`, `percent_port`) VALUES ('DWCR', '1', 'USD', '55', '35.5', '36.15', '1746', '35.75', '1.83', '18');
