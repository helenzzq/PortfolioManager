CREATE TABLE `conygre`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `portfolio_id` INT NULL,
  `account_activity_id` INT NULL,
  PRIMARY KEY (`id`));
INSERT INTO `conygre`.`user` (`id`, `name`, `portfolio_id`, `account_activity_id`) VALUES ('1', 'Ian', '1', '1');
