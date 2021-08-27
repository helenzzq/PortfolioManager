CREATE TABLE `conygre`.`bond` (
                                   `ticker` VARCHAR(15) NOT NULL,
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
                                   INSERT INTO `conygre`.`bond` (`ticker`, `portfolio_id`, `currency`, `quantity`, `cost_per_share`, `market_price`, `market_value`, `profit_and_loss`, `percent_retained`, `percent_port`) VALUES ('Fidelity', '1', 'USD', '67', '12', '12.24', '809', '16.18', '2', '8.09');
