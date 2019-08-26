CREATE DATABASE IF NOT EXISTS `ruk_database`;
USE `ruk_database`;

-- 01. Data Definition Language

CREATE TABLE `branches`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE `employees`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(20) NOT NULL,
    `last_name` VARCHAR(20) NOT NULL,
    `salary` DECIMAL(10, 2) NOT NULL,
    `started_on` DATE NOT NULL,
    `branch_id` INT(11) NOT NULL,
    CONSTRAINT `fk_employees_branches` FOREIGN KEY (`branch_id`) REFERENCES `branches`(`id`)
);

CREATE TABLE `clients`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `full_name` VARCHAR(50) NOT NULL,
    `age` INT(11) NOT NULL
);

CREATE TABLE `employees_clients`(
	`employee_id` INT(11),
    `client_id`  INT(11),
    CONSTRAINT `fk_employees_clients_employees` FOREIGN KEY (`employee_id`) REFERENCES `employees`(`id`),
    CONSTRAINT `fk_employees_clients_clients` FOREIGN KEY (`client_id`) REFERENCES `clients`(`id`)
);

CREATE TABLE `bank_accounts`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `account_number` VARCHAR(10) NOT NULL,
    `balance` DECIMAL(10, 2) NOT NULL,
    `client_id` INT(11) NOT NULL UNIQUE,
    CONSTRAINT `fk_bank_accounts_clients` FOREIGN KEY (`client_id`) REFERENCES `clients`(`id`)
);

CREATE TABLE `cards`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `card_number` VARCHAR(19) NOT NULL,
    `card_status` VARCHAR(7) NOT NULL,
    `bank_account_id` INT(11) NOT NULL,
    CONSTRAINT `fk_cards_bank_accounts` FOREIGN KEY (`bank_account_id`) REFERENCES `bank_accounts`(`id`)
);

-- 02. Data Manipulation Language

INSERT INTO `cards`(`card_number`, `card_status`, `bank_account_id`)
(
	SELECT 
		REVERSE(`c`.`full_name`),
		'Active',
		`c`.`id`
	FROM `clients` AS `c`
	WHERE `c`.`id` BETWEEN 191 AND 200
);

UPDATE `employees_clients` AS `emp_cli`
	JOIN
    (
		SELECT `e`.`id` AS `id`
		FROM `employees` AS `e`
		JOIN `employees_clients` AS `ec`
			ON `e`.`id` = `ec`.`employee_id`
		GROUP BY `e`.`id`
		ORDER BY COUNT(`ec`.`client_id`) ASC, `e`.`id`
		LIMIT 1
	) AS `employee_with_lowest_cards_count`
	SET `emp_cli`.`employee_id` = `employee_with_lowest_cards_count`.`id`		
	WHERE `emp_cli`.`employee_id` = `emp_cli`.`client_id`;
    
DELETE `e`
FROM `employees` AS `e`
LEFT JOIN `employees_clients` AS `ec`
	ON `e`.`id` = `ec`.`employee_id`
WHERE `ec`.`client_id` IS NULL;
    
-- 03. Quering 

SELECT `id`, `full_name`
FROM `clients`
ORDER BY `id`;

SELECT `id`, CONCAT(`first_name`, ' ', `last_name`) AS 'full_name', CONCAT('$', `salary`) AS `salary`, `started_on`
FROM `employees`
WHERE `salary` >= 100000 AND `started_on` >= '2018-01-01'
ORDER BY `salary` DESC, `id` ASC;

SELECT 
	`ca`.`id`,
    CONCAT(`ca`.`card_number`, ' : ', `cl`.`full_name`)
FROM `cards` AS `ca`
JOIN `bank_accounts` AS `ba`
	ON `ca`.`bank_account_id` = `ba`.`id`
JOIN `clients` AS `cl`
	ON `ba`.`client_id` = `cl`.id
ORDER BY `ca`.`id` DESC;

SELECT CONCAT(`e`.`first_name`, ' ', `e`.`last_name`) AS 'name', `e`.`started_on`, COUNT(`ec`.`client_id`) AS `count_of_clients`
FROM `employees` AS `e`
JOIN `employees_clients` AS `ec`
	ON `e`.`id` = `ec`.`employee_id`
GROUP BY `e`.`id`
ORDER BY `count_of_clients` DESC, `e`.`id` ASC
LIMIT 5;

SELECT `b`.`name`, COUNT(`c`.`id`) AS `count_of_cards`
FROM `branches` AS `b`
LEFT JOIN `employees` AS `e`
	ON `b`.`id` = `e`.`branch_id`
LEFT JOIN `employees_clients` AS `ec`
	ON `e`.`id` = `ec`.`employee_id`
LEFT JOIN `bank_accounts` AS `ba`
	ON `ec`.`client_id` = `ba`.`client_id`
LEFT JOIN `cards` AS `c`
	ON 	`ba`.`id` = `c`.`bank_account_id`
GROUP BY `b`.`id`
ORDER BY `count_of_cards` DESC, `b`.`name` ASC;

-- 04. Programmablility
DELIMITER $$
CREATE FUNCTION `udf_client_cards_count`(`name` VARCHAR(30))
RETURNS INT(11)
BEGIN
	DECLARE `client_cards_count` INT(11);
    SET `client_cards_count` := 
		(
			SELECT COUNT(`ca`.`id`)
            FROM `clients` AS `c`
            JOIN `bank_accounts` AS `ba`
				ON `c`.`id` = `ba`.`client_id`
			JOIN `cards` AS `ca`
				ON `ba`.`id` = `ca`.`bank_account_id`
			WHERE `c`.`full_name` = `name`
            GROUP BY `c`.`id`
        );
	RETURN `client_cards_count`;
END; $$

DELIMITER $$
CREATE PROCEDURE `udp_clientinfo`(`full_name` VARCHAR(50))
BEGIN
	SELECT `c`.`full_name`, `c`.`age`, `ba`.`account_number`, CONCAT('$', `ba`.`balance`)
    FROM `clients` AS `c`
    JOIN `bank_accounts` AS `ba`
		ON `c`.`id` = `ba`.`client_id`
    WHERE `c`.`full_name` = `full_name`;
END; $$