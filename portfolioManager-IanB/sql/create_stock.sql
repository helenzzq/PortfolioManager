CREATE TABLE `conygre`.`stock` (
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
INSERT INTO `conygre`.`stock` (`ticker`, `portfolio_id`, `currency`, `quantity`, `cost_per_share`, `market_price`, `market_value`, `profit_and_loss`, `percent_retained`, `percent_port`) VALUES ('BILI', '1', 'USD', '14', '80', '78', '1060', '-28', '1.99', '15');
INSERT INTO `conygre`.`stock` (`ticker`, `portfolio_id`, `currency`, `quantity`, `cost_per_share`, `market_price`, `market_value`, `profit_and_loss`, `percent_retained`, `percent_port`) VALUES ('GME', '1', 'USD', '17', '180', '210.29', '3754', '363', '16.8', '26');
INSERT INTO `conygre`.`stock` (`ticker`, `portfolio_id`, `currency`, `quantity`, `cost_per_share`, `market_price`, `market_value`, `profit_and_loss`, `percent_retained`, `percent_port`) VALUES ('SU', '1', 'USD', '59', '17.66', '18.66', '1101', '59', '5.64', '9');
